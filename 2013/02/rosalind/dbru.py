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
c = {}
for i in range(len(s)):
#    for j in range(len(s)):
#        if i == j:
#            continue
#        e = s[i]
#        f = s[j]
#        if e[1:] == f[:len(f) - 1]:
#            c[e[:len(e)-1], f[1:]] = 1
    c[s[i][:len(s[i])-1], s[i][1:]] = 1
    
for e in c:
    print(str(e).replace("'", ''))