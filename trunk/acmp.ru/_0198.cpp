#include <iostream>
#include <stdio.h>
#include <math.h>
#include <vector>
#define forn(i,j,n) for (int i=j; i<n; i++)
#define vd vector <double>
using namespace std;

int n;
double x;
vector<vd > a;
vd b;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	forn(i,0,n) {
		a.push_back(vd(0));
		forn(j,0,n) {
			cin >> x;
			a[i].push_back(x);
		}
		cin >> x;
		b.push_back(x);
	}
	forn(i,0,n) {
		if (fabs(a[i][i])<=1e-9) {
			forn(j,i+1,n) if (fabs(a[j][i])>1e-9) {
				swap(a[i],a[j]);
				swap(b[i],b[j]);
			}
		}
		double v=a[i][i];
		forn(j,i,n) a[i][j]/=v;
		b[i]/=v;
		forn(j,i+1,n) {
			v=a[j][i];
			forn(k,0,n) {
				a[j][k]-=a[i][k]*v;
			}
			b[j]-=b[i]*v;
		}
	}
	for (int i=n-1; i>0; i--) {
		forn(j,0,i) {
			double v=a[j][i];
			forn(k,i,n) a[j][k]-=v*a[i][k];
			b[j]-=v*b[i];
		}
	}
	forn(i,0,n) {
		cout << (int)floor(b[i]+1e-9) << " ";
	}
	return 0;
}
