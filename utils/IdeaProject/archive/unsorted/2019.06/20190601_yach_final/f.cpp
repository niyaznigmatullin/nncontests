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

struct file {
	bool isDir;
	string name;
	string hash;
	int mask;
	unordered_map<string, int> children;
};

vector<file> files;

int newFile() {
	int ret = (int) files.size();
	files.emplace_back();
	return ret;
}

unordered_map<string, string> aFiles;

void add(int x, string name, string hash) {
	int pos = 0;
	int v = 0;
	files[v].mask |= 1 << x;
	string curName = "/";
	while (true) {
		int npos = (int) name.find("/", pos + 1);
		if (npos == (int) string::npos) break;
		string sname = name.substr(pos + 1, npos - pos - 1);
		curName += sname;
		curName += "/";
		if (files[v].children.find(sname) == files[v].children.end()) {
			int got = newFile();
			files[v].children[sname] = got;
		}
		v = files[v].children[sname];
		files[v].mask |= 1 << x;
		files[v].name = curName;
		files[v].isDir = true;
		pos = npos;
	}
	string fname = name.substr(pos + 1);
	if (fname != "") {
		curName += fname;
		if (files[v].children.find(fname) == files[v].children.end()) {
			int got = newFile();
			files[v].children[fname] = got;
		}
		v = files[v].children[fname];
		files[v].mask |= 1 << x;
		files[v].name = curName;
		files[v].hash = hash;
		files[v].isDir = false;
	}
}

vector<string> ans;

void dfs1(int v) {
	if (files[v].mask == 2) {
		if (files[v].isDir) {
			ans.push_back("mkdir " + files[v].name);
		} else {
			ans.push_back("link " + aFiles[files[v].hash] + " " + files[v].name);
		}
	}
	// debug(v, files[v].mask, files[v].name, files[v].isDir);
	for (auto &e : files[v].children) {
		dfs1(e.second);
	}
}

void dfs2(int v) {
	for (auto &e : files[v].children) {
		dfs2(e.second);
	}
	if (files[v].mask == 1) {
		if (files[v].isDir) {
			ans.push_back("rmdir " + files[v].name);
		} else {
			ans.push_back("unlink " + files[v].name);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m;
	cin >> n >> m;
	int root = newFile();
	files[root].name = "/";
	files[root].isDir = true;
	files[root].mask = 3;
	for (int i = 0; i < n; i++) {
		string name;
		cin >> name;
		if (name[name.size() - 1] == '/') {
			add(0, name, "");
		} else {
			string hash;
			cin >> hash;
			add(0, name, hash);
			aFiles[hash] = name;
		}
	}
	for (int i = 0; i < m; i++) {
		string name;
		cin >> name;
		string hash = "";
		if (name[name.size() - 1] != '/') {
			cin >> hash;
		}
		add(1, name, hash);
	}
	dfs1(0);
	dfs2(0);
	cout << ans.size() << '\n';
	for (string e : ans) cout << e << '\n';
}
