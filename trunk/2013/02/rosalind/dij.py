
n, m = map(int, input().split())
a = [[] for i in range(n)]
for i in range(m):
  v, u, w = map(int, input().split())
  a[v - 1].append((u - 1, w))
inf = 123456789
d = [inf] * n
d[0] = 0
used = [False] * n
while True:
  minv = -1
  for i in range(n):
    if used[i] or d[i] == inf:
      continue
    if minv < 0 or d[minv] > d[i]:
      minv = i
  if minv < 0:
    break
  used[minv] = True
  for u, w in a[minv]:
    if d[u] > d[minv] + w:
      d[u] = d[minv] + w
for i in range(n):
  if d[i] == inf:
    d[i] = -1
print(*d)