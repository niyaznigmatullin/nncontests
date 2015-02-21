
n, m = map(int, input().split())
a = [[] for i in range(n)]
for i in range(m):
  v, u = map(int, input().split())
  a[v - 1].append(u - 1)
q = [0] * n
d = [-1] * n
d[0] = 0
head = 0
tail = 0
q[tail] = 0
tail += 1
while head < tail:
  v = q[head]
  head += 1
  for u in a[v]:
    if d[u] < 0:
      d[u] = d[v] + 1
      q[tail] = u
      tail += 1
print(*d)