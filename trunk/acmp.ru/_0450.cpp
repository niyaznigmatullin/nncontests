#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#define sz size()
using namespace std;

string q;
int b[10],a[10][10],ans=1<<30,n;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	for (int i=0; i<n; i++)
	for (int j=0; j<n; j++) cin >> a[i][j];
	for (int i=0; i<n; i++) b[i]=i;
	do {
	    int sum=0;
	    for (int i=0; i<n; i++)
	    for (int j=0; j<n; j++) if (j!=b[i]) sum+=a[i][j];
	    if (sum<ans) {
            q="";
	        for (int i=0; i<n; i++) q+=(char)(b[i]+'A');
	        ans=sum;
	    }
	} while (next_permutation(b,b+n));
	cout << q << "\n" << ans;
	return 0;
}
