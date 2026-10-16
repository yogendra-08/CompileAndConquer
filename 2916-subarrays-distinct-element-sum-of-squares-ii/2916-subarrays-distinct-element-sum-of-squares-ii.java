class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[] sum, sq, lz;

    private void apply(int o, int len, long v) {
        sq[o] = (sq[o] + 2 * v * sum[o] + v * v % MOD * len) % MOD;
        sum[o] = (sum[o] + v * len) % MOD;
        lz[o] += v;
    }

    private void push(int o, int l, int r) {
        if (lz[o] != 0) {
            int mid = (l + r) >> 1;
            apply(o << 1, mid - l + 1, lz[o]);
            apply(o << 1 | 1, r - mid, lz[o]);
            lz[o] = 0;
        }
    }

    private void update(int o, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            apply(o, r - l + 1, 1);
            return;
        }
        push(o, l, r);
        int mid = (l + r) >> 1;
        if (ql <= mid) update(o << 1, l, mid, ql, qr);
        if (qr > mid) update(o << 1 | 1, mid + 1, r, ql, qr);
        sum[o] = (sum[o << 1] + sum[o << 1 | 1]) % MOD;
        sq[o] = (sq[o << 1] + sq[o << 1 | 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lz = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);
            update(1, 0, n - 1, prev + 1, i);
            last.put(nums[i], i);
            ans = (ans + sq[1]) % MOD;
        }
        return (int) ans;
    }
}