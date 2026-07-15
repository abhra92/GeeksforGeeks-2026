class Solution {
    public int countWays(int n, int sum) {
        if (sum > 9 * n) return -1;
        
        int[][] dp = new int[n + 1][sum + 1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            int lo = (i == 1) ? 1 : 0;
            for (int s = 0; s <= sum; s++) {
                for (int d = lo; d <= 9; d++) {
                    if (s - d >= 0) dp[i][s] += dp[i-1][s-d];
                }
            }
        }
        
        return dp[n][sum] == 0 ? -1 : dp[n][sum];
    }
}