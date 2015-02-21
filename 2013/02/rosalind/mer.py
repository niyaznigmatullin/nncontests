
n = int(input())
a = list(map(int, input().split()))
m = int(input())
b = list(map(int, input().split()))
c = []
i = 0
j = 0
while i < n or j < m:
  if j >= m or i < n and a[i] <= b[j]:
    c.append(a[i])
    i += 1
  else:
    c.append(b[j])
    j += 1
print(*c)