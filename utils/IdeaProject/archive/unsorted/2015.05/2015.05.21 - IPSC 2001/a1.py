
f = 1
ans = 0
for i in range(1001):
    ans += f % 13
    f *= i + 2
print(ans)