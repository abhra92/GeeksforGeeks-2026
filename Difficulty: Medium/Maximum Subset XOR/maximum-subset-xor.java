class Solution {
    public int maxSubsetXOR(int[] arr) {
        int[] basis = new int[21];
        
        for (int num : arr) {
            for (int bit = 20; bit >= 0; bit--) {
                if (((num >> bit) & 1) == 0) continue;
                if (basis[bit] == 0) {
                    basis[bit] = num;
                    break;
                }
                num ^= basis[bit];
            }
        }
        
        int ans = 0;
        for (int bit = 20; bit >= 0; bit--) {
            if ((ans ^ basis[bit]) > ans) {
                ans ^= basis[bit];
            }
        }
        
        return ans;
    }
}