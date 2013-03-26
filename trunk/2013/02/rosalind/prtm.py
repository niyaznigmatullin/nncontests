

fin = open("masstable", "r")
t = int(fin.readline().strip())
c = {}
for i in range(t):
    s = fin.readline().strip().split()
    k = s[0]
    m = float(s[1])
    c[k] = m
s = input()
ans = 0
for d in s:
    ans += c[d]
print(ans)