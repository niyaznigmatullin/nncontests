#include <iostream>
#include <cstdio>
#include <fstream>
#include <cmath>
#include <queue>
#include <algorithm>
#include <vector>
#include <string>
#include <set>
#include <time.h>
#include <map>

#define uint unsigned int
#define pb(x) push_back(x)
#define m_p(x, y) make_pair(x, y)
#define eps 1e-19
#define MAXN  140001
#define sqr(x) (x) * (x)
#define M_PI       3.14159265358979323846
#define INF (long long)1024 * 1024 * 1024 * 1024 * 1024 * 1024
#define INFi 1024 * 1024 * 1024
#define abs(x) (x < 0 ? 0 : (x))
#define pii pair<int, int>
#define ll unsigned long long
#define mod 100000007
#define alp 110
using namespace std;

struct vertex
{
        int go[alp], link, pred;
        bool term, superlink;
        char c;
        vertex()
        {
                link = -1;
                superlink = false;
        }
};

vertex root[MAXN];
int size = 1;

void add(const string &s)
{
        int temp = 0;
        for (uint i = 0; i < s.length(); i++) {
                if (root[temp].go[s[i] - 32] != 0)        {
                        temp = root[temp].go[s[i] - 32];
                }
                else {
                        root[temp].go[s[i] - 32] = size++;
                        root[size - 1].pred = temp;
                        root[size - 1].c = s[i] - 32;
                        temp = size - 1;
                }
        }
        root[temp].term = true;
}

void bfs()
{
        queue<int> q;
        int temp, ver;
        char c = ' ';
        q.push(0);
        while (!q.empty())
        {
                temp = q.front();
                q.pop();
                for (int i = 0; i < alp; i++)
                        if (root[temp].go[i] != 0)
                                q.push(root[temp].go[i]);
                if (temp != 0 && root[temp].pred != 0)
                {
                        c = root[temp].c;
                        ver = root[root[temp].pred].link;
                        while (root[ver].go[(int)c] == 0 && ver != 0)
                                ver = root[ver].link;
                        root[temp].link = root[ver].go[(int)c];
                }
                else
                        root[temp].link = 0;
        }
}

int go1(int v, int c)
{
        if (v == 0 && root[v].go[c] == 0)
                return 0;
        if (root[v].go[c] != 0)
                return root[v].go[c];
        else
                return go1(root[v].link, c);
}

void GetSuperLink()
{
        queue<int> q;
        int temp, t;
        q.push(0);
        while (!q.empty())
        {
                temp = q.front();
                q.pop();
                for (int i = 0; i < alp; i++)
                        if (root[temp].go[i] != 0)
                                q.push(root[temp].go[i]);
                if (temp != 0)
                {
                        if (root[temp].term)
                                root[temp].superlink = true;
                        else
                        {
                                t = root[temp].link;
                                while (t != 0 && !root[t].term)
                                        t = root[t].link;
                                root[temp].superlink = root[t].term;
                        }
                }
        }
}

int main()
{
#ifndef ONLINE_JUDGE
        //console input output inexact-matching
        FILE *in, *out;
        in = freopen("console.in", "r", stdin);
        out = freopen("console.out", "w", stdout);
        //time_t time_start = clock(), time_end;
#endif
        char buf[MAXN * 2], c;
        root[0].link = 0;
        int n, i;
        string s;
        cin >> n;
        gets(buf);
        for (i = 0; i < n; i++) {
                gets(buf);
                s = string(buf);
                add(s);
        }
        bfs();
        GetSuperLink();
        while (cin.get(c)) {
                int temp = 0;
                cin.putback(c);
                s = "";
                gets(buf);
                s = string(buf);
                for (uint i = 0; i < s.length(); i++) {
                        temp = go1(temp, s[i] - 32);
                        if (root[temp].superlink) {
                                cout << s << endl;
                                break;
                        }
                }
        }

#ifndef ONLINE_JUDGE
        //time_end = clock();
        //printf("\nTime : %.13f s", (time_end - time_start) / 1000.0);
#endif
        fclose(in);
        fclose(out);
        return 0;
}