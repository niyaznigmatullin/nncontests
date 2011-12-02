import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class StringFunctions {
	static final int ALPHABET = 26;
	static final int MAXSUMLEN = 1000001;

	static class AhoCorasick {
		int[][] g;
		int[] sufLink;
		int free;
		boolean[] was;

		public AhoCorasick(int len) {
			g = new int[ALPHABET][len];
			for (int[] d : g) {
				Arrays.fill(d, -1);
			}
			sufLink = new int[len];
			was = new boolean[len];
			Arrays.fill(sufLink, -1);
			free = 1;
		}

		int add(String s, int id) {
			int p = 0;
			for (int i = 0; i < s.length(); i++) {
				int c = s.charAt(i)
						- (Character.isUpperCase(s.charAt(i)) ? 'A' : 'a');
				if (g[c][p] == -1) {
					g[c][p] = free++;
				}
				p = g[c][p];
			}
			return p;
		}

		void buildSuf() {
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.add(0);
			while (!q.isEmpty()) {
				int v = q.poll();
				for (int i = 0; i < ALPHABET; i++) {
					int u = g[i][v];
					int w = sufLink[v] == -1 ? 0 : g[i][sufLink[v]];
					if (u >= 0) {
						sufLink[u] = w;
						q.add(u);
					} else {
						g[i][v] = w;
					}
				}
			}
		}
	}

	static int[] getZFunction(String s) {
		int[] z = new int[s.length()];
		z[0] = 0;
		int l = 0;
		int r = 0;
		for (int i = 1; i < s.length(); i++) {
			z[i] = i >= r ? 0 : Math.min(z[i - l], r - i);
			while (z[i] + i < s.length()
					&& s.charAt(z[i]) == s.charAt(i + z[i])) {
				z[i]++;
			}
			if (i + z[i] > r) {
				r = i + z[i];
				l = i;
			}
		}
		return z;
	}

	static int[] getZFunctionDumb(String s) {
		int[] z = new int[s.length()];
		z[0] = 0;
		for (int i = 1; i < s.length(); i++) {
			while (z[i] + i < s.length()
					&& s.charAt(z[i]) == s.charAt(i + z[i])) {
				z[i]++;
			}
		}
		return z;
	}

	static int[] getPrefixFunction(String s) {
		int k = -1;
		int[] p = new int[s.length()];
		p[0] = -1;
		for (int i = 1; i < s.length(); i++) {
			while (k > -1 && s.charAt(i) != s.charAt(k + 1)) {
				k = p[k];
			}
			if (s.charAt(i) == s.charAt(k + 1)) {
				k++;
			}
			p[i] = k;
		}
		return p;
	}

	static int[] getPrefixFunctionDumb(String s) {
		int[] p = new int[s.length()];
		Arrays.fill(p, -1);
		for (int i = 1; i < s.length(); i++) {
			for (int j = i; j > 0; j--) {
				if (s.startsWith(s.substring(j, i + 1))) {
					p[i] = i - j;
				}
			}
		}
		return p;
	}

	static int[] getAllSubstrings(String s, String t) {
		int m = s.length();
		s += "~" + t;
		int[] p = new int[s.length()];
		p[0] = -1;
		int k = -1;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 1; i < s.length(); i++) {
			while (k > -1 && s.charAt(i) != s.charAt(k + 1)) {
				k = p[k];
			}
			if (s.charAt(i) == s.charAt(k + 1)) {
				k++;
			}
			if (k == m - 1) {
				ans.add(i - 2 * m);
			}
			p[i] = k;
		}
		int[] ret = new int[ans.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = ans.get(i);
		}
		return ret;
	}

	static class SuffixAutomaton {
		int[][] link;
		int[] sufLink;
		int[] length;
		int free;
		int root;
		int last;

		public SuffixAutomaton(int maxLength, int alphabet) {
			maxLength = maxLength * 2 + 1;
			link = new int[alphabet][maxLength];
			for (int[] d : link) {
				Arrays.fill(d, -1);
			}
			sufLink = new int[maxLength];
			length = new int[maxLength];
			free = 1;
			root = last = 0;
			sufLink[0] = -1;
		}

		void append(int c) {
			int newNode = free++;
			length[newNode] = length[last] + 1;
			while (last != -1 && link[c][last] == -1) {
				link[c][last] = newNode;
				last = sufLink[last];
			}
			if (last == -1) {
				sufLink[newNode] = root;
				last = newNode;
				return;
			}
			int q = link[c][last];
			if (length[q] == length[last] + 1) {
				sufLink[newNode] = q;
				last = newNode;
				return;
			}
			int copy = free++;
			length[copy] = length[last] + 1;
			for (int i = 0; i < link.length; i++) {
				link[i][copy] = link[i][q];
			}
			while (last != -1 && link[c][last] == q) {
				link[c][last] = copy;
				last = sufLink[last];
			}
			sufLink[copy] = sufLink[q];
			sufLink[newNode] = sufLink[q] = copy;
			last = newNode;
		}
	}

	static int getIndex(char c) {
		return c == '$' ? 0 : c - 'a' + 1;
	}

	static int[] buildSufArray(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		int[] count = new int[ALPHABET];
		for (int i = 0; i < n; i++) {
			count[getIndex(s[i])]++;
		}
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
		int[] p = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			p[--count[getIndex(s[i])]] = i;
		}
		int classes = 0;
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			if (i == 0 || s[p[i]] != s[p[i - 1]]) {
				classes++;
			}
			c[p[i]] = classes - 1;
		}
		int[] newC = new int[n];
		int[] newP = new int[n];
		count = new int[n];
		for (int h = 1; h <= n; h <<= 1) {
			Arrays.fill(count, 0, classes, 0);
			for (int i = 0; i < n; i++) {
				count[c[i]]++;
				p[i] -= h;
				if (p[i] < 0) {
					p[i] += n;
				}
			}
			for (int i = 1; i < classes; i++) {
				count[i] += count[i - 1];
			}
			for (int i = n - 1; i >= 0; i--) {
				newP[--count[c[p[i]]]] = p[i];
			}
			int newClasses = 0;
			for (int i = 0; i < n; i++) {
				if (i == 0 || (c[newP[i]] != c[newP[i - 1]])
						|| c[(newP[i] + h) % n] != c[(newP[i - 1] + h) % n]) {
					newClasses++;
				}
				newC[newP[i]] = newClasses - 1;
			}
			classes = newClasses;
			{
				int[] t = c;
				c = newC;
				newC = t;
			}
			{
				int[] t = p;
				p = newP;
				newP = t;
			}
		}
		Arrays.fill(count, 0, classes, 0);
		for (int i = 0; i < n; i++) {
			count[c[i]]++;
		}
		for (int i = 1; i < classes; i++) {
			count[i] += count[i - 1];
		}
		for (int i = 0; i < n; i++) {
			p[--count[c[i]]] = i;
		}
		return p;
	}

	static class SuffixTree {

		public static class Link {
			int l;
			int r;
			Node where;

			public Link(int l, int r, Node where) {
				this.l = l;
				this.r = r;
				this.where = where;
			}

		}

		public static class Node {
			Link[] link;
			Node sufLink;
			int depth;
			boolean isLeaf;
			int num;

			public Node(int depth, boolean isLeaf, int num) {
				super();
				this.depth = depth;
				this.isLeaf = isLeaf;
				link = new Link[ALPHABET];
				this.num = num;
			}

		}

		Node root;
		Node curNode;
		int curLetter;
		String s;
		Node lastAdded;
		int n;
		ArrayList<Node> nodes;

		public SuffixTree(String s) {
			nodes = new ArrayList<Node>();
			curNode = root = new Node(0, false, nodes.size());
			root.sufLink = root;
			curLetter = -1;
			nodes.add(root);
			this.s = s;
			n = s.length();
			int i = 0;
			int j = 0;
			while (i < n && j < n) {
				if (add(j, i)) {
					j++;
				} else {
					i++;
				}
			}
		}

		static int getIndex(char c) {
			return c == '$' ? 0 : c - 'a' + 1;
		}

		boolean add(int j, int i) {
			if (j > i) {
				return false;
			}
			int len = i - j;
			if (curNode.depth == len) {
				curLetter = getIndex(s.charAt(i));
			}
			Link curLink = curNode.link[curLetter];
			if (curLink != null
					&& getIndex(s.charAt(curLink.l + len - curNode.depth)) == getIndex(s
							.charAt(i))) {
				if (curLink.where.depth == len + 1) {
					curNode = curLink.where;
					curLetter = -1;
				}
				return false;
			}
			boolean wasAdded = false;
			Node lastNode = curNode;
			if (curNode.depth != len) {
				curNode = divideLink(curLink, len - curNode.depth,
						curNode.depth);
				wasAdded = true;
			}
			giveSufLink(curNode);
			if (wasAdded) {
				lastAdded = curNode;
			}
			Node newLeaf = new Node(n - j + 1, true, nodes.size());
			nodes.add(newLeaf);
			curNode.link[getIndex(s.charAt(i))] = new Link(j + curNode.depth,
					n - 1, newLeaf);
			j++;
			curNode = lastNode.sufLink;
			len--;
			while (curNode.depth < len) {
				Node nextNode = curNode.link[getIndex(s.charAt(j
						+ curNode.depth))].where;
				if (nextNode.depth > len) {
					break;
				}
				curNode = nextNode;
			}
			if (curNode.depth == len) {
				curLetter = -1;
				giveSufLink(curNode);
			} else {
				if (j + curNode.depth < n) {
					curLetter = getIndex(s.charAt(j + curNode.depth));
				}
			}
			return true;
		}

		void giveSufLink(Node node) {
			if (lastAdded != null) {
				lastAdded.sufLink = node;
				lastAdded = null;
			}
		}

		Node divideLink(Link link, int len, int depth) {
			Node newNode = new Node(depth + len, false, nodes.size());
			nodes.add(newNode);
			newNode.link[getIndex(s.charAt(link.l + len))] = new Link(link.l
					+ len, link.r, link.where);
			link.r = link.l + len - 1;
			link.where = newNode;
			return newNode;
		}
	}

	int[] getPalindromes1(char[] c) {
		if (c.length == 0) {
			return new int[0];
		}
		int[] d = new int[c.length];
		int l = 0;
		int r = 0;
		d[0] = 1;
		for (int i = 1; i < c.length; i++) {
			if (r <= i) {
				d[i] = 0;
			} else {
				int j = l + r - i;
				d[i] = Math.min(d[j], j - l);
			}
			while (i - d[i] >= 0 && i + d[i] < c.length
					&& c[i - d[i]] == c[i + d[i]]) {
				d[i]++;
			}
			if (r < i + d[i] - 1) {
				l = i - d[i] + 1;
				r = i + d[i] - 1;
			}
		}
		return d;
	}

	int[] getPalindromes2(char[] c) {
		if (c.length <= 1) {
			return new int[0];
		}
		int[] d = new int[c.length - 1];
		int l = -1;
		int r = -1;
		for (int i = 0; i < d.length; i++) {
			if (i >= r) {
				d[i] = 0;
			} else {
				int j = l + r - i - 1;
				d[i] = Math.min(d[j], j - l);
			}
			while (i - d[i] >= 0 && i + d[i] + 1 < c.length
					&& c[i - d[i]] == c[i + d[i] + 1]) {
				d[i]++;
			}
			if (i + d[i] > r) {
				l = i - d[i] + 1;
				r = i + d[i];
			}
		}
		return d;
	}

}
