#include <iostream>
#include <string>

using namespace std;
int n,m;
int main() {
    cin >> n >> m;
    int x;
    for (int i=0; i<10; i++) {
        cin >> x;
        m-=x*20;
    }
    if (m<n) cout << "Dirty debug :("; else cout << "No chance.";
    return 0;
}
