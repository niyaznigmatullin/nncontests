
def gcd(a, b):
    return a if b == 0 else gcd(b, a % b)
    
def solve(a, b):
    f = gcd(a, b);
    for i in range(7):
        f = gcd(f * f, b)
    return f == b
    
t = int(input())
for i in range(t):
    a, b = map(int, input().split())
    print("Yes" if solve(a, b) else "No")