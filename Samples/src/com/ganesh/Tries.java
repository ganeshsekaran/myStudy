package com.ganesh;

import java.util.LinkedList;
import java.util.List;

public class Tries {

	private final TrieNode root;

	public Tries() {
		root = new TrieNode(' ');
	}

	public static void main(String[] args) {
		Tries main = new Tries();

		for (int i = 0; i < 100; i++) {
			main.insert("Ganesh" + i);
		}

		for (int i = 0; i < 100; i++) {
			//main.remove("Ganesh" + i);
			boolean search = main.search("Ganesh" + i);
			System.out.println(search);
		}
	}

	public void insert(String word) {
		if (search(word)) {
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
			if (child != null) {
				current = child;
			} else {
				TrieNode newChild = new TrieNode(ch);
				current.subList.add(newChild);
				current = newChild;
			}
			current.count++;
		}
		current.isEnd = true;
	}

	public void remove(String word) {
		if (search(word) == false) {
			System.out.println(word + " does not exist in trie\n");
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
			if (child.count == 1) {
				current.subList.remove(child);
				return;
			} else {
				child.count--;
				current = child;
			}
		}
		current.isEnd = false;
	}

	public boolean search(String word) {
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			if (current.subNode(ch) == null)
				return false;
			else
				current = current.subNode(ch);
		}
		return current.isEnd;
	}

	class TrieNode {
		final char content;
		List<TrieNode> subList;
		boolean isEnd;
		int count;

		TrieNode(char content) {
			this.content = content;
			subList = new LinkedList<TrieNode>();
			isEnd = false;
			count = 0;
		}

		TrieNode subNode(char c) {
			TrieNode child = null;
			for (TrieNode eachChild : subList) {
				if (eachChild.content == c) {
					child = eachChild;
					break;
				}
			}
			return child;
		}
	}
}