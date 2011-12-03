#include <vector>
#include <math.h>
#include <set>
#include <iostream>
#include <cstdio>

using namespace std;

const double INF = 1e20;

struct Point
{
    int x;
    int y;

    Point(int x = 0, int y = 0) : x(x), y(y)
    {

    }
};

double dist(Point p1, Point p2) 
{
    double dx = p1.x - p2.x;
    double dy = p1.y - p2.y;
    return sqrt(dx * dx + dy * dy);
}

struct Edge
{
    int from;
    int to;
    int flow;
    int cap;
    double cost;
    Edge * rev;

    Edge(int from, int to, int cap, double cost) : from(from), to(to), flow(0), cap(cap), cost(cost)
    {
        
    }
};

typedef pair<double, int> el;

struct Graph
{
    vector<vector<Edge*> > edges;
    int n;

    Graph(int n) : edges(n), n(n)
    {
        
    }

    void addEdge(int from, int to, int cap, double cost)
    {
        Edge * e1 = new Edge(from, to, cap, cost);
        Edge * e2 = new Edge(to, from, 0, -cost);
        e1->rev = e2;
        e2->rev = e1;
        edges[from].push_back(e1);
        edges[to].push_back(e2);
    }

    vector<double> minCostMaxFlow(int all, int target) const
    {
        set<el> q;
        vector<double> d(n);
        vector<bool> was(n);
        vector<double> phi(n);
        vector<double> ret;
        vector<Edge*> lastEdge(n);
        double curCost = 0;
        for (int source = 0; source < all; source++)
        {
            for (int i = 0; i < n; i++)
            {
                d[i] = i == source ? 0 : INF;
                was[i] = false;
            }
            q.insert(make_pair(0, source));
            was[source] = true;
            while (!q.empty()) 
            {                
                const el v = *(q.begin());
//                cerr << v.first << " " << v.second << "\n";
                q.erase(v);
                was[v.second] = true;
                for (vector<Edge*>::const_iterator it = edges[v.second].begin(); it != edges[v.second].end(); ++it)
                {
                    Edge & e = **it;
                    if (e.flow == e.cap || was[e.to])
                    {
                        continue;
                    }
                    if (d[e.to] > d[e.from] + phi[e.from] - phi[e.to] + e.cost)
                    {
//                        cerr << (*it)->from << " " << (*it)->to << " " << (*it)->flow << " " << (*it)->cap << " " << (*it)->cost << " " << "\n";
                        if (d[e.to] != INF)
                        {
                            q.erase(make_pair(d[e.to], e.to));
                        }
                        d[e.to] = d[e.from] + phi[e.from] - phi[e.to] + e.cost;
                        q.insert(make_pair(d[e.to], e.to));
                        lastEdge[e.to] = *it;
                    }
                }
            }
            curCost += d[target] - phi[source] + phi[target];
            ret.push_back(curCost);
            for (int i = target; i != source; i = lastEdge[i]->from)
            {
                lastEdge[i]->flow++;
                lastEdge[i]->rev->flow--;
            }
            for (int i = 0; i < n; i++)
            {
                phi[i] += d[i] == INF ? 0 : d[i];
            }
        }
        return ret;
    }
};


void solve() 
{
    int n, m;
    scanf("%d%d", &n, &m);
    vector<Point> p(n);
    vector<int> c(n);
    for (int i = 0; i < n; i++)
    {
        scanf("%d%d%d", &p[i].x, &p[i].y, &c[i]);
    }
    vector<Point> p2(m);
    for (int i = 0; i < m; i++) 
    {
        scanf("%d%d", &p2[i].x, &p2[i].y);
    }
    Graph g(n + m + 1);
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            g.addEdge(i, j + m, 1, dist(p2[i], p[j]));
        }
    }
    for (int i = 0; i < n; i++) 
    {
        g.addEdge(i + m, n + m, c[i], 0);
    }
    vector<double> ans = g.minCostMaxFlow(m, n + m);
    for (vector<double>::iterator it = ans.begin(); it != ans.end(); ++it)
    {
        printf("%.3lf\n", *it);
    }
    printf("\n");
}

int main() 
{
    int t;
    scanf("%d", &t);
    for (int i = 0; i < t; i++) 
    {
        solve();
    }
}