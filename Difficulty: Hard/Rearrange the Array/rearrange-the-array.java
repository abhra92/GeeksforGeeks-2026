class Solution {
    int minOperations(int[] b) {
        int n = b.length;
        int MOD = 1_000_000_007;
        boolean[] visited = new boolean[n + 1];
        Map<Integer, Integer> maxExp = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                int len = 0, j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = b[j - 1];
                    len++;
                }
                // factorize len, update maxExp
                int temp = len;
                for (int p = 2; (long) p * p <= temp; p++) {
                    int exp = 0;
                    while (temp % p == 0) {
                        temp /= p;
                        exp++;
                    }
                    if (exp > 0) {
                        maxExp.merge(p, exp, Math::max);
                    }
                }
                if (temp > 1) {
                    maxExp.merge(temp, 1, Math::max);
                }
            }
        }

        long ans = 1;
        for (Map.Entry<Integer, Integer> e : maxExp.entrySet()) {
            long p = e.getKey();
            int exp = e.getValue();
            for (int k = 0; k < exp; k++) {
                ans = (ans * p) % MOD;
            }
        }
        return (int) ans;
    }
};