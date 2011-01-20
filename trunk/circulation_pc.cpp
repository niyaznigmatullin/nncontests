/*
 * A.cpp
 *
 *  Created on: 12.12.2010
 *      Author: pasha
 */

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

const int MAX_VERTICES = 200;
const int MAX_INT = ~(1 << 31);

struct edge
{
	int from;
	int to;
	int cap;
	int flow;
	int bw_index;
	int index;
	edge(int form, int to, int cap, int index) :
		from(from), to(to), cap(cap), flow(0), index(index)
	{

	}
};

typedef vector<edge>::iterator edge_iter_t;

struct vertice
{
	vector<edge> adj;
	edge_iter_t cur_iter;
	int level;
};

vector<vertice> graph;

typedef vector<vertice>::iterator vert_iter_t;

void set_levels(int s)
{
	for (vert_iter_t i = graph.begin(); i != graph.end(); ++i)
	{
		i->level = MAX_INT;
	}
	queue<int> bfs_queue;
	bfs_queue.push(s);
	graph[s].level = 0;
	while (!bfs_queue.empty())
	{
		int u = bfs_queue.front();
		bfs_queue.pop();
		for (edge_iter_t i = graph[u].adj.begin(); i != graph[u].adj.end(); i++)
		{
			if (i->cap - i->flow > 0 && graph[i->to].level == MAX_INT)
			{
				graph[i->to].level = graph[u].level + 1;
				bfs_queue.push(i->to);
			}
		}
	}
}

int dfs(int u, int min_cap, int t)
{
	if (u == t)
		return min_cap;
	for (edge_iter_t & i = graph[u].cur_iter; i != graph[u].adj.end(); ++i)
	{
		if (graph[i->to].level == graph[u].level + 1 && i->cap - i->flow > 0)
		{
			int pushed_flow = dfs(i->to, min(min_cap, i->cap - i->flow), t);
			if (pushed_flow)
			{
				i->flow += pushed_flow;
				graph[i->to].adj[i->bw_index].flow -= pushed_flow;
				return pushed_flow;
			}
		}
	}
	return 0;
}

long long max_flow(int s, int t)
{
	long long flow = 0;
	while (true)
	{
		set_levels(s);
		if (graph[t].level == MAX_INT)
			break;
		for (vert_iter_t i = graph.begin(); i != graph.end(); ++i)
			i->cur_iter = i->adj.begin();
		while (int cur_flow = dfs(s, MAX_INT, t))
			flow += cur_flow;
	}
	return flow;
}

void add_edge(vector<vertice> & graph, int from, int to, int cap, int index)
{
	graph[from].adj.push_back(edge(from, to, cap, index));
	graph[to].adj.push_back(edge(to, from, 0, -1));
	graph[from].adj.back().bw_index = graph[to].adj.size() - 1;
	graph[to].adj.back().bw_index = graph[from].adj.size() - 1;
}

vector<bool> visited;

int main()
{
	ifstream stin("circulation.in");
	ofstream stout("circulation.out");
	int n, m;
	stin >> n >> m;
	graph.resize(n + 2);
	vector<int> lower_values;

	for (int i = 0; i < m; i++)
	{
		int from, to, lower, cap;
		stin >> from >> to >> lower >> cap;
		from--;
		to--;
		lower_values.push_back(lower);
		add_edge(graph, from, to, cap - lower, i);
		add_edge(graph, n, to, lower, -1);
		add_edge(graph, from, n + 1, lower, -1);
	}

	max_flow(n, n + 1);

	bool good = true;
	for (edge_iter_t i = graph[n].adj.begin(); i != graph[n].adj.end(); ++i)
	{
		if (i->cap - i->flow != 0)
		{
			good = false;
		}
	}
	for (edge_iter_t i = graph[n + 1].adj.begin(); i != graph[n + 1].adj.end(); ++i)
	{
		edge & bw = graph[i->to].adj[i->bw_index];
		if (bw.cap - bw.flow != 0)
		{
			good = false;
		}
	}

	if (!good)
	{
		stout << "NO\n";
	}
	else
	{
		stout << "YES\n";
		vector<int> answ(m);
		for (vert_iter_t i = graph.begin(); i != graph.end(); ++i)
		{
			for (edge_iter_t j = i->adj.begin(); j != i->adj.end(); ++j)
			{
				if (j->index != -1)
				{
					answ[j->index] = j->flow + lower_values[j->index];
				}
			}
		}
		for (int i = 0; i < (int) answ.size(); ++i)
		{
			stout << answ[i] << '\n';
		}
	}
}
