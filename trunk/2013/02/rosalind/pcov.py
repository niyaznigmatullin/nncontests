from sys import stdin

s = [x.strip() for x in stdin.readlines()]

ans = s[0]
while len(ans) < len(s):
    t = ans[len(ans) - len(s[0]) + 1:]
    for e in s:
        if e.find(t) == 0:
            ans += e[-1:]
            break
print(ans)