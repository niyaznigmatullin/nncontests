ans = []
tests = int(input())
for curt in range(tests):
  input()
  n, m = map(int, input().split())
  p = [i for i in range(n)]
  d = [0] * n
  ok = True
  for i in range(m):
    v, u = map(int, input().split())
    v -= 1
    u -= 1
    c1 = 0
    c2 = 0
    while p[v] != v:
      c1 = c1 ^ d[v]
      v = p[v]
    while p[u] != u:
      c2 = c2 ^ d[u]
      u = p[u]
    if v == u and c1 == c2:
      ok = False
    p[v] = u
    d[v] = 1 if c1 == c2 else 0
  ans.append(1 if ok else -1)
print(*ans)
