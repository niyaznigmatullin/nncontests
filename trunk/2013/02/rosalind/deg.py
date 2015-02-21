
n, m = map(int, input().split())
deg = [0] * n
for i in range(m):
  v, u = map(int, input().split())
  deg[v - 1] += 1
  deg[u - 1] += 1
print(*deg)