#include <cstdio>

int main() {
    int n;
    scanf("%d", &n);
    if (n & 1) {
        if (n == 1) puts("0"); else
        printf("%d\n", n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) putchar(' ');
                if (i == j) putchar('0'); else {
                    int d = (i + j) % n + 1;
                    printf("%d", d);
                }
            }
            puts("");
        }
    } else {
        printf("%d\n", n - 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) putchar(' ');
                if (i == j) putchar('0'); else {
                    int d = (i + j) % (n - 1) + 1;
                    if (i == n - 1 || j == n - 1) {
                        d = (i + i + j + j) - (n - 1) * 2;
                        d = d % (n - 1) + 1;
                    }
                    printf("%d", d);
                }
            }
            puts("");
        }    
    }
}