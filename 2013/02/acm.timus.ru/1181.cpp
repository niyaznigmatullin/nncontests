#include <cstdio>
#include <cassert>


int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    int sg = c == '-';
    if (sg) c = getchar();
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}


int a[1111], id[1111], cnt[111], n;

int main() {
    n = ni();
    int c = getchar();
    while (c != 'R' && c != 'G' && c != 'B') {
        c = getchar();
    }
    for (int i = 0; i < n; i++) {
        a[i] = c;
        cnt[c]++;
        c = getchar();
        id[i] = i;
    }
    printf("%d\n", n - 3);
    while (n > 3) {
        int rem = -1;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            if (cnt[a[id[j]]] == 1) continue;
            int k = (i + 2) % n;
            if (a[id[i]] != a[id[k]]) {
                printf("%d %d\n", id[i] + 1, id[k] + 1);
                rem = j;
                cnt[a[id[j]]]--;
                break;
            }
        }
        while (rem < 0);
        for (int i = rem; i + 1 < n; i++) {
            id[i] = id[i + 1];
        }
        --n;
    }
}
