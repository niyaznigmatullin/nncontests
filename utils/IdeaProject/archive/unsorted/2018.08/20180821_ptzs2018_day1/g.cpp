/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */
#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

#define _GLIBCXX_DEBUG
#define DEBUG 1

#define err(...) fprintf(stderr, "%.2f : ", 1. * clock() / CLOCKS_PER_SEC), fprintf(stderr, __VA_ARGS__), fflush(stderr)

int P, G;
int MOD;

int gcd(int a, int b) {
	if (a < 0) a = -a;
	if (b < 0) b = -b;
	while (b > 0) {
		int t = a % b;
		a = b;
		b = t;
	}
	return a;
}

void add(int &a, int b) {
	a += b;
	if (a >= MOD) a -= MOD;
}

struct treeadd {
	int n;
	vector<int> a;

	treeadd(int sz) {
		n = 1;
		while (n < sz) n *= 2;
		a.assign(2 * n, 0);
	}

	void add(int l, int r, int x) {
		r--;
		l += n;
		r += n;
		while (l <= r) {
			if ((l & 1) == 1) {
				::add(a[l++], x);
			}
			if ((r & 1) == 0) {
				::add(a[r--], x);
			}
			l >>= 1;
			r >>= 1;
		}
		// for (int i = l; i < r; i++) ::add(a[i], x);
	}

	int get(int x) {
		int ans = 0;
		x += n;
		while (x > 0) {
			::add(ans, a[x]);
			x >>= 1;
		}
		return ans;
		// return a[x];
	}
};

struct treeGCD {
	int n;
	vector<int> a;

	treeGCD(int sz) {
		n = 1;
		while (n < sz) n *= 2;
		a.assign(2 * n, 0);
	}

	int getGCD(int l, int r) {
		r--;
		l += n;
		r += n;
		int ans = 0;
		while (l <= r) {
			if ((l & 1) == 1) {
				ans = gcd(ans, a[l++]);
			}
			if ((r & 1) == 0) {
				ans = gcd(ans, a[r--]);
			}
			l >>= 1;
			r >>= 1;
		}
		return ans;
		// int ans = 0;
		// for (int i = l; i < r; i++) {
		// 	ans = gcd(ans, a[i]);
		// }
		// return ans;
	}

	void addPoint(int x, int y) {
		x += n;
		add(a[x], y);
		while (x > 1) {
			x >>= 1;
			a[x] = gcd(a[x + x], a[x + x + 1]);
		}
		// add(a[x], y);
	}
};

int mul(int a, int b, int p) {
	return (int) ((long long) a * b % p);
}

int modPow(int a, int b, int p) {
	int ans = 1 % p;
	while (b > 0) {
		if ((b & 1) == 1) ans = mul(ans, a, p);
		a = mul(a, a, p);
		b >>= 1;
	}
	return ans;
}

int inverse(int a, int p) {
	return modPow(a, p - 2, p);
}

void init() {
	for (int i = 2; ; i++) {
		bool bad = false;
		for (int j = 2; j * j <= P - 1; j++) {
			if ((P - 1) % j != 0) continue;
			if (modPow(i, j, P) == 1 || modPow(i, (P - 1) / j, P) == 1) {
				bad = true;
				break;
			}
		}
		if (!bad) {
			G = i;
			break;
		}
	}
	// cerr << G << ' ' << P << endl;
	// int x = 1;
	// for (int i = 1; i < P - 1; i++) {
	// 	x = mul(x, G, P);
	// 	cerr << i << " " << x << endl;
	// 	assert(x != 1);
	// }
}

const int K = 5e2;

int invG;

template <const int max_size>
struct hashTable {
  int hash[max_size];
  int f[max_size];

  int position( int H ) const {
    int i = H % max_size;
    while (hash[i] && hash[i] != H)
      if (++i == max_size)
        i = 0;
    return i;
  }

  int& operator [] ( int H ) {
    int i = position(H);
    if (!hash[i])
      hash[i] = H, f[i] = 0;
    return f[i];
  }
  bool count( int H ) {
    int i = position(H);
    return hash[i] != 0;
  }
};

hashTable<int(10e6 + 3)> table;

// unordered_map<int, int> table(int(1e9 / K) + 1);

void precalc() {
	invG = inverse(G, P);
	int GK = modPow(G, K, P);
	for (int i = 0, r = 1; i < P; i += K)
		table[r] = i, r = mul(r, GK, P);
}

int logg( int x ) {
	// int old = x;
	forn(j, K) {
		if (table.count(x)) {
			// assert(modPow(G, table[x] + j, P) == old);
			return table[x] + j;
		}
		x = mul(x, invG, P);
	}
	assert(0);
}

void timeS( const char *s = 0) {
	static double start = 0;
	fprintf(stderr, "%.2f : %s\n", 1. * (clock() - start) / CLOCKS_PER_SEC, s ? s : "");
	start = clock();
}

inline int readChar();
template <class T = int> inline T readInt(); 
template <class T> inline void writeInt( T x, char end = 0 );
inline void writeChar( int x ); 
inline void writeWord( const char *s );

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	P = readInt();
	int n = readInt(), q = readInt();
	MOD = P - 1;
	init();
	precalc();
	timeS("precalc");
	vector<int> a(n);
	for (int i = 0; i < n; i++) {
		a[i] = logg(readInt());
	}
	timeS("input");
	treeadd f(n);
	treeGCD g(n);
	for (int i = 0; i < n; i++) {
		f.add(i, i + 1, a[i]);
		int x = i + 1 < n ? a[i + 1] : 0;
		add(x, MOD - a[i]);
		g.addPoint(i, x);
	}
	timeS("build");
	for (int i = 0; i < q; i++) {
		int type = readInt();
		int left = readInt() - 1;
		int right = readInt();
		if (type == 1) {
			int x = readInt();
			int pw = logg(x);
			f.add(left, right, pw);
			if (left > 0) {
				g.addPoint(left - 1, pw);
			}
			g.addPoint(right - 1, MOD - pw);
		} else {
			int ans = f.get(left);
			// err("ans = %d\n", ans);
			if (left + 1 < right) {
				ans = gcd(ans, g.getGCD(left, right - 1));
				// err("ans2 = %d\n", g.getGCD(left, right - 1));
			}
			ans = gcd(ans, P - 1);
			ans = (P - 1) / ans;
			writeInt(ans, '\n');
		}
	}
}

//3 8 14

/** Read */

static const int buf_size = 4096;

inline int getChar() {
	static char buf[buf_size];
	static int len = 0, pos = 0;
	if (pos == len)
		pos = 0, len = fread(buf, 1, buf_size, stdin);
	if (pos == len)
		return -1;
	return buf[pos++];
}

inline int readChar() {
	int c = getChar();
	while (c <= 32)
		c = getChar();
	return c;
}

template <class T>
inline T readInt() {
	int s = 1, c = readChar();
	T x = 0;
	if (c == '-')
		s = -1, c = getChar();
	while ('0' <= c && c <= '9')
		x = x * 10 + c - '0', c = getChar();
	return s == 1 ? x : -x;
}

/** Write */

static int write_pos = 0;
static char write_buf[buf_size];

inline void writeChar( int x ) {
	if (write_pos == buf_size)
		fwrite(write_buf, 1, buf_size, stdout), write_pos = 0;
	write_buf[write_pos++] = x;
}

template <class T> 
inline void writeInt( T x, char end ) {
	if (x < 0)
		writeChar('-'), x = -x;

	char s[24];
	int n = 0;
	while (x || !n)
		s[n++] = '0' + x % 10, x /= 10;
	while (n--)
		writeChar(s[n]);
	if (end)
		writeChar(end);
}

inline void writeWord( const char *s ) {
	while (*s)
		writeChar(*s++);
}

struct Flusher {
	~Flusher() {
		if (write_pos)
			fwrite(write_buf, 1, write_pos, stdout), write_pos = 0;
	}
} flusher;