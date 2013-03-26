
s = input()
p = [0]*len(s)
p[0] = -1
k = -1
for i in range(1, len(s)):
    while k > -1 and s[k + 1] != s[i]:
        k = p[k]
    if s[k + 1] == s[i]:
        k += 1
    p[i] = k
print(" ".join(str(x + 1) for x in p))