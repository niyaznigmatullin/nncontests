#include <stdio.h>
#include <iostream>
using namespace std;

int n, maxi;

bool razlsl(int x, int y)
{
    if ((y*(y+1))/2>x) {
        return false;
    }
    if (y%2==0)
    {
        if ((x%y)==(y/2)) return true; else return false;
    } else
    {
        if (x%y==0) return true; else return false;
    }
}
int ans;
int main() {
    cin >> n;
    maxi = 1;
	ans = n;
    for (long long ii=2; ((ii+1)*ii)/2<=n; ii++)
    {
        if (razlsl(n,(int)ii))
        {
			int i = (int)ii;
			if (maxi<i) {
				maxi = i;
				if (i % 2 == 0) ans = n / i - i / 2 + 1; else ans = n / i - i / 2;
			}
        }
    }
	printf("%d %d",ans, maxi);
    return 0;
}
