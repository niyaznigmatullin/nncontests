
n, m = map(int, input().split())
deg = [0] * n
a = [[] for i in range(n)]
for i in range(m):
  v, u = map(int, input().split())
  a[v - 1].append(u - 1)
  a[u - 1].append(v - 1)
  deg[v - 1] += 1
  deg[u - 1] += 1
ddeg = [0] * n
for i in range(n):
  for j in a[i]:
    ddeg[i] += deg[j]
print(*ddeg)
