class Solution {
    public int find(int[] arr) {
        long m = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            m = (m + arr[i] + 1) / 2;   // ceil((m + arr[i]) / 2)
        }
        return (int) m;
    }
}