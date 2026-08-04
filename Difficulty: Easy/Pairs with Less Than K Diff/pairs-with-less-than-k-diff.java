class Solution {
    public static int countPairs(int arr[], int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int count = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            while (arr[right] - arr[left] >= k) {
                left++;
            }
            count += right - left;
        }
        return count;
    }
}