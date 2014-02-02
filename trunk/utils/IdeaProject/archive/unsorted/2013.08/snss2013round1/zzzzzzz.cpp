#include<iostream>
#include<cstdio>
#include<stack>
#include<algorithm>
#include<cmath>

using namespace std;
struct point
{
        long double x, y;
};

const long double EPS = 0.0000001;
const long double PI = 3.141592653589;
point a[1000000];

point to_vector(point a, point b)
{
        point c;
        c.x = b.x - a.x;
        c.y = b.y - a.y;
        return c;
}

long double cross(point a, point b)
{
        return a.x * b.y - a.y * b.x;
}

long double dot(point a, point b)
{
        return a.x * b.x + b.y * a.y;
}

int main()
{
        freopen("point.in", "r", stdin);
        freopen("point.out", "w", stdout);
        int n, i;
        point  k;
        long double ans = 0, at;
        bool flag = false;

        cin >> n >> k.x >> k.y;
        for(i = 0; i < n; i++)
        {
                cin >> a[i].x >> a[i].y;
                if(a[i].x == k.x && a[i].y == k.y)
                        flag = true;
        }

        a[n] = a[0];
        n++;
        for(i = 1; i < n; i++)
        {
                at = atan2(cross(to_vector(k, a[i - 1]), to_vector(k, a[i])),
                dot(to_vector(k, a[i - 1]), to_vector(k, a[i])));
                if(fabs(fabs(at) - PI) < EPS) {
                        flag = true;
                        break;
                }
                ans += at;
        }
        //cout << 2.00 * PI << endl;
        //cout << ans << endl;
        if (ans < 0) ans = -ans;
        if(fabs(ans - 2.000 * PI) < EPS || flag)
                cout << "YES";
        else
                cout << "NO";

        return 0;
}