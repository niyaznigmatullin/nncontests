
n, k = map(int, input().split())
f1 = 0
f2 = 1
for i in range(n - 1):
    f1, f2 = f2, k * f1 + f2
print(f2)   
