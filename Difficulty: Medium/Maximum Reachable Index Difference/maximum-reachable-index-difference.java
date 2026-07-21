class Solution {
    public int maxIndexDifference(String s) {
        int[] minStart = new int[26];
        for (int i = 0; i < 26; i++) {
            minStart[i] = Integer.MAX_VALUE;
        }
        
        int maxDiff = -1;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            
            if (c == 0) {
                minStart[0] = Math.min(minStart[0], i);
                maxDiff = Math.max(maxDiff, 0);
            } else if (minStart[c - 1] != Integer.MAX_VALUE) {
                minStart[c] = Math.min(minStart[c], minStart[c - 1]);
                maxDiff = Math.max(maxDiff, i - minStart[c]);
            }
        }
        
        return maxDiff;
    }
}