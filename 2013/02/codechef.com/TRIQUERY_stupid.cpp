#include <iostream>

using namespace std;
int x[333333], y[333333];
int main() {
    int n, q;
    cin >> n >> q;
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
    }
    for (int i = 0; i < q; i++) {
        int ans = 0;
        int cx, cy, d;
        cin >> cx >> cy >> d;
        for (int j = 0; j < n; j++) {
            if (x[j] + y[j] <= cx + cy + d && x[j] >= cx && y[j] >= cy) ++ans;
        }
        cout << ans << "\n";
    }
}