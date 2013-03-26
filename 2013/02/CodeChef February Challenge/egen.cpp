#include <cstdio>
#include <ctime>
#include <cstdlib>

int main() {
    int n = 50;
    int z = 4000 + rand() % 2000;
    printf("%d\n", n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j< n; j++) {
            if (rand() % 10000 < z) {
                putchar('B');
            } else {
                putchar('W');
            }
        }
        putchar('\n');
    }
}