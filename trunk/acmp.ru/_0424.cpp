#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#include <map>
#define sz size()
using namespace std;
typedef long long int64;

map <int64,bool> w;
int64 n;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	w[1]=false;
	for (map <int64,bool>::iterator it=w.begin(); it!=w.end(); it++) {
		for (int i=2; i<=9; i++) if (it->first*i<n) if (w.count(it->first*i)==0) w[it->first*i]=false;
	}
	for (map <int64,bool>::iterator it=w.end(); it--!=w.begin(); ) {
		if (it->first*9>=n) it->second=true; else {
			bool ok=true;
			for (int i=2; i<=9; i++) {
				ok&=w[it->first*i];
			}
			it->second=!ok;
		}
	}
	if (w[1]) cout << "Stan wins."; else cout << "Ollie wins.";
	return 0;
}
