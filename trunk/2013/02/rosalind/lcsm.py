from sys import stdin

s = stdin.readlines()
s = [c[:len(c) - 1] for c in s]

i = 0
t = []
while i < len(s):
    j = i + 1
    cur = ""
    while j < len(s) and s[j][0] != '>':
        cur += s[j]
        j += 1
    t.append(cur)
    i = j
s = t[0]
ans = ""
for i in range(len(s)):
    for j in range(i, len(s)):
        if j - i + 1 <= len(ans):
            continue
        sub = s[i:j+1]
        ok = True
        for e in t:
            if e.find(sub) < 0:
                ok = False
                break
        if ok:
            ans = sub
print(ans)
