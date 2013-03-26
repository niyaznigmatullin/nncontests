
s = input()
t = input()
i = 0
ans = []
for j in range(len(s)):
    if i >= len(t):
        break
    if t[i] == s[j]:
        ans.append(j)
        i += 1
print(" ".join(str(x + 1) for x in ans))