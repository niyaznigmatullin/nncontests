#include <cstdio>
#include <cassert>

int n, ss[4444], ff[4444], s[4444];
char z[4444];

int solve(char a, char b) {
    for (int i = 0; i <= n; i++) z[i] = 0;
    int ca = 0;
    int cb = 0;
    z[0] = s[0];
    if (s[0] == a) ++ca; else ++cb;
    ss[0] = -1;
    ff[0] = -1;
    for (int i = 1; i < n; i += 2) {
        int c = s[i];
        int d = s[i + 1];
        if (c == b && d == b && (ca & 1) == 1 && ca >= 5 && cb != 0) {
            return 0;
        }
        z[i] = c;
        z[i + 1] = d;
        if (c == a && d == a) {
            if ((ca & 1) == 1 && i != 1) {
                ss[i] = i / 2;
                ff[i] = i / 2 + 1;
            } else {
                ss[i] = -1;
                ff[i] = -1;                
            }
            ss[i + 1] = ca / 2;
            ff[i + 1] = i - ca / 2;  
        } else if (c == b && d == b) {
            if (ca == 1) {
                ss[i + 1] = i / 2;
                ff[i + 1] = i / 2 + 1;
                ss[i] = -1;
                ff[i] = -1;       
            } else if (ca == 3) {
                if (cb == 0) {
                    ss[i + 1] = 1;
                    ff[i + 1] = 4;
                    ss[i] = ff[i] = -1;
                } else {
                    ss[i] = i / 2;
                    ff[i] = i / 2 + 1;
                    ss[i + 1] = i - 1;
                    ff[i + 1] = i + 1;
                }
            } else if (cb == 0) { 
                ss[i] = i / 2;
                ff[i] = i;
                ss[i + 1] = i / 2 + 2;
                ff[i + 1] = i + 1;
            } else {
                ss[i] = i - ca / 2;
                ff[i] = i;
                ss[i + 1] = i - ca / 2 + 1;
                ff[i + 1] = i + 1;
            }
        } else {
            if (c == a && d == b) {
                if (ca == 0) {
                    ss[i + 1] = i / 2 + 1;
                    ff[i + 1] = i;
                    ff[i] = ss[i] = -1;                    
                } else if ((ca & 1) == 1) {
                    ss[i] = i / 2;
                    ff[i] = ca / 2; 
                    ss[i + 1] = i + 1;
                    ff[i + 1] = i - ca / 2;
                } else {
                    ss[i] = i / 2 + 1;
                    ff[i] = i - ca / 2;
                    ss[i + 1] = i + 1;
                    ff[i + 1] = i - ca / 2 + 1;
                }
            } else {
                if (ca == 0) {
                    ss[i] = ff[i] = -1;
                    ss[i + 1] = i / 2 + 1;
                    ff[i + 1] = i + 1;
                } else if ((ca & 1) == 1) {
                    ss[i] = i / 2;
                    ff[i] = ca / 2; 
                    ss[i + 1] = i;
                    ff[i + 1] = i - ca / 2;
                } else {
                    ss[i] = i / 2 + 1;
                    ff[i] = i - ca / 2;                    
                    ss[i + 1] = i;
                    ff[i + 1] = i - ca / 2 + 1;
                }
            }
        }
        for (int k = i; k <= i + 1; k++) {
            if (ss[k] == ff[k] || z[ss[k]] == z[ff[k]]) {
                ss[k] = ff[k] = -1;
                continue;
            }
            if (ss[k] > ff[k]) {
                int tt = ss[k];
                ss[k] = ff[k];
                ff[k] = tt;
            }
            assert(ss[k] <= k && ff[k] <= k);
            char tmp = z[ss[k]];
            z[ss[k]] = z[ff[k]];
            z[ff[k]] = tmp;            
        }
        if (c == a) ++ca; else ++cb;
        if (d == a) ++ca; else ++cb;
        for (int j = 0; j < i + 2; j++) assert(z[j] == z[i + 1 - j]);
        puts(z);
    }
    puts("Qc");
    for (int i = 0; i < n; i++) {
        printf("%d %d\n", ss[i] + 1, ff[i] + 1);
    }
    return 1;
}

int main() {
    scanf("%d", &n);
    n = 2 * n - 1;
    for (int i = 0; i < n; i++) {
        int c = getchar();
        while (c != 'Q' && c != 'C') c = getchar();
        s[i] = c;
    }   
    if (solve('Q', 'C')) return 0;
    if (solve('C', 'Q')) return 0;
    puts("He");
}
