import java.util.*;

class Solution {
    private int[] jobs;
    private int[] workloads;
    private int k;

    public int minimumTimeRequired(int[] jobs, int k) {
        this.jobs = jobs.clone();
        this.k = k;

        Arrays.sort(this.jobs);

        for (int i = 0, j = this.jobs.length - 1; i < j; i++, j--) {
            int temp = this.jobs[i];
            this.jobs[i] = this.jobs[j];
            this.jobs[j] = temp;
        }

        int low = jobs[0];
        int high = 0;

        for (int job : jobs) {
            low = Math.max(low, job);
            high += job;
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            workloads = new int[k];

            if (canFinish(0, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean canFinish(int index, int limit) {
        if (index == jobs.length) return true;

        int job = jobs[index];
        Set<Integer> tried = new HashSet<>();

        for (int i = 0; i < k; i++) {
            if (workloads[i] + job > limit) continue;
            if (!tried.add(workloads[i])) continue;

            workloads[i] += job;

            if (canFinish(index + 1, limit)) return true;

            workloads[i] -= job;

            if (workloads[i] == 0) break;
        }

        return false;
    }
}