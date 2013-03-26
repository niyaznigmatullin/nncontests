

s = input()
t = input()
if s.find(t) >= 0:
    print(s)
elif t.find(s) >= 0:
    print(t)
else:
    ans = None
    for i in range(len(s)):
        e = s[len(s) - i:]
        if t.find(e) == 0:
            q = s[:len(s)-i]+t
            if ans is None or len(ans) > len(q):
                ans = q
    for i in range(len(t)):
        e = t[len(t) - i:]
        if s.find(e) == 0:
            q = t[:len(t)-i]+s
            if ans is None or len(ans) > len(q):
                ans = q
    print(ans)
    
