#include <bits/stdc++.h>

using namespace std;

int const N = 100100;

int n;
pair<long long, int> a[N];
map<long long, vector<int> > cnt;
map<long long, vector<int> >::iterator it1, it2;
int x[N], y[N], sz;
bool used[N];

vector<int> nulls;

long long pw(long long x) {
  long long ret = 1;
  while (x >= ret) {
    ret <<= 1;
  }
  return ret;
}

int main() {
  freopen("in.txt", "r", stdin);
  freopen("out.txt", "w", stdout);
  scanf("%d", &n);
  for (int i = 0; i < n; ++i) {
    scanf("%I64d", &a[i].first);
    a[i].second = i + 1;
    if (a[i].first == 0) {
      nulls.push_back(a[i].second);
    }
  }
  sort(a, a + n);
  for (int i = 0; i < n; ++i) {
    cnt[a[i].first].push_back(a[i].second);
  }
  for (int i = n - 1; i >= 0 && a[i].first > 0; --i) {
    if (used[a[i].second]) {
      continue;
    }
    if ((a[i].first & (a[i].first - 1)) == 0) {
      continue;
    }
    it1 = cnt.find(a[i].first);
    if (it1 == cnt.end() || it1->second.empty()) {
      continue;
    }
    long long sum = pw(a[i].first);
    it2 = cnt.find(sum - a[i].first);
    if (it2 == cnt.end() || it2->second.empty()) {
      continue;
    }
    x[sz] = it1->second.back();
    used[it1->second.back()] = true;
    it1->second.pop_back();
    y[sz] = it2->second.back();
    used[it2->second.back()] = true;
    it2->second.pop_back();
    ++sz;
  }
/*  for (auto & e : cnt) {
    cerr << "    " << e.first << endl;
    for (auto & f : e.second) {
      cerr << f << ' ';
    }
    cerr << '\n';
  }*/
  for (int i = 62; i >= 0; --i) {
    long long a = 1LL << i;
    it1 = cnt.find(a);
    if (it1 == cnt.end()) {
      continue;
    }
    if (!nulls.empty() && it1->second.size() % 2 == 1) {
      x[sz] = nulls.back();
      y[sz] = it1->second.back();
      nulls.pop_back();
      it1->second.pop_back();
      ++sz;
    }  
  }
  for (int i = 62; i >= 0; --i) {
    long long a = 1LL << i;
    it1 = cnt.find(a);
    if (it1 == cnt.end()) {
      continue;
    }
    while (!it1->second.empty() && !nulls.empty()) {
      x[sz] = nulls.back();
      y[sz] = it1->second.back();
      nulls.pop_back();
      it1->second.pop_back();
      ++sz;
    }
    while ((int)it1->second.size() > 1) {
      x[sz] = it1->second.back();
      it1->second.pop_back();
      y[sz] = it1->second.back();
      it1->second.pop_back();
      ++sz;
    }
  }
  printf("%d\n", sz);
  for (int i = 0; i < sz; ++i) {
    printf("%d %d\n", x[i], y[i]);
  }  
  return 0;
}