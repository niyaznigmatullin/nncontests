#include <cstdio>
#include <ctime>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <cassert>
#include <memory.h>

using namespace std;

const int DAYS[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

void read_int(int & a) {
  int c = getchar();
  while (c < '0' || c > '9') c = getchar();
  int ret = 0;
  while (c >= '0' && c <= '9') {
    ret = ret * 10 + c - '0';
    c = getchar();
  }
  a = ret;
}

void read_date(int & a, int & b, int & c) {
  read_int(a);
  read_int(b);
  read_int(c);  
}

void read_time(int & a, int & b) {
  read_int(a);
  read_int(b);
}

bool leap(int y) {
  return ((y % 400 == 0) || y % 100 != 0 && y % 4 == 0);
}

int get_days(int y, int m) {
  if (leap(y) && m == 2) {
    return 29;
  }
  return DAYS[m - 1];
}

long long read_all() {
  int y, m, d;
  read_date(y, m, d);
  int h, mn;
  read_time(h, mn);
  long long ret = (400 * 365 + 97) * 1440LL;
  ret *= y / 400;
  y %= 400;
  ret += y * 365 * 1440;
  int cl = (y + 3) / 4;
  if (y > 100) --cl;
  if (y > 200) --cl;
  if (y > 300) --cl;
  ret += cl * 1440;
  for (int i = 1; i < m; i++) {
    ret += get_days(y, i) * 1440;    
  }
  ret += (d - 1) * 1440;
  ret += h * 60 + mn;
  return ret;
}

void read_id(string & s) {
  s = "";
  int c = getchar();
  while (c <= 32) c = getchar();
  while (c > 32) {
    s += (char) c;
    c = getchar();
  }
}


const int N = 123456;

long long dtime[N];
int dur[N];
string s1[N], s2[N];

void solve() {
  int n;
  scanf("%d", &n);
  map<pair<string, string>, vector<int>> ids;
  for (int i = 0; i < n; i++) {
    dtime[i] = read_all();
    read_int(dur[i]);
    read_id(s1[i]);
    read_id(s2[i]);
    ids[make_pair(s1[i], s2[i])].push_back(i);
  }
  map<string, set<string> > ans;
  for (int i = 0; i < n; i++) {
    ans[s1[i]];
    ans[s2[i]];
  }
  for (int i = 0; i < n; i++) {
    pair<string, string> rr = make_pair(s2[i], s1[i]);
    if (ids.find(rr) == ids.end()) continue;
    vector<int> & a = ids[rr];
    auto it = lower_bound(a.begin(), a.end(), i);
    if (it == a.end()) continue;
    int pos = *it;
    long long lt = dtime[i] + dur[i] + 1440;
    if (lt < dtime[pos]) continue;
    ans[s1[i]].insert(s2[i]);
    ans[s2[i]].insert(s1[i]);
  }
  for (auto e = ans.begin(); e != ans.end(); e++) {
    printf("%s:", e->first.c_str());
    set<string> & ss = e->second;
    for (auto f = ss.begin(); f != ss.end(); f++) {
      printf(" %s", f->c_str());
    }
    puts("");
  }
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}