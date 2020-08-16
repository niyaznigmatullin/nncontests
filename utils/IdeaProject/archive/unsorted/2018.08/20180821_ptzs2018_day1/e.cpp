/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

typedef double dbl;

struct num {
	dbl x, y;
	num() { }
	num( dbl a ) : x(cos(a)), y(sin(a)) { }
	num( dbl x, dbl y ) : x(x), y(y) { }
	num operator * ( double k ) const     { return num(x * k, y * k); }
	num operator + ( const num &a ) const { return num(x + a.x, y + a.y); }
	num operator - ( const num &a ) const { return num(x - a.x, y - a.y); }
	num operator * ( const num &a ) const { return num(x * a.x - y * a.y, x * a.y + y * a.x); }
	num ort() { return num(x, -y); }
};

const int maxlog = 13, maxn = 1 << maxlog;

num root[maxn];
int rev[maxn];

void init() {
	forn(i, maxn)
		rev[i] = (rev[i >> 1] >> 1) + ((i & 1) << (maxlog - 1));
	for (int k = 1; k < maxn; k *= 2) {
		num tmp(M_PI / k);
		root[k] = {1, 0};
		for (int i = 1; i < k; i++)
			root[k + i] = (i & 1) ? root[(k + i) >> 1] * tmp : root[(k + i) >> 1]; // Way #2
	}
}

void fft( const num *a, num *res ) {
	forn(i, maxn)
		res[rev[i]] = a[i];
	for (int k = 1; k < maxn; k *= 2)
		for (int i = 0; i < maxn; i += 2 * k)
			forn(j, k) {
				num tmp = root[k + j] * res[i + j + k];
				res[i + j + k] = res[i + j] - tmp;
				res[i + j] = res[i + j] + tmp;
			}
}

const int MOD = 40961;

num A[maxn], F[maxn];

// a, b, result -- arrays[maxn]
void mulPolynom( int *a, int *b, int *result ) {
	forn(i, maxn)
		A[i] = num(a[i], b[i]);
	fft(A, F);
	forn(i, maxn / 2 + 1) {
		int j = i ? maxn - i : 0;
		num z0 = F[i], z1 = F[j];
		F[i] = num(z0.x + z1.x, z0.y - z1.y) * num(z0.y + z1.y, z1.x - z0.x) * 0.25;
		F[j] = F[i].ort();
	}
	fft(F, A);
	reverse(A + 1, A + maxn);
	forn(i, maxn)
		result[i] = (int)round(A[i].x / maxn);
}

// int a[maxn], b[maxn], c[maxn];
int main() {
	// a[0] = 1, a[1] = 2, a[2] = 3;
	// b[0] = 1, b[1] = -1, b[2] = 7, b[3] = 5;
	// a[0] = 1, a[1] = 2;
	// b[0] = 1, b[1] = 2;
	// forn(i, 4) forn(j, 4) c[i + j] += a[i] * b[j];
	// forn(i, 8) printf("%d ", c[i]); puts("");

	init();
	// mulPolynom(a, b, c);
	// forn(i, 8) printf("%d ", c[i]); puts("");
}
