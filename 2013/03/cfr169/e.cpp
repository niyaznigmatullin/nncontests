#include <cstdio>


const int MAXN = 222222;
const int MAXF = 222222;
int ss[MAXN], ff[MAXN], md[MAXN], vv[MAXN], id[MAXN], de[MAXN];
int fcaca[MAXN * 10];
int * f[MAXN];
int * allf;

int getSum(int * f, int m, int x) {
    int ret = 0;
    for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
        ret += f[i];
    }
    return ret;
}

void add2(int * f, int m, int x, int y) {
    for (int i = x; i < m; i |= i + 1) {
        f[i] += y;   
    }
}

void addValue(int * f, int m, int x, int y) {
    for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
        f[i] += y;
    }
}

int getValue(int * f, int m, int x) {
    int ret = 0;
    for (int i = x; i < m; i |= i + 1) {
        ret += f[i];   
    }
    return ret;
}



int main() {
    int n, q;
    scanf("%d %d", &n, &q);
    for (int i = 0; i <= n; i++) ss[i] = ff[i] = -1;
    int cf = 0;
    for (int i = 0; i + 1 < n; i++) {
        int v, u;
        scanf("%d %d", &v, &u);
        if (v != 1) {
            if (ss[v] >= 0) ff[v] = u; else ss[v] = u;   
        } else {
            vv[cf++] = u;
        }
        if (u != 1) {
            if (ss[u] >= 0) ff[u] = v; else ss[u] = v;
        } else {
            vv[cf++] = v;   
        }
    }
    int * fca = fcaca;
    allf = fca;
    fca = fca + (2 * MAXF);
    for (int it = 0; it < cf; it++) {
        int i = vv[it];
        int last = 1;
        int cnt = 1;
        while (i >= 0) {
            id[i] = it;
            de[i] = cnt++;
            int j = ss[i] ^ ff[i] ^ last;            
            last = i;
            i = j;
        }
        md[it] = cnt + 1;
        f[it] = fca;
        fca = fca + cnt + 1;
    }
    for (int i = 0; i < q; i++) {
        int type, v;
        scanf("%d %d", &type, &v);
        if (type == 0) {
            int add, d;
            scanf("%d %d", &add, &d);
            if (v == 1) {
                add2(allf, MAXF, MAXF - d - 1, add);
            } else if (de[v] > d) {
                addValue(f[id[v]], md[id[v]], de[v] - d - 1, -add);
                int till = de[v] + d;
                if (till > md[id[v]] - 1) till = md[id[v]] - 1;
                addValue(f[id[v]], md[id[v]], till, add);
            } else {
                addValue(f[id[v]], md[id[v]], 0, -add);
                int till = de[v] + d;
                if (till > md[id[v]] - 1) till = md[id[v]] - 1;
                addValue(f[id[v]], md[id[v]], till, add);
                add2(allf, MAXF, MAXF - (d - de[v]) - 1, add);
                till = d - de[v];
                if (till > md[id[v]] - 1) till = md[id[v]] - 1;
                addValue(f[id[v]], md[id[v]], till, -add);
            }
        } else {
            int ans = v == 1 ? 0 : getValue(f[id[v]], md[id[v]], de[v]);
            printf("%d\n", ans + getSum(allf, MAXF, MAXF - de[v] - 1));
        }
    }
}