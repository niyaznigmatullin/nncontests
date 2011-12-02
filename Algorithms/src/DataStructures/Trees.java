package DataStructures;
import java.util.Arrays;

public class Trees {
	static class SegmentTreeMinInt {
		int[] min;
		int n;

		public SegmentTreeMinInt(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			min = new int[this.n << 1];
			Arrays.fill(min, Integer.MAX_VALUE);
		}

		void set(int x, int y) {
			x += n;
			min[x] = y;
			while (x > 1) {
				x >>= 1;
				min[x] = Math.min(min[x << 1], min[(x << 1) | 1]);
			}
		}

		int getMin(int l, int r) {
			int ret = Integer.MAX_VALUE;
			l += n;
			r += n;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret = Math.min(ret, min[l++]);
				}
				if ((r & 1) == 0) {
					ret = Math.min(ret, min[r--]);
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	static class SegmentTreeMinLong {
		long[] min;
		int n;

		public SegmentTreeMinLong(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			min = new long[this.n << 1];
			Arrays.fill(min, Long.MAX_VALUE);
		}

		void set(int x, long y) {
			x += n;
			min[x] = y;
			while (x > 1) {
				x >>= 1;
				min[x] = Math.min(min[x << 1], min[(x << 1) | 1]);
			}
		}

		long getMin(int l, int r) {
			long ret = Long.MAX_VALUE;
			l += n;
			r += n;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret = Math.min(ret, min[l++]);
				}
				if ((r & 1) == 0) {
					ret = Math.min(ret, min[r--]);
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	static class SegmentTreeMaxInt {
		int[] max;
		int n;

		public SegmentTreeMaxInt(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			max = new int[this.n << 1];
			Arrays.fill(max, Integer.MIN_VALUE);
		}

		void set(int x, int y) {
			x += n;
			max[x] = y;
			while (x > 1) {
				x >>= 1;
				max[x] = Math.max(max[x << 1], max[(x << 1) | 1]);
			}
		}

		int getMax(int l, int r) {
			int ret = Integer.MIN_VALUE;
			l += n;
			r += n;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret = Math.max(ret, max[l++]);
				}
				if ((r & 1) == 0) {
					ret = Math.max(ret, max[r--]);
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	static class SegmentTreeMaxLong {
		long[] max;
		int n;

		public SegmentTreeMaxLong(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			max = new long[this.n << 1];
			Arrays.fill(max, Long.MIN_VALUE);
		}

		void set(int x, long y) {
			x += n;
			max[x] = y;
			while (x > 1) {
				x >>= 1;
				max[x] = Math.max(max[x << 1], max[(x << 1) | 1]);
			}
		}

		long getMax(int l, int r) {
			long ret = Long.MIN_VALUE;
			l += n;
			r += n;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret = Math.max(ret, max[l++]);
				}
				if ((r & 1) == 0) {
					ret = Math.max(ret, max[r--]);
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	static class SegmentTreeSum {
		long[] sum;
		int n;

		public SegmentTreeSum(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			sum = new long[this.n << 1];
		}

		void set(int x, long y) {
			x += n;
			while (x > 0) {
				sum[x] += y;
				x >>= 1;
			}
		}

		long getSum(int l, int r) {
			long ret = 0;
			l += n;
			r += n;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret += sum[l++];
				}
				if ((r & 1) == 0) {
					ret += sum[r--];
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	static class SegmentTreeAddMin {
		private long[] min;
		private long[] add;
		final int n;

		public SegmentTreeAddMin(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			min = new long[this.n << 1];
			add = new long[this.n << 1];
		}

		private void set(int node, long x) {
			add[node] += x;
		}

		private void relax(int node) {
			if (add[node] == 0) {
				return;
			}
			min[node] = getMin(node);
			set((node << 1) | 1, add[node]);
			set((node << 1) + 2, add[node]);
			add[node] = 0;
		}

		private long getMin(int node) {
			return min[node] + add[node];
		}

		private void add(int node, int l, int r, int left, int right, long x) {
			if (right <= l || r <= left) {
				return;
			}
			if (left <= l && r <= right) {
				set(node, x);
				return;
			}
			relax(node);
			int m = (l + r) >> 1;
			add((node << 1) | 1, l, m, left, right, x);
			add((node << 1) + 2, m, r, left, right, x);
			min[node] = Math.min(getMin((node << 1) | 1),
					getMin((node << 1) + 2));
		}

		private long getMin(int node, int l, int r, int left, int right) {
			if (right <= l || r <= left) {
				return Long.MAX_VALUE;
			}
			if (left <= l && r <= right) {
				return getMin(node);
			}
			relax(node);
			int m = (l + r) >> 1;
			return Math.min(getMin((node << 1) | 1, l, m, left, right),
					getMin((node << 1) + 2, m, r, left, right));
		}

		public void add(int l, int r, long x) {
			if (l >= r) {
				return;
			}
			add(0, 0, n, l, r, x);
		}

		public long getMin(int l, int r) {
			if (l >= r) {
				return Long.MAX_VALUE;
			}
			return getMin(0, 0, n, l, r);
		}
	}

}
