import itertools

def go(x, n, p, was):
    if x == n:
        print(" ".join(str(x + 1) for x in p))
        return
    for i in range(n):
        if was[i]:
            continue
        was[i] = True
        p[x] = i
        go(x + 1, n, p, was)
        was[i] = False
    return
def f(n):
    return 1 if n == 0 else n * f(n - 1)
    

n = int(input())
print(f(n))
p = [0]*n
was = [False]*n
go(0, n, p, was)
