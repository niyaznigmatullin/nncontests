/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

int uncode(char c) {
	if (c >= 'A' && c <= 'Z') return c - 'A';
	if (c >= 'a' && c <= 'z') return c - 'a' + 26;
	assert(false);
}

int const N = 1000001;
int const K = 52;

int w[N * K];

// const int textLen = K * K;
// const int wordLen = 80;
// const int maxN = 1000;
const int maxV = K * K + 1;
const int E = 2;

int n, m, root = 0, vn = 1, Next[maxV][E], p[maxV], suf[maxV], depth[maxV];
char is[maxV], pc[maxV];

// char text[textLen + 1], word[wordLen + 1];

int newV( int v, int ch ) {
  assert(vn < maxV);
  memset(Next[vn], -1, sizeof(Next[vn]));
  p[vn] = v, pc[vn] = ch;
  is[vn] = -1;
  suf[vn] = (!v ? 0 : -1);
  return vn++;
}

void add( const char *s ) {
  int v = root;
  int len = 0;
  while (*s) {
    int ch = *s++ - '0', &r = Next[v][ch];
    if (r <= 0)
      r = newV(v, ch);
    v = r;
    depth[v] = ++len;
  }
  is[v] = 1;
}

int getSuf( int v );
int getNext( int v, int c ) {
  int &r = Next[v][c];
  return r != -1 ? r : (r = getNext(getSuf(v), c));
}
int getSuf( int v ) {
  int &r = suf[v];
  return r != -1 ? r : (r = getNext(getSuf(p[v]), pc[v]));
}
int getIs( int v ) {
  char &r = is[v];
  return r != -1 ? r : (r = getIs(getSuf(v)));
}

int f[N * K + 1];
int st[K + K];

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);

	int k;
	cin >> k;
	string s;
	cin >> s;
	vector<string> a(k);
	for (int i = 0; i < k; i++) {
		cin >> a[i];
		string f(a[i]);
		reverse(all(f));
		add(f.c_str());
	}
	int n = 0;
	for (char c : s) {
		for (char d : a[uncode(c)]) {
			w[n++] = d - '0';
		}
	}
	int v = 0;
	f[n] = 0;
	int const INF = 1000000;
	int best = -INF;
	int sz = 0;
	st[sz++] = n;
	for (int i = n - 1; i >= 0; i--) {
		v = getNext(v, w[i]);
		if (getIs(v)) {
			f[i] = f[i + depth[v]];
			int l = -1;
			int r = sz;
			cerr << i + depth[v] << endl;
			cerr << i << endl;
			while (l < r - 1) {
				int mid = (l + r) >> 1;
				if (st[mid] >= i + depth[v]) {
					l = mid;
				} else {
					r = mid;
				}
			}
			assert(r != sz);
			f[i] = f[st[r]] + 1;
			// f[i] = max[i + 1, i + depth[v] - 1] + 1
		} else {
			f[i] = best == -INF ? -INF : best + 1;
		}
		best = std::max(best, f[i]);
		while (sz > 0 && f[st[sz - 1]] <= f[i]) {
			--sz;
		}
		st[sz++] = i;
	}
}
