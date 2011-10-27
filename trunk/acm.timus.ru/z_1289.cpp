#include <iostream>
#include <string>
#include <vector>

using namespace std;

int base = 1000000;
class TLong{
public:
	long long a[1000];
	int l;
	void norm() {
		for (int i=0; i<l; i++) {
			if (a[i]>base) {
				a[i+1]+=a[i]/base;
				a[i]%=base;
				if (i==l-1) l++;
			}
			if (a[i]<0) {
				a[i+1]-=-a[i]/base+1;
				a[i]+=(-a[i]/base+1)*base;
			}
		}
	}
	TLong() {
		l=0;
		memset(a,0,sizeof(a));
	}
	void operator =(const TLong& b) {
		l=b.l;
		for (int i=0; i<l; i++) a[i]=b.a[i];
	}
	TLong operator -(const TLong& c) {
		TLong b;
		b=*this;
		for (int i=0; i<l; i++) b.a[i]-=c.a[i];
		b.norm();
		return b;
	}
	TLong operator *(int v) {
		TLong c;
		c=*this;
		for (int i=0; i<l; i++) c.a[i]*=v;
		c.norm();
		return c;
	}
	bool operator ==(const TLong& b) {
		if (l!=b.l) return false; else {
			for (int i=0; i<l; i++) if (a[i]!=b.a[i]) return false;
		}
		return true;
	}
	void operator /=(int v) {
		int um=0;
		for (int i=l-1; i>=0; i--) {
			um=(um*base)+a[i];
			a[i]=um/v;
			um%=v;
		}
		while (a[l-1]==0) l--;
	}
	void operator -=(const TLong& b) {
		for (int i=0; i<l; i++) a[i]-=b.a[i];
		norm();
	}
	void operator +=(const TLong& b) {
		if (l<b.l) l=b.l;
		for (int i=0; i<l; i++) a[i]+=b.a[i];
		norm();
	}
	bool operator >(const TLong& b) {
		if (l>b.l) return true; else 
			if (l<b.l) return false; else {
				for (int i=l-1; i>=0; i++) if (a[i]>b.a[i]) return true; else
					if (a[i]<b.a[i]) return false;
			}
		return false;
	}
	bool odd() {
		return a[0]%2!=0;
	}
	bool is_zero() {
		return (l<=1 && a[0]==0);
	}
	TLong operator *(const TLong& b) {
		TLong c;
		memset(c.a,0,sizeof(c.a));
		c.l=0;
		for (int i=0; i<l; i++) {
			for (int j=0; j<b.l; j++) {
				c.a[i+j]+=a[i]*b.a[j];
				c.l=max(c.l,i+j+1);
			}
			c.norm();
		}
		return c;
	}
	void operator *=(int v) {
		for (int i=0; i<l; i++) a[i]*=v;
		norm();
	}
	void operator =(string s) {
		l=1;
		int p=0,r=1;
		for (int i=s.size()-1; i>=0; i--) {
			p=p+r*(s[i]-'0');
			r*=10;
			if (r>=base) {
				a[l-1]=p;
				p=0;
				r=1;
				l++;
			}
		}
		if (p!=0) a[l-1]=p, l++;
		while (a[l-1]==0) l--;
	}
	void operator =(int v) {
		l=1;
		a[0]=v;
	}
};

ostream& operator << (ostream& out, const TLong& a) {
	if (a.l == 0) {
		out << 0;
		return out;
	}
	out << a.a[a.l-1];
	for (int i=a.l-2; i>=0; i--) {
		for (int p=base/10; p>1 && a.a[i]<p; p/=10) {
			out << 0;
		}
		out << a.a[i];
	}
	return out;
}
TLong dp[20],ans;
int n;

int main() {
    cin >> n;
	/*memset(dp, 0, sizeof(dp));
	dp[0] = 1;
	for (int i=1; i<=n/2; i++) {
		for (int j=18; j>=0; j--) {			
			for (int t=1; t<=9; t++) if (j-t>=0 && j-t<=9) {
				dp[j] += dp[j - t];
			}
		}
		for (int j=10; j<=18; j++) {
			dp[j - 9] += dp[j];
			dp[j] = 0;
		}		
	}
	for (int i=0; i<=9; i++) {
		ans += dp[i] * dp[i];
	}
	cout << ans;*/
	if(n == 2){
		printf("10");
		return 0;
	}
	for(int i=0; i<n/2-1; i++) {
		printf("1");
	}
	printf("0");
	for (int i=0; i<n/2-2; i++) {
		printf("8");
	}
	printf("90");
    return 0;
}
