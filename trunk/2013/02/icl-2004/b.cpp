#include <cstdio>
#include <algorithm>

using namespace std;

int ni() {
    int c = getchar();
    while (c < '0' || c > '9') {
        c = getchar();
    }
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return ret;
}

int a[5555];

int main() {
    int n = ni();
    for (int i = 0; i < n; i++) {
        a[i] = ni();
    }     
    sort(a, a + n);
    int k = 0;
    while ((k + 2) * (k + 1) / 2 <= n) ++k;
    int cur = 0;
    for (int i = 0; i < k; i++) {
        printf("%d", a[n - k + i]);
        for (int j = 0; j < i; j++) {
            printf(" %d", a[cur]);
            ++cur; 
        }
        printf("\n");
    }
}