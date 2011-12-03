#include <stdio.h>
#include <iostream>

using namespace std;

typedef unsigned long long ulong;
typedef unsigned int uint;
typedef uint** Matrix;

const ulong MOD = 4294967143LL;
const int ROWS = 11;

/*class Matrix {
    public:
    int n;
    ulong ** a;

    Matrix(int n_ = ROWS) : n(n_) {        
        a = new ulong*[n];
        for (int i = 0; i < n; i++) {
            a[i] = new ulong[n];
            for (int j = 0; j < n; j++) {
                a[i][j] = 0;
            }
        }
    }

    ulong * operator[] (size_t x) {
        return a[x];
    }

    ulong * operator[] (size_t x) const {
        return a[x];
    }
};*/

Matrix * pows;
Matrix tmp;
Matrix ans;

void mul(Matrix const & a, Matrix const & b, Matrix & c);

void mul2(Matrix const & a, Matrix const & b, Matrix & c) {    
    uint * aa;
    uint * bb;
    ulong v;
    for (int i = 0; i < ROWS; i++) {
        aa = a[i];
        for (int j = 0; j < ROWS; j++) {
            bb = b[j];
            v = 0;
            for (int k = 0; k < ROWS; k++) {
                v += (ulong) aa[k] * bb[k];
                if (v >= MOD) {
                    v %= MOD;
                }
            }
            c[i][j] = (uint)v;
        }
    }    
}

ulong go(ulong n) {
    if (n == 1) {
        return 10;
    }
    --n;
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < ROWS; j++) {
            ans[i][j] = i == j ? 1 : 0;
        }
    }
    for (int i = 0; i < 64; i++) {
        if (((n >> i) & 1) == 1) {
            mul2(ans, pows[i + 1], tmp);
            Matrix e = ans;
            ans = tmp;
            tmp = e;
        }
    }
    ulong ret = 0;
    for (int i = 1; i < ROWS; i++) {
        ret += ans[i][i - 1];
        if (ret >= MOD) {
            ret -= MOD;
        }
        if (i < ROWS - 1) {
            ret += ans[i][i + 1];
            if (ret >= MOD) {
                ret -= MOD;
            }
        }
    }
    return ret;
}

void mul(Matrix const & a, Matrix const & b, Matrix & c) {    
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < ROWS; j++) {
            ulong v = 0;
            for (int k = 0; k < ROWS; k++) {
                v += (ulong) a[i][k] * b[k][j];
                if (v >= MOD) {
                    v %= MOD;
                }
            }
            c[i][j] = (uint)v;
        }
    }    
}

int main() {
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    pows = new Matrix[66];
    for (int i = 0; i < 66; i++) {
        pows[i] = new uint*[ROWS];
        for (int j = 0; j < ROWS; j++) {
            pows[i][j] = new uint[ROWS];
            for (int k = 0; k < ROWS; k++) {
                pows[i][j][k] = 0;
            }
        }
    }
    tmp = new uint*[ROWS];
    ans = new uint*[ROWS];
    for (int i = 0; i < ROWS; i++) {
        tmp[i] = new uint[ROWS];
        ans[i] = new uint[ROWS];
    } 
    for (int i = 0; i < ROWS; i++) {
        pows[0][i][i] = 1;
    }
    for (int i = 0; i < ROWS; i++) {
        if (i > 0) {
            pows[1][i][i - 1] = 1;
        }
        if (i < ROWS - 1) {
            pows[1][i][i + 1] = 1;
        }
    }
    for (int i = 2; i < 66; i++) {
        mul(pows[i - 1], pows[i - 1], pows[i]);
    }
    for (int i = 0; i < 66; i++) {
        for (int j = 0; j < ROWS; j++) {
            for (int k = 0; k < ROWS; k++) {
                if (j != k) {
                    uint & a = pows[i][j][k];
                    uint & b = pows[i][k][j];
                    a ^= b ^= a ^= b;
                }
            }
        }
    }
/*    for (int i = 0; i < 64; i++) {
        printf("Matrix #%d\n", i);
        for (int j = 0; j < pows[i].n; j++) {
            for (int k = 0; k < pows[i].n; k++) {
                printf("%lld\t", pows[i][j][k]);
            }
            printf("\n");
        }
        printf("\n");
    }    */
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        ulong x;
        scanf("%lld", &x);
        printf("%lld\n", go(x));
    }
    return 0;
}
