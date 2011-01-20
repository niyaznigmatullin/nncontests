#include <iostream>
#include <set>
#include <vector>
#include <list>
using namespace std;

struct TMan{
 int dif,a,b,n;
};
struct comart{
 bool operator() (const TMan& x, const TMan& y) const {
  return (x.dif<y.dif || (x.dif==y.dif && x.n<y.n));
 }
};
int n,m,p;
vector <int> a,b,c,d;
vector <vector <int> > e,f;
set <TMan,comart> s;
set <TMan,comart> t;

int main() {
    freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m >> p;
	for (int i=0; i<=p; i++) {
		f.push_back(vector <int> (0));
		e.push_back(vector <int> (0));
	}
	int aa,bb,cc,dd;
	for (int i=0; i<n; i++) {
		cin >> aa >> bb >> cc >> dd;
		a.push_back(aa);
		b.push_back(bb);
		c.push_back(cc);
		d.push_back(dd);
		e[cc].push_back(i);
		f[dd].push_back(i);
	}
	TMan x;
	set <TMan,comart>::iterator it;
	long long sum2=0,sum1=0,q=0;
	for (int i=1; i<=p; i++) {
		for (int j=0; j<(int)f[i].size(); j++) {
			x.dif=a[f[i][j]]-b[f[i][j]];
			x.a=a[f[i][j]];
			x.b=b[f[i][j]];
			x.n=f[i][j];
			it=s.find(x);
			if (it==s.end()) {
				it=t.find(x);
				sum2-=it->b;
				t.erase(it);
				continue;
			}
			sum1-=it->a;
			s.erase(it);
		}
		for (int j=0; j<e[i].size(); j++) {
			x.dif=a[e[i][j]]-b[e[i][j]];
			x.a=a[e[i][j]];
			x.b=b[e[i][j]];
			x.n=e[i][j];
			if (x.dif<=0 || (!t.empty() && x.dif<t.rbegin()->dif)) {
				t.insert(x);
				sum2+=x.b;
			} else {
				s.insert(x);
				sum1+=x.a;
			}
		}
		while (s.size()>m) {
			it=s.begin();
			sum2+=it->b;
			sum1-=it->a;
			t.insert(*it);
			s.erase(it);
		}
		while (s.size()<m && !t.empty())
		if (t.rbegin()->dif>0) {
			it=t.end();
			--it;
			sum2-=it->b;
			sum1+=it->a;
			s.insert(*it);
			t.erase(it);
		} else break;
		q+=sum1+sum2;
	}
	cout << q;
	return 0;
}
