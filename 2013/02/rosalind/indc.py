from math import log

def f(n):
    return 1 if n == 0 else n * f(n - 1)

def c(n, k):
    return f(n) / f(k) / f(n - k)
    
a = int(input()) * 2
ans = []
div = 2**a
d = 0
for i in range(0, a):
    d += c(a, i) / div
    ans.append(d)

print(" ".join(str(log(x) / log(10)) for x in reversed(ans)))

