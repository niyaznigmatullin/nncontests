from sys import stdin

s = [x.strip() for x in stdin.readlines()]
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
t = t[1:]
changed = True
for e in t:
    s = s.replace(e, '')
print(s)
