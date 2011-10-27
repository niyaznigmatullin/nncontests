#include <iostream>
#include <vector>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#define pb push_back
#define all(a) a.begin(),a.end()
#define vi vector<int>
#define eps 1e-8
using namespace std;
vi pv,ps;
vector <double> v,s;
int n,k;
bool less_v(int x, int y) { return v[x]>v[y]; }
bool less_s(int x, int y) { return s[x]>s[y]; }

double calc_t() {
	vi p;
	for (int i=0; i<n; i++) {
		p.pb(i);
	}
	sort(all(p),less_s);
	double t=0,sums=0,sumv=0;
	for (int i=0; i<n; i++) {
		if (i<k) sumv+=v[pv[i]];
		sums+=max(0.0,s[p[i]]);
		if (t<sums/sumv) t=sums/sumv;
	}
	return t;
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	double x;
	for (int i=0; i<n; i++) {
		cin >> x;
		s.pb(x);
		ps.pb(i);
	}
	cin >> k;
	for (int i=0; i<k; i++) {
		cin >> x;
		v.pb(x);
		pv.pb(i);
	}
	sort(all(pv),less_v);
	if (k>n) k=n;
	double t,curt=0;
	printf("%.20lf\n",calc_t());
	while ((t=calc_t())>eps) {
		double mint=0,maxt=t;
		sort(all(ps),less_s);
		for (int tt=0; tt<60; tt++) {
			double mid=(mint+maxt)*0.5;
			for (int i=0; i<k; i++) s[ps[i]]-=v[pv[i]]*mid;
			if ((mid+calc_t())<t+eps) mint=mid; else maxt=mid;
			for (int i=0; i<k; i++) s[ps[i]]+=v[pv[i]]*mid;
		}
		double mid=mint;
		for (int i=0; i<k; i++) s[ps[i]]-=v[pv[i]]*mid;
		for (int i=0; i<k; i++) printf("%.20lf %d %d\n",curt,ps[i]+1,pv[i]+1);
		curt+=mid;
	}
	return 0;
}
