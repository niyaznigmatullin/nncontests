package DataStructures;
import static java.util.Arrays.fill;

import java.util.Random;

public class Tree {
	static class MySetGen<K extends Comparable<K>> {
		final static Random RNG = new Random();

		static class Node<K> {
			K key;
			int priority;
			Node<K> l;
			Node<K> r;

			public Node(K k) {
				priority = RNG.nextInt();
				this.key = k;
			}
		}

		Node<K> root;

		void add(K e) {
			if (contains(e)) {
				return;
			}
			Node<K>[] t = split(root, e);
			root = merge(t[0], new Node<K>(e));
			root = merge(root, t[1]);
		}

		void remove(K e) {
			if (!contains(e)) {
				return;
			}
			root = remove(root, e);
		}

		private Node<K> max(Node<K> v) {
			while (v.r != null) {
				v = v.r;
			}
			return v;
		}

		private Node<K> min(Node<K> v) {
			while (v.l != null) {
				v = v.l;
			}
			return v;
		}

		private Node<K> getLower(Node<K> v, K e) {
			if (v == null) {
				return null;
			}
			int comp = v.key.compareTo(e);
			if (comp == 0) {
				if (v.l == null) {
					return null;
				}
				return max(v.l);
			}
			if (comp < 0) {
				Node<K> got = getLower(v.r, e);
				if (got == null) {
					return v;
				} else {
					return got;
				}
			} else {
				return getLower(v.l, e);
			}
		}

		private Node<K> getHigher(Node<K> v, K e) {
			if (v == null) {
				return null;
			}
			int comp = v.key.compareTo(e);
			if (comp == 0) {
				if (v.r == null) {
					return null;
				}
				return min(v.r);
			}
			if (comp < 0) {
				return getHigher(v.r, e);
			} else {
				Node<K> got = getHigher(v.l, e);
				if (got == null) {
					return v;
				} else {
					return got;
				}
			}
		}

		K lower(K e) {
			Node<K> got = getLower(root, e);
			return got == null ? null : got.key;
		}

		K higher(K e) {
			Node<K> got = getHigher(root, e);
			return got == null ? null : got.key;
		}

		K ceiling(K e) {
			Node<K> got = get(root, e);
			if (got != null) {
				System.err.println("YER");
				return got.key;
			}
			return higher(e);
		}

		K floor(K e) {
			Node<K> got = get(root, e);
			if (got != null) {
				return got.key;
			}
			return lower(e);
		}

		private Node<K> remove(Node<K> v, K e) {
			if (v == null) {
				return null;
			}
			if (v.key.compareTo(e) == 0) {
				return merge(v.l, v.r);
			}
			if (v.key.compareTo(e) < 0) {
				v.r = remove(v.r, e);
			} else {
				v.l = remove(v.l, e);
			}
			return v;
		}

		private Node<K> merge(Node<K> l, Node<K> r) {
			if (l == null) {
				return r;
			}
			if (r == null) {
				return l;
			}
			Node<K> ret;
			if (l.priority > r.priority) {
				l.r = merge(l.r, r);
				ret = l;
			} else {
				r.l = merge(l, r.l);
				ret = r;
			}
			return ret;
		}

		private Node<K>[] split(Node<K> v, K e) {
			if (v == null) {
				return new Node[2];
			}
			Node<K>[] t;
			if (v.key.compareTo(e) <= 0) {
				t = split(v.r, e);
				v.r = t[0];
				t[0] = v;
			} else {
				t = split(v.l, e);
				v.l = t[1];
				t[1] = v;
			}
			return t;
		}

		private Node<K> get(Node<K> v, K e) {
			if (v == null) {
				return null;
			}
			int comp = v.key.compareTo(e);
			if (comp == 0) {
				return v;
			} else if (comp < 0) {
				return get(v.r, e);
			} else {
				return get(v.l, e);
			}
		}

		boolean contains(K e) {
			return get(root, e) != null;
		}

	}

	@SuppressWarnings("unchecked")
	static class MySet<K extends Comparable<K>> {
		final static Random RNG = new Random();
		int n;
		Object[] keys;
		int[] priority;
		int[] left;
		int[] right;
		int free = 0;
		int[] spec1 = new int[2];
		int[] spec2 = new int[2];

		int getNewNode(K k) {
			keys[free] = k;
			priority[free] = RNG.nextInt();
			return free++;
		}

		public MySet(int n) {
			this.n = n;
			keys = new Object[n];
			priority = new int[n];
			left = new int[n];
			right = new int[n];
			root = -1;
			fill(left, -1);
			fill(right, -1);
		}

		int root;

		void add(K e) {
			if (contains(e)) {
				return;
			}
			int[] t = spec1;
			split(root, e, t);
			root = merge(t[0], getNewNode(e));
			root = merge(root, t[1]);
		}

		void remove(K e) {
			if (!contains(e)) {
				return;
			}
			root = remove(root, e);
		}

		private int max(int v) {
			while (right[v] != -1) {
				v = right[v];
			}
			return v;
		}

		private int min(int v) {
			while (left[v] != -1) {
				v = left[v];
			}
			return v;
		}

		private int getLower(int v, K e) {
			if (v == -1) {
				return -1;
			}
			int comp = ((Comparable<K>) keys[v]).compareTo(e);
			if (comp == 0) {
				if (left[v] == -1) {
					return -1;
				}
				return max(left[v]);
			}
			if (comp < 0) {
				int got = getLower(right[v], e);
				if (got == -1) {
					return v;
				} else {
					return got;
				}
			} else {
				return getLower(left[v], e);
			}
		}

		private int getHigher(int v, K e) {
			if (v == -1) {
				return -1;
			}
			int comp = ((Comparable<K>) keys[v]).compareTo(e);
			if (comp == 0) {
				if (right[v] == -1) {
					return -1;
				}
				return min(right[v]);
			}
			if (comp < 0) {
				return getHigher(right[v], e);
			} else {
				int got = getHigher(left[v], e);
				if (got == -1) {
					return v;
				} else {
					return got;
				}
			}
		}

		K lower(K e) {
			int got = getLower(root, e);
			return got == -1 ? null : (K) keys[got];
		}

		K higher(K e) {
			int got = getHigher(root, e);
			return got == -1 ? null : (K) keys[got];
		}

		K ceiling(K e) {
			int got = get(root, e);
			if (got != -1) {
				return (K) keys[got];
			}
			return higher(e);
		}

		K floor(K e) {
			int got = get(root, e);
			if (got != -1) {
				return (K) keys[got];
			}
			return lower(e);
		}

		private int remove(int v, K e) {
			if (v == -1) {
				return -1;
			}
			if (((K) keys[v]).compareTo(e) == 0) {
				return merge(left[v], right[v]);
			}
			if (((K) keys[v]).compareTo(e) < 0) {
				right[v] = remove(right[v], e);
			} else {
				left[v] = remove(left[v], e);
			}
			return v;
		}

		private int merge(int l, int r) {
			if (l == -1) {
				return r;
			}
			if (r == -1) {
				return l;
			}
			int ret;
			if (priority[l] > priority[r]) {
				right[l] = merge(right[l], r);
				ret = l;
			} else {
				left[r] = merge(l, left[r]);
				ret = r;
			}
			return ret;
		}

		private void split(int v, K e, int[] t) {
			if (v == -1) {
				t[0] = t[1] = -1;
				return;
			}
			if (((K) keys[v]).compareTo(e) <= 0) {
				split(right[v], e, t);
				right[v] = t[0];
				t[0] = v;
			} else {
				split(left[v], e, t);
				left[v] = t[1];
				t[1] = v;
			}
		}

		private int get(int v, K e) {
			if (v == -1) {
				return -1;
			}
			int comp = ((K) keys[v]).compareTo(e);
			if (comp == 0) {
				return v;
			} else if (comp < 0) {
				return get(right[v], e);
			} else {
				return get(left[v], e);
			}
		}

		boolean contains(K e) {
			return get(root, e) != -1;
		}

	}

}
