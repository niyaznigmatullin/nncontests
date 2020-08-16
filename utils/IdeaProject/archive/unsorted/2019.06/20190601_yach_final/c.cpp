/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;


string to_string(string s) {
  return '"' + s + '"';
}

string to_string(const char* s) {
  return to_string((string) s);
}

string to_string(bool b) {
  return (b ? "true" : "false");
}

template <typename A, typename B>
string to_string(pair<A, B> p) {
  return "(" + to_string(p.first) + ", " + to_string(p.second) + ")";
}

template <typename A>
string to_string(A v) {
  bool first = true;
  string res = "{";
  for (const auto &x : v) {
    if (!first) {
      res += ", ";
    }
    first = false;
    res += to_string(x);
  }
  res += "}";
  return res;
}

void debug_out() { cerr << endl; }

template <typename Head, typename... Tail>
void debug_out(Head H, Tail... T) {
  cerr << " " << to_string(H);
  debug_out(T...);
}

#ifndef LOCAL
#define debug(...) cerr << "[" << #__VA_ARGS__ << "]:", debug_out(__VA_ARGS__)
#else
#define debug(...) 42
#endif


long long const INF = 1LL << 60;

struct cht2 {
	vector<pair<int, long long>> z;

	void add(int a, long long b) {
		z.push_back({a, b});
	}

	long long get(long long x) {
		long long ans = -INF;
		for (auto &e : z) {
			ans = std::max(ans, e.first * x + e.second);
		}
		return ans;
	}
};

struct point {
	long long a;
	long long b;
};

string to_string(point p) {
	return "(" + to_string(p.a) + ", " + to_string(p.b) + ")";
}

struct cht {
	vector<point> z;

	void add(long long a, long long b) {
		// debug("add", a, b);
		while (z.size() > 1) {
			auto &p2 = z.back();
			auto &p1 = z[z.size() - 2];
			if ((p2.b - p1.b) * (p2.a - a) <= (p1.a - p2.a) * (b - p2.b)) {
				z.pop_back();
			} else break;
		}
		z.push_back({a, b});
		// debug(z);
	}

	long long get(long long x) {
		int l = 0;
		int r = (int) z.size();
		while (l < r - 1) {
			int mid = (l + r) >> 1;
			if ((z[mid].b - z[mid - 1].b) >= x * (z[mid - 1].a - z[mid].a)) {
				l = mid;
			} else {
				r = mid;
			}
		}
		return z[l].a * x + z[l].b;
	}
};

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, k;
	cin >> n >> k;
	vector<int> a(n), L(k);
	for (int i = 0; i < n; i++) cin >> a[i];
	for (int i = 0; i < k; i++) cin >> L[i];
	vector<long long> dp(n, -INF);
	for (int step = 0; step < k; step++) {
		cht t;
		if (step == 0) {
			t.add(0, -L[step] * (long long) a[0]);
		}
		vector<long long> ndp(n);
		for (int i = 0; i < n; i++) {
			if (t.z.size() > 0) {
				ndp[i] = t.get(a[i]) + (long long) a[i] * (i + 1);
			} else ndp[i] = -INF;
			if (dp[i] != -INF) {
				t.add(-(i + 1), dp[i] - (i + 1 < n ? L[step] * (long long) a[i + 1] : 0));
			}
		}
		dp.swap(ndp);
		// debug(dp);
	}
	cout << dp[n - 1] << endl;
}
