class Solution {
    public int countSubarray(int[] arr, int l, int r) {
        return countSubarrays(arr, r) - countSubarrays(arr, l - 1);
    }
    
    private int countSubarrays(int[] arr, int target) {
        int n = arr.length;
        int count = 0;
        int left = 0;
        int sum = 0;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            count += right - left + 1;
        }
        
        return count;
    }
}