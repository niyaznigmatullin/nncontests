
n = int(input())
s = input()
p = [0] * len(s)
p[0] = -1
k = -1
for i in range(1, len(s)):
    while k > -1 and s[k + 1] != s[i]:
        k = p[k]
    if s[k + 1] == s[i]:
        k += 1
    p[i] = k
ans = 0
m = 1
c = {}
i = len(s) - 1
while i >= 0:
    c[i + 1] = 1
    i = p[i]
for i in range(1, len(s) + 1):
    m *= n
    if i in c:
        ans += m
print(ans)