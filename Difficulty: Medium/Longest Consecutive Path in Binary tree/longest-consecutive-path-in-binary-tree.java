class Solution {
    int maxLen = 1;

    public int longestConsecutive(Node root) {
        if (root == null) return -1;
        dfs(root);
        return maxLen > 1 ? maxLen : -1;
    }

    private int dfs(Node node) {
        int left = 0, right = 0;

        if (node.left != null) {
            int l = dfs(node.left);
            if (node.left.data == node.data + 1) left = l;
        }

        if (node.right != null) {
            int r = dfs(node.right);
            if (node.right.data == node.data + 1) right = r;
        }

        int best = Math.max(left, right) + 1;
        maxLen = Math.max(maxLen, best);
        return best;
    }
}