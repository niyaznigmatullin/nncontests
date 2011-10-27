#include <iostream>
#include <stdio.h>
#include <string>
using namespace std;
string s;
bool a[4000][4000];
int dp[4010],p[4010];

int printt(int x) {
    if (x==0) {
        return 0;
    } else printt(p[x]);
    cout << s.substr(p[x],x-p[x]) << " ";
    return 0;
}

int main() {
//    freopen("input.txt","r",stdin);
//    freopen("output.txt","w",stdout);
    cin >> s;
    int n=s.size(),k;
    memset(a,false,sizeof(a));
    for (int i=0; i<n; i++) {
        a[i][1]=true;
        k=1;
        while (i-k>=0 && i+k<n && s[i-k]==s[i+k]) {
            a[i-k][k+k+1]=true;
            k++;
        }
    }
    for (int i=0; i<n-1; i++) if (s[i]==s[i+1]) {
        a[i][2]=true;
        k=1;
        while (i-k>=0 && i+k+1<n && s[i-k]==s[i+k+1]) {
            a[i-k][k+k+2]=true;
            k++;
        }
    }
    memset(dp,-1,sizeof(dp));
    dp[0]=0;
    for (int i=0; i<n; i++) if (dp[i]!=-1) {
        for (int j=1; j<=n; j++) if (a[i][j]) if (dp[i+j]==-1 || dp[i]+1<dp[i+j]) {
            dp[i+j]=dp[i]+1;
            p[i+j]=i;
        }
    }
    cout << dp[n] << "\n";
    printt(n);
    return 0;
}
