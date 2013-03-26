from math import log 

s = input()
p = map(float, input().split())
ans = []
for z in p:
    p1 = log(z * .5) / log(10)
    p2 = log((1 - z) * .5) / log(10)
    a = 0
    for c in s:
        if "GC".find(c) >= 0:
            a += p1
        else:
            a += p2
    ans.append(a)
print(" ".join(str(x) for x in ans))
    