

n = int(input())
a = [x for x in map(int, input().split())]

dp1 = [0]*n
dp2 = [0]*n
for i in range(n):
    dp1[i] = 1
    dp2[i] = 1
    for j in range(i):
        if a[j] < a[i] and dp1[j] + 1 > dp1[i]:
            dp1[i] = dp1[j] + 1
        if a[j] > a[i] and dp2[j] + 1 > dp2[i]:
            dp2[i] = dp2[j] + 1
ans1 = []
ans2 = []
best1 = -1
best2 = -1
for i in range(n):
    if best1 < 0 or dp1[best1] < dp1[i]:
        best1 = i
    if best2 < 0 or dp2[best2] < dp2[i]:
        best2 = i
while True:
    ans1.append(a[best1])
    if dp1[best1] == 1:
        break
    for i in range(best1):
        if a[i] < a[best1] and dp1[i] + 1 == dp1[best1]:
            best1 = i
            break
while True:
    ans2.append(a[best2])
    if dp2[best2] == 1:
        break
    for i in range(best2):
        if a[i] > a[best2] and dp2[i] + 1 == dp2[best2]:
            best2 = i
            break
print(" ".join(str(x) for x in reversed(ans1)))
print(" ".join(str(x) for x in reversed(ans2)))