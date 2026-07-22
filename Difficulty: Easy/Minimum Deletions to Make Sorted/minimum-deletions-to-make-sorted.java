class Solution {
    public int minDeletions(int[] arr) {
        int n = arr.length;
        int[] tails = new int[n];
        int len = 0;
        for (int x : arr) {
            int lo = 0, hi = len;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (tails[mid] < x) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = x;
            if (lo == len) len++;
        }
        return n - len;
    }
}