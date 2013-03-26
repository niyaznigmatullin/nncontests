from sys import stdin

fin = open("masstable", "r")
t = int(fin.readline().strip())
c = {}
for i in range(t):
    s = fin.readline().strip().split()
    k = s[0]
    m = float(s[1])
    c[k] = m

s = [float(x.strip()) for x in stdin.readlines()]
ans = ''
for i in range(1, len(s)):
    d = s[i] - s[i - 1]
    best = 2**100
    ch = '?'
    for e in c:
        if best > abs(c[e] - d):
            best = abs(c[e] - d)
            ch = e
    ans += ch
print(ans)