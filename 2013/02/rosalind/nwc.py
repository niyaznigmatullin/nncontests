
tests = int(input())
ans = []
for curt in range(tests):
  n, m = map(int, input().split())
  a = []
  for i in range(m):
    v, u, w = map(int, input().split())
    a.append((v - 1, u - 1, w))
  d = [0] * n
  for it in range(n - 1):
    for v, u, w in a:
      if d[v] + w < d[u]:
        d[u] = d[v] + w
  ok = False
  for v, u, w in a:
    if d[v] + w < d[u]:
      ok = True
      break
  ans.append(1 if ok else -1)
print(*ans)