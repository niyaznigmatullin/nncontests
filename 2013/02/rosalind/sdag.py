import sys
sys.setrecursionlimit(1 << 20)

def dfs(v):
  global dp, a, topsort, used
  used[v] = True
  for to, w in a[v]:
    if not used[to]:
      dfs(to)
  topsort.append(v)
  
n, m = map(int, input().split())
a = [[] for i in range(n)]
for i in range(m):
  v, u, w = map(int, input().split())
  a[v - 1].append((u - 1, w))
dp = [1 << 30] * n
used = [False] * n
topsort = []
dfs(0)
topsort.reverse()
dp[0] = 0
for v in topsort:
  for u, w in a[v]:
    if dp[u] > dp[v] + w:
      dp[u] = dp[v] + w
for i in range(n):
  if dp[i] == 1 << 30:
    dp[i] = 'x'
print(*dp)