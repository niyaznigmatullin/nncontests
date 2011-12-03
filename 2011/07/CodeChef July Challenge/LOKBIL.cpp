#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <sstream>
#include <cstdio>
using namespace std;
const int INTMAXVALUE = ~(1 << 31);

void solve() {
    int n;
    scanf("%d", &n);
    vector<vector<int> > edges(n);
    {
        string e;
        getline(cin, e);
    }
    for (int i = 0; i < n; i++) {
        string s;
        getline(cin, s);
        stringstream ss(s);
        int x;
        while (ss >> x) {
            edges[i].push_back(x - 1);         
        }
    }
    int minSum = INTMAXVALUE;
    int who = -1;
    for (int i = 0; i < n; i++) {
        queue<int> q;
        q.push(i);
        vector<bool> was(n, false);
        vector<int> d(n, INTMAXVALUE);
        d[i] = 0;
        was[i] = true;
        while (!q.empty()) {
            int v = q.front();
            q.pop();
            for (vector<int>::iterator it = edges[v].begin(); it != edges[v].end(); ++it) {
                int e = *it;
                if (was[e]) {
                    continue;
                }
                was[e] = true;
                d[e] = d[v] + 1;
                q.push(e);
            }            
        }
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += d[j];
        }
        if (sum < minSum) {
            minSum = sum;
            who = i;
        }
    }
    printf("%d %.6lf\n", who + 1, 1. * minSum / n);
}

int main() {
    int t;
    scanf("%d", &t);
    for (int i = 0; i < t; i++) {
        solve();
    }
}