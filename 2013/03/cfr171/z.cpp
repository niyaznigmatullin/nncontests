#include <vector>
#include <cstdio>
#include <iostream>

using std::cin;
using std::endl;
using std::vector;
using std::cout;

long i, n, m;
long a[100005];
vector<long> ls[100005];

bool same(long l, long r) {
    long i, j;
    for (i = 0; i < ls[l].size(); i++) {
        for (j = 0; j < ls[r].size(); j++) {
            if (ls[l][i] == ls[r][j]) return true;
        }
    }
    return false;
}
    
int main() {
    cin >> n >> m;
    for (i = 0; i < n; i++) {
        cin >> a[i];
    }
    bool up = true;
    long ladder = 1;
    int op = 0;
    for (i = 0; i < n; i++) {
        ls[i].push_back(ladder);
        if (i == n - 1) break;
        if (a[i] < a[i + 1] && !up) {
            ++ladder;
            while (a[i] >= a[i - 1]) {
                --i;
                op++;
            }
            --i;
            up = true;
        } else if (a[i] > a[i + 1] && up) {
            up = false;
        }
    }
    int mm = 0;
    for (i = 0; i < n; i++) {
        if (mm < ls[i].size()) mm = ls[i].size();
    }
    printf("mm = %d, op = %d\n", mm, op);
    return 0;
    long l, r;
    for (i = 0; i < m; i++) {
        cin >> l >> r;
        cout << (same(l - 1, r - 1) ? "Yes" : "No") << endl;
    }
}