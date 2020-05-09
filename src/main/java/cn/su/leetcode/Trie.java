package cn.su.leetcode;

/**
 * * Your Trie object will be instantiated and called as such:
 * * Trie obj = new Trie();
 * * obj.insert(word);
 * * boolean param_2 = obj.search(word);
 * * boolean param_3 = obj.startsWith(prefix);
 *
 * @author suyulong
 * @date 2019/9/25 4:42 PM
 */
public class Trie {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                return null;
            }
            node = node.get(ch);
        }
        return node;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private class TrieNode {
        private final int R = 26;
        private TrieNode[] links;
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        //取的就是下一个链接
        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        //新建一条边，trie节点实际存在边上的
        public void put(char ch, TrieNode trieNode) {
            links[ch - 'a'] = trieNode;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }

    }
}

