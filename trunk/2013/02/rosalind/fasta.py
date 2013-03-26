from sys import stdin

s = [x.strip() for x in stdin.readlines()]
t = []
i = 0
while i < len(s):
    j = i + 1
    cur = ""
    while j < len(s) and s[j][0] != '>':
        cur += s[j]
        j += 1  
    t.append(cur)
    i = j
for e in t:
    print(e)
        