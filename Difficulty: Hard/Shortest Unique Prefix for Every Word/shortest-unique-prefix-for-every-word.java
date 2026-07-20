class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int count = 0;
    }

    TrieNode root = new TrieNode();

    void insert(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) node.children[i] = new TrieNode();
            node = node.children[i];
            node.count++;
        }
    }

    String prefix(String s) {
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            sb.append(c);
            node = node.children[i];
            if (node.count == 1) break;
        }
        return sb.toString();
    }

    public ArrayList<String> findPrefixes(String[] arr) {
        root = new TrieNode();
        for (String s : arr) insert(s);
        ArrayList<String> res = new ArrayList<>();
        for (String s : arr) res.add(prefix(s));
        return res;
    }
}