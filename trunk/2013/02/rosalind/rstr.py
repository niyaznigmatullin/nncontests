from math import log, pow
n, z = map(float, input().split())
s = input()
p1 = z * .5
p2 = (1 - z) * .5
a = 1
for c in s:
    if "GC".find(c) >= 0:
        a *= p1
    else:
        a *= p2
print(1 - pow(1 - a, n))