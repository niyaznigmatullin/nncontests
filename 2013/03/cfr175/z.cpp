#include <iostream>
#include <algorithm>

using namespace std;


int main() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    sort(a, a + n);
    long long ans = 0;
    for (int i = 0; i < n; i++) ans += std::abs(a[i] - (i + 1));
    cout << ans << endl;
}