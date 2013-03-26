
def f(n):
    return 1 if n == 0 else n * f(n - 1)
def c(n, k):
    return f(n) / f(k) / f(n - k)
    
k, n = map(int, input().split())
a = 2**k
ans = 0
div = 4**a
for i in range(n, a + 1):
    ans += c(a, i) * (3**(a - i)) / div
print(ans)