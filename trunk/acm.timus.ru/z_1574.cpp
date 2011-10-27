#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#define sz size()
using namespace std;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	string s;
	int a[200001];
	memset(a,0,sizeof(a));
	cin >> s;
	int k=0;
	for (int i=0; i<(int)s.sz; i++) {
		if (s[i]=='(') k++; else k--;
		a[k+100000]++;
	}
	for (int i=0; i<=100000; i++) {
		if (a[i]!=0) {
			if (k==0) printf("%d",a[i]); else printf("0");
			break;
		}
	}
	return 0;
}
