class Solution {
    public int maxDiffSubArrays(int[] arr) {
        int n = arr.length;
        int[] maxFront = new int[n], minFront = new int[n];
        int[] maxBack = new int[n], minBack = new int[n];

        maxFront[0] = minFront[0] = arr[0];
        int curMax = arr[0], curMin = arr[0];
        for (int i = 1; i < n; i++) {
            curMax = Math.max(arr[i], curMax + arr[i]);
            curMin = Math.min(arr[i], curMin + arr[i]);
            maxFront[i] = Math.max(maxFront[i-1], curMax);
            minFront[i] = Math.min(minFront[i-1], curMin);
        }

        maxBack[n-1] = minBack[n-1] = arr[n-1];
        curMax = arr[n-1]; curMin = arr[n-1];
        for (int i = n-2; i >= 0; i--) {
            curMax = Math.max(arr[i], curMax + arr[i]);
            curMin = Math.min(arr[i], curMin + arr[i]);
            maxBack[i] = Math.max(maxBack[i+1], curMax);
            minBack[i] = Math.min(minBack[i+1], curMin);
        }

        int ans = 0;
        for (int i = 0; i < n-1; i++) {
            ans = Math.max(ans, Math.abs(maxFront[i] - minBack[i+1]));
            ans = Math.max(ans, Math.abs(minFront[i] - maxBack[i+1]));
        }
        return ans;
    }
}