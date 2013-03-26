

n = int(input())
s = []
for i in range(n):
    input()
    s.append(input())
for i in range(n):
    ans = []
    for j in range(n):
        ans.append(1. * sum(1 if c != d else 0 for (c, d) in zip(s[i], s[j])) / len(s[i]))
    print(" ".join(str(x) for x in ans))