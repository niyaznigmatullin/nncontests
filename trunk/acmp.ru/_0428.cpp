#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#define sz size()
using namespace std;

string d[8]={"ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};
int c[8]={3,3,3,3,3,4,3,4};
int base=100000;

class TLong{
    public:
    int l;
    int a[50];
    void norm() {
        for (int i=0; i<l; i++) {
            if (a[i]>base) {
                a[i+1]+=a[i]/base;
                a[i]%=base;
                if (i==l-1) l++;
            }
        }
    }
    void operator=(int x) { memset(a,0,sizeof(a)); a[0]=x; l=1; }
    bool operator==(int x) { return (l<=1 && a[0]==x); }
    void operator+=(TLong b) {
        if (l<b.l) l=b.l;
        for (int i=0; i<l; i++) {
            a[i]+=b.a[i];
        }
        norm();
    }
};

ostream& operator << (ostream& out, TLong& a) {
    out << a.a[a.l-1];
    for (int i=a.l-2; i>=0; i--) {
        for (int p=base/10;p>1 && a.a[i]<p; p/=10) out << 0;
        out << a.a[i];
    }
    return out;
}
int n;
TLong dp[300][100];
string s;
int kol[256],wh[256],pul[500],k=0;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d\n",&n);
	getline(cin,s);
	for (int i=0; i<8; i++)
	for (int j=0; j<c[i]; j++) {
	    kol[(int)d[i][j]]=j+1;
	    wh[(int)d[i][j]]=i;
	}
	for (int i=0; i<(int)s.size(); i++) {
	    for (int j=0; j<kol[(int)s[i]]; j++) pul[k++]=wh[(int)s[i]];
	}
	memset(dp,0,sizeof(dp));
	dp[0][0]=1;
	for (int i=0; i<k; i++) {
	    for (int j=0; j<n; j++) if (!(dp[i][j]==0)){
	        for (int r=0; r<c[pul[i]] && pul[i+r]==pul[i]; r++) {
	            dp[i+r+1][j+1]+=dp[i][j];
	        }
	    }
	}
	cout << dp[k][n];
	return 0;
}
