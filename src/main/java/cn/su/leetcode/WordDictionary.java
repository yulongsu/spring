package cn.su.leetcode;

/**
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 * 示例:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 *
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 *
 * @author suyulong
 * @date 2019/9/25 5:13 PM
 */

public class WordDictionary {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        if (word == null) {
            return;
        }
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
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one
     * letter.
     */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        return recurseSearch(root, word, 0);
    }

    public boolean recurseSearch(TrieNode node, String word, int depth) {
        if (depth == word.length()) {
            return node.isEnd;
        }

        char ch = word.charAt(depth);

        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.get(i) != null && recurseSearch(node.get(i), word, depth + 1)) {
                    return true;
                }
            }
        } else {
            if (node.get(ch) != null && recurseSearch(node.get(ch), word, depth + 1)) {
                return true;
            }
        }
        return false;
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

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public TrieNode get(int i) {
            return links[i];
        }

        public void put(char ch, TrieNode trieNode) {
            links[ch - 'a'] = trieNode;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }
    }

    public static void main(String[] args) {
        String[] strings = {"WordDictionary", "addWord", "addWord", "addWord", "addWord", "search", "search", "addWord",
            "search", "search", "search", "search", "search", "search"};
        WordDictionary wordDictionary = new WordDictionary();
        for (String s : strings) {
            wordDictionary.addWord(s.toLowerCase());
        }
        System.out.println(wordDictionary.search("a"));
    }
}