
int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) {
        c = getchar();
    }
    int sg = 0;
    if (c == '-') {
        sg = 1;
        c = getchar();
    }
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

int ss[111111], ff[111111], w[111111], he[111111], ne[111111], pd[111111];

int get(int x) {
    return x == pd[x] ? x : (pd[x] = get(pd[x]));
}

void un(int x, int y) {
    pd[get(x)] = get(y);
}


int main() {
    int n = ni();
    int m = ni();
    int k = ni();
    int maxw = 0;
    for (int i = 0; i < m; i++) {
        ss[i] = ni() - 1;
        ff[i] = ni() - 1;
        w[i] = ni();
        if (maxw < w[i]) maxw = w[i];
    }
    ++maxw;
    for (int i = 0; i < maxw; i++) {
        he[i] = -1;
    }
    for (int i = 0; i < m; i++) {
        ne[i] = he[w[i]];
        he[w[i]] = i;
    }
    
}