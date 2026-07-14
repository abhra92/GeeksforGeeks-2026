class Solution {
    public int bitonic(int[] arr) {
        int n = arr.length;
        int[] inc = new int[n];
        int[] dec = new int[n];
        
        inc[0] = 1;
        for (int i = 1; i < n; i++) {
            inc[i] = (arr[i] >= arr[i-1]) ? inc[i-1] + 1 : 1;
        }
        
        dec[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            dec[i] = (arr[i] >= arr[i+1]) ? dec[i+1] + 1 : 1;
        }
        
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, inc[i] + dec[i] - 1);
        }
        
        return maxLen;
    }
}