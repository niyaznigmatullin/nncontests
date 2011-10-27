#include <iostream>
#include <stdio.h>
using namespace std;

int base=1000000;
class TLong{
    public:
    int l;
    long long a[100];
    TLong() { memset(a,0,sizeof(0)); l=0; }
    void norm() {
        for (int i=0; i<=l; i++) {
            if (a[i]>base) {
                a[i+1]+=a[i]/base;
                a[i]%=base;
                if (i==l) l++;
            }
        }
    }
    void operator +=(TLong b) {
        if (l<b.l) l=b.l;
        for (int i=0; i<=l; i++) {
            a[i]+=b.a[i];
        }
        norm();
    }
    void clear() {
        l=0;
        memset(a,0,sizeof(a));
    }
    void operator *=(long long k) {
        for (int i=0; i<=l; i++) a[i]*=k;
        norm();
    }
    void operator *=(TLong b) {
        TLong c;
        c.clear();
        for (int i=0; i<=l; i++) {
            for (int j=0; j<=b.l; j++) {
                c.a[i+j]+=a[i]*b.a[j];
                c.l=max(i+j,c.l);
            }
            c.norm();
        }
        l=c.l;
        for (int i=0; i<=l; i++) a[i]=c.a[i];
    }
    void operator =(long long k) {
        clear();
        a[0]=k;
    }
    void operator =(TLong b) {
        clear();
        l=b.l;
        for (int i=0; i<=l; i++) a[i]=b.a[i];
    }
};

ostream& operator << (ostream& out, TLong a) {
    out << a.a[a.l];
    for (int i=a.l-1; i>=0; i--) {
        int p=base/10;
        while (p>a.a[i] && p>1) {
            out << 0;
            p/=10;
        }
        out << a.a[i];
    }
    return out;
}
TLong a[100],h;
int n,k;
int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n >> k;
    a[0]=1;
    a[1]=k;
    for (int i=2; i<=n; i++) {
        //a[i]=a[i-1];
        //a[i]*=k;
        a[i].clear();
        for (int j=0; j<i; j++) {
            h=a[j];
            h*=k;
            h*=a[i-j-1];
            a[i]+=h;
        }
    }
    cout << a[n];
	return 0;
}
