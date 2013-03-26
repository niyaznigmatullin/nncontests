
def f(n):
    ret = 1
    for i in range(2, n + 1):
        ret *= i
    return ret
   
def c(n, k):
    return f(n) // f(k) // f(n - k)

def sol(s, c1, c2):
    a = s.count(c1)
    b = s.count(c2)
    ab = min(a, b)
    return c(a, ab) * c(b, ab) * f(ab)

s = input()
print(sol(s, 'A', 'U') * sol(s, 'G', 'C'))