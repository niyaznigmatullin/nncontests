import itertools

def go2(x, n, p):
    if x == n:
        print(" ".join(str(x) for x in p))
        return
    go2(x + 1, n, p)
    p[x] = -p[x]
    go2(x + 1, n, p)
    p[x] = -p[x]
    
def go(x, n, p, was):
    if x == n:
        go2(0, n, p)
        return
    for i in range(n):
        if was[i]:
            continue
        was[i] = True
        p[x] = i + 1
        go(x + 1, n, p, was)
        was[i] = False
    return
    
def f(n):
    return 1 if n == 0 else n * f(n - 1) * 2
    

n = int(input())
print(f(n))
p = [0]*n
was = [False]*n
go(0, n, p, was)
