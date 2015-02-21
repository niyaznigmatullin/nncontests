
n, m = map(int, input().split())
p = [i for i in range(n)]
for i in range(m):
  v, u = map(int, input().split())
  v -= 1
  u -= 1
  while p[v] != v:
    v = p[v]
  while p[u] != u:
    u = p[u]
  p[v] = u
ans = 0
for i in range(n):
  if p[i] == i:
    ans += 1
print(ans)