class Solution {
    static final int MOD = 1_000_000_007;

    public int maxAmount(int[] arr, int k) {
        int n = arr.length;
        int lo = 1, hi = 0;
        for (int v : arr) hi = Math.max(hi, v);

        // f(x) = count of tickets priced >= x
        // find largest p in [0, hi] with f(p) >= k  (p=0 means f(0)=inf, always true)
        int p = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long cnt = countAtLeast(arr, mid);
            if (cnt >= k) { p = mid; lo = mid + 1; }
            else hi = mid - 1;
        }

        long fPlus1 = countAtLeast(arr, p + 1); // tickets strictly > p, taken fully
        long remaining = k - fPlus1;             // tickets to take at price p

        long sum = 0;
        for (int v : arr) {
            if (v > p) {
                long full = sumRange(p + 1, v); // sum p+1..v
                sum = (sum + full) % MOD;
            }
        }
        sum = (sum + (remaining % MOD) * p) % MOD;
        return (int) sum;
    }

    private long countAtLeast(int[] arr, int x) {
        if (x <= 0) return Long.MAX_VALUE / 2; // effectively infinite
        long cnt = 0;
        for (int v : arr) {
            if (v >= x) cnt += (v - x + 1);
        }
        return cnt;
    }

    private long sumRange(int a, int b) {
        // sum a..b, a<=b, use mod-safe arithmetic (values up to 1e6, fits long fine before mod)
        long cntTerms = b - a + 1;
        long total = (long)(a + b) * cntTerms / 2;
        return total % MOD;
    }
}