from sys import stdin

def rev(s):
    ret = ""
    for c in s[::-1]:
        ret += "ATGC"["TACG".find(c)]    
    return ret

t = [x.strip() for x in stdin.readlines()]
s = []
for e in t:
    s.append(e)
    s.append(rev(e))
    
ans = s[0]
while len(ans) < len(s):
    t = ans[len(ans) - len(s[0]) + 1:]
    for e in s:
        if e.find(t) == 0:
            ans += e[-1:]
            break
ans2 = rev(ans)
z = 0
for k in range(1, len(ans) + 1):
    if ans[:k] == ans2[-k:] or ans2[:k] == ans[-k:]:
        z = k
print(ans, ans2)
if ans[:z] == ans2[-z:]:
    print(ans + ans2[z:])
else:
    print(ans2 + ans[z:])
