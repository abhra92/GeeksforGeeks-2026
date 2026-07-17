class Solution {
    static final int MOD = 1_000_000_007;
    static final int INF = Integer.MAX_VALUE / 2;

    public int findWays(int[][] matrix, int k) {
        int n = matrix.length, m = matrix[0].length;

        // rowSuffix[r][c] = sum of row r, cols c..m-1
        int[][] rowSuf = new int[n][m + 1];
        for (int r = 0; r < n; r++)
            for (int c = m - 1; c >= 0; c--)
                rowSuf[r][c] = rowSuf[r][c + 1] + matrix[r][c];

        // colSuffix[r][c] = sum of col c, rows r..n-1
        int[][] colSuf = new int[n + 1][m];
        for (int c = 0; c < m; c++)
            for (int r = n - 1; r >= 0; r--)
                colSuf[r][c] = colSuf[r + 1][c] + matrix[r][c];

        // firstRowWithOne[r][c] = smallest rr>=r with a 1 in row rr, cols c..m-1
        int[][] frow = new int[n][m];
        for (int c = 0; c < m; c++) {
            frow[n - 1][c] = rowSuf[n - 1][c] > 0 ? n - 1 : INF;
            for (int r = n - 2; r >= 0; r--)
                frow[r][c] = rowSuf[r][c] > 0 ? r : frow[r + 1][c];
        }

        // firstColWithOne[r][c] = smallest cc>=c with a 1 in col cc, rows r..n-1
        int[][] fcol = new int[n][m];
        for (int r = 0; r < n; r++) {
            fcol[r][m - 1] = colSuf[r][m - 1] > 0 ? m - 1 : INF;
            for (int c = m - 2; c >= 0; c--)
                fcol[r][c] = colSuf[r][c] > 0 ? c : fcol[r][c + 1];
        }

        int[][] i0 = new int[n][m], j0 = new int[n][m];
        int[][] dp = new int[n][m]; // k=1 layer
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                i0[r][c] = (frow[r][c] != INF && frow[r][c] <= n - 2) ? frow[r][c] + 1 : INF;
                j0[r][c] = (fcol[r][c] != INF && fcol[r][c] <= m - 2) ? fcol[r][c] + 1 : INF;
                dp[r][c] = (frow[r][c] != INF) ? 1 : 0;
            }

        if (k == 1) return dp[0][0];

        for (int layer = 2; layer <= k; layer++) {
            int[][] sufR = new int[m][n + 1]; // sufR[c][i] = sum dp[i'][c] for i'>=i
            for (int c = 0; c < m; c++)
                for (int r = n - 1; r >= 0; r--)
                    sufR[c][r] = (sufR[c][r + 1] + dp[r][c]) % MOD;

            int[][] sufC = new int[n][m + 1]; // sufC[r][j] = sum dp[r][j'] for j'>=j
            for (int r = 0; r < n; r++)
                for (int c = m - 1; c >= 0; c--)
                    sufC[r][c] = (sufC[r][c + 1] + dp[r][c]) % MOD;

            int[][] nxt = new int[n][m];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    long val = 0;
                    if (i0[r][c] != INF) {
                        int idx = Math.max(r + 1, i0[r][c]);
                        val += sufR[c][idx];
                    }
                    if (j0[r][c] != INF) {
                        int idx = Math.max(c + 1, j0[r][c]);
                        val += sufC[r][idx];
                    }
                    nxt[r][c] = (int) (val % MOD);
                }
            }
            dp = nxt;
        }
        return dp[0][0];
    }
}