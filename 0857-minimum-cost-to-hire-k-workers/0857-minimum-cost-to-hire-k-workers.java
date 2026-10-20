class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[][] workers = new double[n][2];

        for (int i = 0; i < n; i++) {
            workers[i][0] = (double) wage[i] / quality[i];
            workers[i][1] = quality[i];
        }

        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        int totalQuality = 0;
        double minCost = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int q = (int) workers[i][1];

            maxHeap.offer(q);
            totalQuality += q;

            if (maxHeap.size() > k) {
                totalQuality -= maxHeap.poll();
            }

            if (maxHeap.size() == k) {
                double cost = totalQuality * workers[i][0];
                minCost = Math.min(minCost, cost);
            }
        }

        return minCost;
    }
}