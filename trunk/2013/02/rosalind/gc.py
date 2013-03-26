from sys import stdin

s = stdin.readlines()
s = [c[:len(c) - 1] for c in s]
i = 0
best = -1
name = ""
while i < len(s):
    j = i + 1
    while j < len(s) and s[j][0] != '>':
        j += 1
    c = ""
    curname = s[i][1:]
    i += 1
    while i < j:
        c += s[i]
        i += 1
    cur = 1. * (c.count('G') + c.count('C')) / len(c)
    if cur > best:
        best = cur
        name = curname
print(name)
print(best * 100)
