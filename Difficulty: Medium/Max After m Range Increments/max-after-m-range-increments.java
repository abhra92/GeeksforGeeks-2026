class Solution {
    public int findMax(int n, int[] a, int[] b, int[] k) {
        int[] diff = new int[n + 1];
        
        int m = a.length;
        for (int i = 0; i < m; i++) {
            diff[a[i]] += k[i];
            diff[b[i] + 1] -= k[i];
        }
        
        int max = 0, curr = 0;
        for (int i = 0; i < n; i++) {
            curr += diff[i];
            max = Math.max(max, curr);
        }
        
        return max;
    }
}