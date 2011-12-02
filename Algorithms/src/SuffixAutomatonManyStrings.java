import static java.util.Arrays.*;
import java.util.*;

public class SuffixAutomatonManyStrings {
    
	static class Terminal {
		boolean bad;
		int a;

		public Terminal(boolean bad, int a, int b) {
			this.bad = bad;
			this.a = a;
		}

		void merge(Terminal what) {
			if (bad) {
				return;
			}
			if (what.bad || (a >= 0 && what.a != a)) {
				bad = true;
			} else {
				a = what.a;
			}
		}

		void add(int v) {
			if (a < 0 || a == v) {
				a = v;
			} else {
				bad = true;
			}
		}
	}
	

	static class SuffixAutomaton {
		int[][] link;
		Terminal[] ter;
		int[] sufLink;
		int[] length;
		int[] letter;
		int free;
		int last;
		int root;
		boolean[] was;
		int[] d;
		int[] parent;

		public SuffixAutomaton(String[] s) {
			int sumLen = 0;
			for (String e : s) {
				sumLen += e.length();
			}
			int states = 2 * sumLen + 1;
			link = new int[26][states];
			ter = new Terminal[states];
			sufLink = new int[states];
			length = new int[states];
			letter = new int[states];
			for (int i = 0; i < states; i++) {
				ter[i] = new Terminal(false, -1, -1);
			}
			for (int[] d : link) {
				fill(d, -1);
			}
			fill(sufLink, -1);
			free = 1;
			last = root = 0;
			int curLen = 0;
			for (int j = 0; j < s.length; j++) {
				String e = s[j];
				curLen = 0;
				for (int i = 0; i < e.length(); i++) {
					curLen++;
					append(e.charAt(i) - 'a', curLen);
				}
				for (int i = last; i >= 0; i = sufLink[i]) {
					ter[i].add(j);
				}
				last = root;
				curLen++;
			}
			was = new boolean[states];
			for (int i = 0; i < free; i++) {
				if (was[i]) {
					continue;
				}
				dfs(i);
			}
			bfs(root);
		}

		void bfs(int root) {
			d = new int[was.length];
			parent = new int[d.length];
			parent[root] = -1;
			fill(d, Integer.MAX_VALUE);
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.add(root);
			d[root] = 0;
			while (!q.isEmpty()) {
				int v = q.poll();
				for (int i = 0; i < link.length; i++) {
					int e = link[i][v];
					if (e < 0) {
						continue;
					}
					if (d[e] != Integer.MAX_VALUE) {
						continue;
					}
					d[e] = d[v] + 1;
					parent[e] = v;
					q.add(e);
				}
			}
		}

		void dfs(int v) {
			was[v] = true;
			for (int i = 0; i < link.length; i++) {
				int e = link[i][v];
				if (e < 0) {
					continue;
				}
				if (!was[e]) {
					dfs(e);
				}
				ter[v].merge(ter[e]);
			}
		}

		void append(int c, int len) {
			if (link[c][last] >= 0 && length[last] + 1 == length[link[c][last]]) {
				last = link[c][last];
				return;
			}
			int newNode = free++;
			length[newNode] = len;
			letter[newNode] = c;
			while (last >= 0 && link[c][last] < 0) {
				link[c][last] = newNode;
				last = sufLink[last];
			}
			if (last < 0) {
				sufLink[newNode] = root;
				last = newNode;
				return;
			}
			int q = link[c][last];
			if (length[last] + 1 == length[q]) {
				sufLink[newNode] = q;
				last = newNode;
				return;
			}
			int copy = free++;
			letter[copy] = c;
			length[copy] = length[last] + 1;
			ter[copy].merge(ter[q]);
			for (int i = 0; i < link.length; i++) {
				link[i][copy] = link[i][q];
			}
			sufLink[copy] = sufLink[q];
			while (last >= 0 && link[c][last] == q) {
				link[c][last] = copy;
				last = sufLink[last];
			}
			sufLink[q] = sufLink[newNode] = copy;
			last = newNode;
		}

		String getShortest(int v) {
			StringBuilder sb = new StringBuilder();
			for (int i = v; i > 0; i = parent[i]) {
				sb.appendCodePoint(letter[i] + 'a');
			}
			return sb.reverse().toString();
		}
	}
}
