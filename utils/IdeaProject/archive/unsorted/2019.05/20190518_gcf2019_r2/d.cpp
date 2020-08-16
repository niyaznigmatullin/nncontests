/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int const MOD = 1000000007;

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

vector<vector<int> > edges, redges;
vector<int> was;
vector<int> ts;
vector<int> color;

void dfs(int v) {
	was[v] = 1;
	for (int to : edges[v]) {
		if (was[to]) continue;
		dfs(to);
	}
	ts.push_back(v);
}

void dfs2(int v, int c) {
	color[v] = c;
	for (int to : redges[v]) {
		if (color[to] < 0) {
			dfs2(to, c);
		}
	}
}

void solve(int test) {
	cout << "Case #" << test << ": ";
	int n;
	cin >> n;
	vector<int> first(n), second(n);
	redges.assign(n, vector<int>());
	edges.assign(n, vector<int>());
	was.assign(n, 0);
	for (int i = 0; i < n; i++) {
		cin >> first[i] >> second[i];
		--first[i];
		--second[i];
		edges[i].push_back(first[i]);
		redges[first[i]].push_back(i);
		edges[i].push_back(second[i]);
		redges[second[i]].push_back(i);
	}
	ts.clear();
	for (int i = 0; i < n; i++) {
		if (was[i]) continue;
		dfs(i);
	}
	reverse(ts.begin(), ts.end());
	int c = 0;
	color.assign(n, -1);
	for (int v : ts) {
		if (color[v] >= 0) continue;
		dfs2(v, c++);
	}
	vector<vector<int> > vs(c);
	for (int i = 0; i < n; i++) vs[color[i]].push_back(i);
	vector<int> dp(n);
	auto add = [](int &x, int y) {
		if (x == -1) return;
		if (y == -1) x = -1; else {
			x += y;
			if (x >= MOD) x -= MOD;
		}
	};
	for (int i = 0; i < n; i++) {
		assert(color[first[i]] >= color[i]);
		assert(color[second[i]] >= color[i]);
	}
	for (int d = c - 1; d >= 0; d--) {
		int have = 0;
		for (int v : vs[d]) {
			if (v == 0) {
				dp[v] = 1;
				continue;
			}
			if (color[v] == color[first[v]] && dp[second[v]] != 0) {
				dp[v] = -1;
			} else if (color[v] == color[second[v]] && dp[first[v]] != 0) {
				dp[v] = -1;
			} else {
				add(dp[v], dp[first[v]]);
				add(dp[v], dp[second[v]]);
			}
		}
		for (int v : vs[d]) {
			add(have, dp[v]);
		}
		for (int v : vs[d]) {
			if (color[v] == color[first[v]] && color[v] == color[second[v]] && have != 0) {
				have = -1;
				break;
			}
		}
		if (have == -1) {
			for (int v : vs[d]) {
				dp[v] = -1;
			}
		}
	}
	int ans = 0;
	vector<int> g(n);
	for (int i = 0; i < n; i++) cin >> g[i];
	for (int i = 0; i < n; i++) {
		int x = g[i];
		if (x > 0) {
			if (dp[i] == -1) {
				ans = -1;
				break;
			} else {
				ans = (int) ((ans + (long long) x * dp[i]) % MOD);
			}
		}
	}
	if (ans == -1) {
		cout << "UNBOUNDED\n";
	} else {
		cout << ans << '\n';
	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}
}
