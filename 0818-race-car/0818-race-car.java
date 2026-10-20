import java.util.*;

class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();

    public int racecar(int target) {
        return dp(target);
    }

    private int dp(int target) {
        if (target == 0) return 0;
        if (memo.containsKey(target)) return memo.get(target);

        int n = 32 - Integer.numberOfLeadingZeros(target);
        int full = (1 << n) - 1;

        if (full == target) {
            memo.put(target, n);
            return n;
        }

        int best = n + 1 + dp(full - target);

        int previous = (1 << (n - 1)) - 1;

        for (int m = 0; m < n - 1; m++) {
            int backward = (1 << m) - 1;
            int remaining = target - previous + backward;

            best = Math.min(best, (n - 1) + 1 + m + 1 + dp(remaining));
        }

        memo.put(target, best);
        return best;
    }
}