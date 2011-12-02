package DataStructures;
import java.util.Random;

public class Persistent {

	static class Pair<F, S> {
		public F first;
		public S second;

		public Pair(F first, S second) {
			this.first = first;
			this.second = second;
		}

	}

	static class Treap<V> {
		static final Random RAND = new Random(1213L);
		Node<V> root;

		public Treap(Node<V> root) {
			this.root = root;
		}

		static class Node<V> {
			Node<V> left;
			Node<V> right;
			int priority;
			int count;
			V value;

			public Node(Node<V> left, Node<V> right, V value, int priority) {
				this.left = left;
				this.right = right;
				this.priority = priority;
				this.value = value;
				count = getCount(left) + getCount(right) + 1;
			}

			public Node(Node<V> left, Node<V> right, Node<V> copy) {
				this(left, right, copy.value, copy.priority);
			}

			static <V> Node<V> merge(Node<V> t1, Node<V> t2) {
				if (t1 == null) {
					return t2;
				}
				if (t2 == null) {
					return t1;
				}
				Node<V> ret;
				if (t1.priority > t2.priority) {
					ret = new Node<V>(t1.left, merge(t1.right, t2), t1);
				} else {
					ret = new Node<V>(merge(t1, t2.left), t2.right, t2);
				}
				return ret;
			}

			@SuppressWarnings("unchecked")
			static <V> Node<V>[] split(Node<V> v, int k) {
				if (v == null) {
					return new Node[2];
				}
				Node<V>[] t;
				if (getCount(v.left) + 1 <= k) {
					t = split(v.right, k - getCount(v.left) - 1);
					t[1] = new Node<V>(v.left, t[1], v);
				} else {
					t = split(v.left, k);
					t[0] = new Node<V>(t[0], v.right, v);
				}
				return t;
			}

			static <V> int getCount(Node<V> v) {
				return v == null ? 0 : v.count;
			}
		}

		Pair<Treap<V>, Treap<V>> split(int k) {
			Node<V>[] t = Node.split(root, k);
			return new Pair<Persistent.Treap<V>, Persistent.Treap<V>>(
					new Treap<V>(t[0]), new Treap<V>(t[1]));
		}

		Treap<V> merge(Treap<V> treap) {
			return new Treap<V>(Node.merge(root, treap.root));
		}

	}

	static class PersistentArray {
		static class Node {
			Node left;
			Node right;
			int value;

			public Node(Node left, Node right, int value) {
				this.left = left;
				this.right = right;
				this.value = value;
			}

			public Node(Node left, Node right) {
				this.left = left;
				this.right = right;
			}

			public Node() {
				this(null, null, 0);
			}

			public Node(int value) {
				this.value = value;
			}

		}

		Node root;
		int n;

		public PersistentArray(int n) {
			this.n = n;
			root = new Node();
			makeArray(root, 0, n);
		}

		public PersistentArray(Node root, int n) {
			this.root = root;
			this.n = n;
		}

		static void makeArray(Node v, int left, int right) {
			int mid = (left + right) >> 1;
			if (left < mid) {
				v.left = new Node();
				makeArray(v.left, left, mid);
			}
			if (mid < right) {
				v.right = new Node();
				makeArray(v.right, mid, right);
			}
		}

		static Node set(Node v, int left, int right, int index, int newValue) {
			if (left + 1 == right) {
				return new Node(newValue);
			}
			int mid = (left + right) >> 1;
			if (index < mid) {
				return new Node(set(v.left, left, mid, index, newValue),
						v.right);
			} else {
				return new Node(v.left, set(v.right, mid, right, index,
						newValue));
			}
		}

		public PersistentArray set(int x, int y) {
			return new PersistentArray(set(root, 0, n, x, y), n);
		}

		public int get(int x) {
			int left = 0;
			int right = n;
			Node node = root;
			while (left + 1 < right) {
				int mid = (left + right) >> 1;
				if (x < mid) {
					node = node.left;
					right = mid;
				} else {
					node = node.right;
					left = mid;
				}
			}
			return node.value;
		}
	}

}
