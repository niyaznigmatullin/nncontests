	void getTest() {
		int[][] ans = new int[N][N];
		Random rand = new Random();
		final int M = 27;
		for (int i = 0; i < M; i++) {
			int x, y;
			do {
				x = rand.nextInt(9);
				y = rand.nextInt(9);
			} while (ans[x][y] != 0);
			loop: while (true) {
				int r = rand.nextInt(9) + 1;
				for (int it = 0; it < N; it++) {
					if (r == ans[x][it] || r == ans[it][y]) {
						continue loop;
					}
				}
				int thisX = x / 3;
				int thisY = y / 3;
				for (int i1 = 0; i1 < 3; i1++) {
					for (int j1 = 0; j1 < 3; j1++) {
						if (r == ans[thisX * 3 + i1][thisY * 3 + j1]) {
							continue loop;
						}
					}
				}
				ans[x][y] = r;
				break;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				out.print(ans[i][j] + " ");
			}
			out.println();
		}
		out.close();
	}

final int N = 9;
	int[][] a;

	void go(int m) {
		if (m == 81) {
			print();
		}
		int x = m / N;
		int y = m % N;
		if (a[x][y] != 0) {
			go(m + 1);
			return;
		}
		boolean[] used = new boolean[10];
		for (int i = 0; i < N; i++) {
			used[a[i][y]] = true;
			used[a[x][i]] = true;
		}
		int thisX = x / 3;
		int thisY = y / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				used[a[thisX * 3 + i][thisY * 3 + j]] = true;
			}
		}
		for (int i = 1; i < 10; i++) {
			if (used[i])
				continue;
			a[x][y] = i;
			go(m + 1);
			a[x][y] = 0;
		}
	}

	void print() {
		for (int i = 0; i < N; i++) {
			System.err.println(Arrays.toString(a[i]));
		}
		System.exit(0);
	}

	void solve() {
		a = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a[i][j] = nextInt();
			}
		}
		go(0);
	}