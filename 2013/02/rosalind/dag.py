ans = []
tests = int(input())

def dfs(v, c):
  global color, a
  color[v] = c
  for to in a[v]:
    if color[to] < 0:
      if not dfs(to, c ^ 1):
        return False
    else:
      if color[to] == color[v]:
        return False
  return True
  
for curt in range(tests):
  input()
  n, m = map(int, input().split())
  a = [[] for i in range(n)]
  for i in range(m):
    v, u = map(int, input().split())
    a[v - 1].append(u - 1]
  ok = True
  color = [-1] * n
  for i in range(n):
    if color[i] >= 0:
      continue
    if not dfs(i, 0):
      ok = False
      break
  ans.append(1 if ok else -1)
