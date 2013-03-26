

def f(n):
    return 1 if n == 0 else n * f(n - 1)
    
n, k = map(int, input().split())
print(f(n) // f(n - k) % 1000000)