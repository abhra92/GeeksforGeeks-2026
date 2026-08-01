class Solution {
    static final int MOD = 1_000_000_007;
    static final int[] PRIMES = {2,3,5,7,11,13,17,19,23,29};

    public int countSubsets(int[] arr) {
        int[] valMask = new int[31];
        boolean[] valid = new boolean[31];

        for (int v = 2; v <= 30; v++) {
            int x = v, mask = 0;
            boolean sqfree = true;
            for (int i = 0; i < PRIMES.length; i++) {
                int p = PRIMES[i];
                if (x % p == 0) {
                    int cnt = 0;
                    while (x % p == 0) { x /= p; cnt++; }
                    if (cnt > 1) sqfree = false;
                    mask |= (1 << i);
                }
            }
            valid[v] = sqfree;
            valMask[v] = mask;
        }

        long[] countByMask = new long[1 << PRIMES.length];
        long ones = 0;

        for (int x : arr) {
            if (x == 1) ones++;
            else if (x >= 2 && x <= 30 && valid[x]) {
                countByMask[valMask[x]]++;
            }
            // else: bad element (has squared prime factor), ignore entirely
        }

        long[] dp = new long[1 << PRIMES.length];
        dp[0] = 1;

        for (int m = 1; m < (1 << PRIMES.length); m++) {
            long cnt = countByMask[m];
            if (cnt == 0) continue;
            long[] ndp = dp.clone();
            for (int s = 0; s < (1 << PRIMES.length); s++) {
                if (dp[s] == 0) continue;
                if ((s & m) == 0) {
                    ndp[s | m] = (ndp[s | m] + dp[s] * (cnt % MOD)) % MOD;
                }
            }
            dp = ndp;
        }

        long sumGood = 0;
        for (int s = 1; s < (1 << PRIMES.length); s++) {
            sumGood = (sumGood + dp[s]) % MOD;
        }

        long powTwo = modpow(2, ones, MOD);
        return (int) ((sumGood * powTwo) % MOD);
    }

    private long modpow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}