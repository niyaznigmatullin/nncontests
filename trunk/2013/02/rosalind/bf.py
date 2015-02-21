
n, m = map(int, input().split())
a = []
for i in range(m):
  v, u, w = map(int, input().split())
  a.append((v - 1, u - 1, w))
inf = 1234567890123456789
d = [inf] * n
d[0] = 0
for it in range(n - 1):
  for v, u, w in a:
    if d[v] != inf and d[v] + w < d[u]:
      d[u] = d[v] + w
for i in range(n):
  if d[i] == inf:
    d[i] = 'x'
print(*d)