#include <string>
#include <cstdio>

#define min(x, y) ((x) > (y) ? (y) : (x))

int sl[444444], len[444444], lk[26][444444], term[444444], fr;

int newNode(int l) {
    len[fr] = l;
    for (int i = 0; i < 26; i++) lk[i][fr] = -1;
    sl[fr] = -1;
    return fr++;
}

int copyNode(int v, int l) {
    len[fr] = l;
    sl[fr] = sl[v];
    for (int i = 0; i < 26; i++) lk[i][fr] = lk[i][v];
    return fr++;
}

int append(int v, int c) {
    int n = newNode(len[v] + 1);
    while (v >= 0 && lk[c][v] < 0) {
        lk[c][v] = n;
        v = sl[v];
    }
    if (v < 0) {
        sl[n] = 0;
        return n;
    }
    int q = lk[c][v];
    if (len[q] == len[v] + 1) {
        sl[n] = q;
        return n;
    }
    int cp = copyNode(q, len[v] + 1);
    while (v >= 0 && lk[c][v] == q) {
        lk[c][v] = cp;
        v = sl[v];
    }
    sl[q] = sl[n] = cp;
    return n;
}

int s[222222], t[222222], a[444444];
int d[444444];

inline int isa(int c) {
    return c >= 'a' && c <= 'z';
}

void rev(int * a, int len) {
    for (int i = 0, j = len - 1; i < j; i++, j--) {
        int z = a[i];
        a[i] = a[j];
        a[j] = z;
    }
}

int main() {
    int c = getchar();
    while (!isa(c)) c = getchar();
    int n = 0;
    while (isa(c)) {
        s[n++] = c - 'a';
        c = getchar();
    }
    while (!isa(c)) c = getchar();
    int m = 0;
    while (isa(c)) {
        t[m++] = c - 'a';
        c = getchar();
    }
    int changed = 0;
    if (n < m) {
        changed = 1;
        for (int i = 0; i < m; i++) {
            int tt = s[i];
            s[i] = t[i];
            t[i] = tt;
        }
        int tt = n;
        n = m;
        m = tt;
        rev(s, n);
        rev(t, m);
    }
    for (int i = 0; i < n; i++) s[i + n] = s[i];
    rev(t, m);
    for (int i = 0; i < m; i++) t[i + m] = t[i];
    fr = 0;
    int v = newNode(0);
    for (int i = 0; i < m + m; i++) {
        v = append(v, t[i]);
    }
    while (v != 0) {
        term[v] = 1;
        v = sl[v];
    }
    for (int i = 0; i < n + n; i++) {
        a[i + i] = -1;
        a[i + i + 1] = s[i];
    }
    a[n + n + n + n] = -1;
    int l = -1, r = -1;
    for (int i = 0; i <= 4 * n; i++) {
        d[i] = i > r ? 0 : min(r - i, d[l + r - i]);
        while (i - d[i] >= 0 && i + d[i] <= 4 * n && a[i + d[i]] == a[i - d[i]]) ++d[i];
        if (i + d[i] - 1 > r) {
            r = i + d[i] - 1;
            l = i - d[i] + 1;
        }
    }
    v = 0;
    for (int i = 0, j = 0; i < n; i++) {
        while (j - i < m && lk[s[j]][v] >= 0) {
            v = lk[s[j]][v];
            ++j;
        }
        if (j - i == m) {
            int le = n - m;
            int ll = 2 * j + 1;
            int rr = 2 * (j + le - 1) + 1;
            int mid = (ll + rr) / 2;
            if (d[mid] >= rr - mid + 1) {
                puts("Yes");
                v = 0;
                for (int k = i; k < j; k++) {
                    v = lk[s[k]][v];
                }
                int cnt = 0;
                while (!term[v]) {
                    for (int k = 0; k < 26; k++) {
                        if (lk[k][v] >= 0) {
                            v = lk[k][v];
                            break;
                        }
                    }
                    ++cnt;
                }
                cnt = cnt % m + 1;
                ++i;
                if (changed) printf("%d %d\n", (m - cnt + 1) % m + 1, (n - i + 1) % n + 1); else
                printf("%d %d\n", i, cnt);
                return 0;
            }
        }
        if (j == i) {
            ++j;
            continue;
        }
        if (len[sl[v]] < j - i - 1) {
            continue;
        }
        v = sl[v];
    }
    puts("No");
}