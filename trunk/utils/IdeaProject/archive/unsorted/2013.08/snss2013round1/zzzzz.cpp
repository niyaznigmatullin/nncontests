#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
#include <math.h>

#define lli long long

using namespace std;

ifstream in;
ofstream out;

struct point
{
    lli x, y;
};

struct vect
{
    lli x, y;
};

struct line
{
    long double a, b, c;
    void get(point p1, point p2)
    {
        a = p1.y - p2.y;
        b = p2.x - p1.x;
        c = -(a * p1.x + b * p1.y);
    }
    bool d(point p)
    {
        if (a * p.x + b * p.y + c >= 0)
            return true;
        return false;
    }
};

bool in_m(point p, const vector<point> &mn, line l1, line l2, const vector<double> &u, vect v)
{
    int n = (int)mn.size();
    double d = atan2((double)(v.x * p.y - p.x * v.y), (double)(v.x * p.x + p.y * v.y));
    if (!((l1.d(p)) && (l2.d(p))))
        return false;
    int l = 0, r = n - 1, m;
    while (r - l > 1)
    {
        m = (l + r) / 2;
        if (u[m] < d)
            l = m;
        else
            r = m;
    }
    line li;
    li.get(mn[l], mn[r]);
    if (li.d(p))
        return true;
    return false;
}

vector<point> mn;
vector<double> u;
vector<vect> v;

int main()
{
    int ans = 0;
    point p;
    in.open("theodore.in");
    out.open("theodore.out");
    int n, m, k;
    in >> n >> m >> k;
    mn.resize(n);
    u.resize(n - 1);
    v.resize(n - 1);
    for (int i = 0; i < n; ++i)
        in >> mn[i].x >> mn[i].y;
    for (int i = 0; i < n - 1; ++i)
    {
        v[i].x = mn[i + 1].x - mn[0].x;
        v[i].y = mn[i + 1].y - mn[0].y;
    }
    line l1, l2;
    for (int i = 1; i < n; ++i)
        u[i] = atan2(v[0].x * v[i].y - v[i].x * v[0].y, v[0].x * v[i].x + v[i].y * v[0].y);
    l2.get(mn[n - 1], mn[0]);
    l1.get(mn[0], mn[1]);
    for (int i = 0; i < m; ++i)
    {
        in >> p.x >> p.y;
        if (in_m(p, mn, l1, l2, u, v[0]))
            ++ans;
    }
    if (ans >= k)
        out << "YES";
    else
        out << "NO";
    return 0;
}
