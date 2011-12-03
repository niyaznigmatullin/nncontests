#include <set>
#include <memory.h>
#include <vector>
#include <cstdio>
#include <iostream>
#include <algorithm>

using namespace std;

const int MOD = 1000000007;

int f(int, int, int);

int g(int, int, int);

int dp1[101][51][1001];
int dp2[101][51][1001];

int f(int d, int a, int v) 
{
    if (v < 0) 
    {
        return 0;
    }
    int & ret = dp1[d][a][v];
    if (ret != -1) 
    {
        return ret;
    }
    int d1 = d >> 1;
    int d2 = (d >> 1) + (d & 1);
    ret = 0;
    for (int i = 0; i <= a; i++) 
    {       
        long long e1 = f(d1, i, v - a);
        long long f2 = g(d2, a - i, v - a);
        long long f1 = g(d1, i, v - a);
        long long e2 = f(d2, a - i, v - a);
        ret = (int) ((((e1 * f2 + e2 * f1 - e1 * e2 + ret) % MOD) + MOD) % MOD);
        
    }
//    cerr << "f[" << d << "][" << a << "][" << v << "] = " << ret << "\n";
    return ret;
}

int g(int d, int a, int v)
{
    if (v < 0) 
    {
        return 0;
    }
    int & ret = dp2[d][a][v];
    if (ret != -1) 
    {
        return ret;
    }
    if (v == 0) 
    {
        return ret = f(d, a, 0);
    }
    else
    {
        ret = f(d, a, v) + g(d, a, v - 1);
        if (ret >= MOD) 
        {
            ret -= MOD;
        }
        return ret;
    }
}

void solve(int a, int e, int v) 
{
    if (a + e == 0) 
    {
        if (v == 0) 
        {
            printf("1\n");
        }
        else
        {
            printf("0\n");
        }
        return;
    }
    printf("%d\n", f(a + e, a, v));
}

int main() 
{
    for (size_t i = 0; i <= 100; i++) 
    {
        for (int j = 0; j <= 50; j++)
        {
            memset(dp1[i][j], i == 0 ? 0 : -1, (1000 + 1) * (sizeof(int)));
            memset(dp2[i][j], i == 0 ? 0 : -1, (1000 + 1) * (sizeof(int)));
        }
    }
    dp1[1][0][0] = 1;
    dp1[1][1][1] = 1;
    for (int i = 0; i <= 1000; i++)
    {
        dp2[1][0][i] = 1;
        if (i > 0)
        {
            dp2[1][1][i] = 1;
        }
    }
    int t;
    scanf("%d", &t);
    for (int i = 0; i < t; i++) 
    {
        int a, e, v;
        scanf("%d%d%d", &a, &e, &v);
        solve(a, e, v);
    }
}