class Solution {

    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {

            events.add(new int[]{b[0], -b[2]}); // start

            events.add(new int[]{b[1], b[2]});  // end
        }

        Collections.sort(events, (a, b) -> {

            if (a[0] != b[0]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        PriorityQueue<Integer> pq =
            new PriorityQueue<>(Collections.reverseOrder());

        HashMap<Integer, Integer> removed =
            new HashMap<>();

        pq.offer(0);

        int prevMax = 0;

        List<List<Integer>> ans = new ArrayList<>();

        for (int[] event : events) {

            int x = event[0];
            int h = event[1];

            if (h < 0) {

                pq.offer(-h);

            } else {

                removed.put(
                    h,
                    removed.getOrDefault(h, 0) + 1
                );
            }

            while (!pq.isEmpty()) {

                int top = pq.peek();

                if (removed.getOrDefault(top, 0) > 0) {

                    removed.put(
                        top,
                        removed.get(top) - 1
                    );

                    pq.poll();

                } else {
                    break;
                }
            }

            int currMax = pq.peek();

            if (currMax != prevMax) {

                ans.add(
                    Arrays.asList(x, currMax)
                );

                prevMax = currMax;
            }
        }

        return ans;
    }
}