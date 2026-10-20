class Solution {
    private int count;

    public int totalNQueens(int n) {
        count = 0;
        solve(0, n, new boolean[n],
              new boolean[2 * n - 1],
              new boolean[2 * n - 1]);
        return count;
    }

    private void solve(int row, int n, boolean[] cols,
                       boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || diag1[d1] || diag2[d2]) continue;

            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            solve(row + 1, n, cols, diag1, diag2);

            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}