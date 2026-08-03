class Solution {
    public int maxSumWithK(int[] arr, int k) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];

        // sum of first k elements
        int res = prefix[k] - prefix[0];
        int minPrefix = prefix[0];

        for (int i = k; i < n; i++) {
            // candidate: window ending at i, starting anywhere from 0 to i-k
            minPrefix = Math.min(minPrefix, prefix[i - k + 1]);
            res = Math.max(res, prefix[i + 1] - minPrefix);
        }

        return res;
    }
}