#include <iostream>
#include <string>
#include <map>

using namespace std;

int d,e,f,dp,ep,h,n,maxn;
map <int,int> dis;
int main() {
    cin >> d >> e >> f >> dp >> ep >> h;
	maxn = (1 << (f)) - 1;
	n = (1 << (f + 1)) - 1;
	dp += maxn;
	ep += maxn;
	dp >>= (f - d);
	ep >>= (f - e);
	int ans = 0, kol = 0;	
	while (dp > 0) {
		dis[dp] = kol++;
		dp >>= 1;
	}
	kol = 0;
	while (ep > 0) {
		if (dis.count(ep)!=0) {
			ans = dis[ep];
			break;
		}
		ep >>= 1;
		kol ++;
	}
	if (ans + kol > h) {
		printf("NO\n");
	} else printf("YES\n");
    return 0;
}
