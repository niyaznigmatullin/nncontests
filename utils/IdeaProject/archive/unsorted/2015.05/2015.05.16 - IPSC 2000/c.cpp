#include <bits/stdc++.h>

using namespace std;

int main() {
    char c;
    string s;
    while (cin >> c) {
        s += c;
    }
    unordered_map<long long, int> f;
    ifstream fin("cc.out");
    {
        long long a;
        int b;
        while (fin >> a >> b) {
            f[a] = b;
        }
    }
    unordered_map<long long, string> ss;
    for (int i = 0; i < (int) s.length(); i++) {
        long long hash = 0;
        for (int j = i + 1; j <= (int) s.length(); j++) {
            hash = hash * 239017 + s[j - 1];
            if (ss.find(hash) == ss.end() && f.find(hash) != f.end()) {
                ss[hash] = s.substr(i, j - i);
            }
        }
    }
    vector<pair<long long, int> > a(f.begin(), f.end());
    sort(a.begin(), a.end(), [](pair<long long, int> const & a, pair<long long, int> const & b) {
        return a.second < b.second;
    }); 
    for (int i = 0; i < (int) a.size(); i++) {
        if (i > 0 && a[i].second == a[i - 1].second) continue;
        if (i + 1 < (int) a.size() && a[i].second == a[i + 1].second) continue;
        cout << a[i].first << ' ' << ss[a[i].first] << ' ' << a[i].second << endl;
    }
}