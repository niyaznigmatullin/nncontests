#include <iostream>
#include <stdio.h>
#include <algorithm>

#include <vector>
#include <string>
#include <queue>
#define sz size()
using namespace std;

int n,p;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> p;
	double s1=p*0.01,s2=(100-p)*0.01/n;
	printf("%.10lf",1/(s1+s2));
	return 0;
}
