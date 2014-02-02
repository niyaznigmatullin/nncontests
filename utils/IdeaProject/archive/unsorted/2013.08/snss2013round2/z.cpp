#pragma comment(linker, "/STACK:100000000000000")
#include <stdio.h>
#include <vector>
#include <map>
#include <string>
#include <algorithm>
#include <set>
#include <stack>
#include <queue>
#include <math.h>
#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <string.h>
#include <cctype>
#include <cassert>
#include <ctime>
#define mp make_pair
#define pb push_back
#define lo long long int
#define pi 3.14159265358979323
#define eps 1e-4
#define MN (2500 )
using namespace std;
const lo INF= 2e18;
lo dp[1002][MN][12];
bool ch[MN][12];
vector<lo> gg[MN][12];
inline void cnt_go(lo a, lo wh, lo m)
{
        if(ch[a][wh])
                return;
        ch[a][wh] = true;
        if(wh == m - 1)
        {
                lo te = (a & (((1LL << (m + 1)) - 1 - (1LL << m))));
                gg[a][wh].push_back(te * 2);
                gg[a][wh].push_back(te * 2 + 1);
                return;
        }
        lo f[3] = {-1, -1, -1};
        for(int i = 0; i < 3; i++)
        {
                f[i] = ((a & (1LL << (wh + i))) > 0LL);
        }
    lo te = (a & ((1LL << (m + 1)) - 1 - (1LL << (wh + 1))));
        if(!(f[0] == f[1] && f[1] == f[2] && f[2] == 0)) gg[a][wh].push_back(te);
        if(!(f[0] == f[1] && f[1] == f[2] && f[2] == 1)) gg[a][wh].push_back(te | (1LL << (wh + 1)));
}
inline int take(lo vv, lo i)
{
        return ((vv & (1LL << i)) >> i);
}
inline bool is(lo fr, lo to, lo m)
{
        if((fr & (1LL << 0)) != (to & (1LL << 0))) return false;
        fr /= 2;
        for(int i = 0; i < m - 1; i++)
        {
                if(take(fr, i) == take(fr, i + 1) && take(to, i) == take(to , i+ 1)
                        && take(fr, i) == take(to, i))
                return false;
        }
        return true;
}
lo ma[3000];
int main()
{
        assert((freopen("makeintihappy.in", "r", stdin))!= NULL);
    assert((freopen("makeintihappy.out", "w", stdout))!= NULL);
        lo n, m, k;
        cin >> n >> m >> k;
        lo det = 0;
        det++;
        det++;
        det++;
        if(m == 1 || n == 1)
        {
                k--;
                lo with = (max(n, m) <= 62 ? (1LL << max(n, m)) : INF);
                if(k >= with)cout << "Impossible";
                else
                {
                        string s = "";
                        for(lo i = 0; i < max(n, m); i++)
                        {
                                if(i <= 62 &&  (k & (1LL << i))) s ='b' + s;
                                else s = 'w' + s;
                        }
                        if(m == 1)
                        cout << s;
                        else
                                for(lo i = 0; i < (lo) s.size(); i++)
                                {
                                        cout << s[i] << endl;
                                }
                }
                return 0;
        }
        memset(dp, 0, sizeof dp);
        memset(ma, 0, sizeof ma);
        for(int i = 0; i < (1LL << (m + 1)); i++)
        {
                dp[1][i][0] = 1;
        }
        for(int i = 1; i < n; i++)
        {
                for(int j = 0; j < m; j++)
                {
                        for(lo k = 0; k < (1LL << (m + 1)); k++)
                        {
                                cnt_go(k, j, m);
                                for(int d = 0; d < (lo) gg[k][j].size(); d++)
                                {
                                        lo to = gg[k][j][d];
                                        dp[i + (j + 1) /m][to][(j + 1) % m] += dp[i][k][j];
                                        dp[i + (j + 1) /m][to][(j + 1) % m] = min(dp[i + (j + 1) /m][to][(j + 1) % m], INF);
                                }
                        }
                }
        }
        lo cur = -1;
        for(int i = 0; i < (1LL << m); i++)
        {
                lo sum = dp[n - 1][i][m - 1] +  dp[n - 1][i + (1LL << m)][m - 1];
                if(k <= sum)
                {
                        cur = i;
                        break;
                }
                k -= sum;
        }
        if(cur == -1)
        {
                cout << "Impossible";
                return 0;
        }
        ma[n - 1] = cur;
        for(int i = n - 1; i >= 1; i--)
        {
                for(lo j = 0; j < (1LL << (m + 1)); j++)
                {
                        if(!is(j, cur, m))
                                continue;
                        if(k <= dp[i][j][0])
                        {
                                ma[i - 1] = cur = j / 2;
                                break;
                        }
                        k -= dp[i][j][0];
                }
        }
        for(int j = m - 1; j >= 0; j--)
        {
           for(int i = 0; i < n; i++)
           {
                     lo temp = ma[n - 1 - i];
                  printf((temp & (1LL << j) ? "b" :"w"));
           }
           printf("\n");
        }
}
