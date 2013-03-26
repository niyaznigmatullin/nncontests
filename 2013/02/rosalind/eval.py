
n = int(input())
s = input()
zz = map(float, input().split())
ans = []
for z in zz:
    p1 = z * .5
    p2 = (1 - z) * .5
    p = 1
    for c in s:
        if "GC".find(c) >= 0:
            p *= p1
        else:
            p *= p2
    ans.append((n - len(s) + 1) * p)
print(" ".join(str(x) for x in ans))