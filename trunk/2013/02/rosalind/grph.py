

n = int(input())
s = []
t = []
for i in range(n):
    s.append(input())
    t.append(input())
for i in range(n):
    for j in range(n):
        if i == j:
            continue
        if t[i][:3] == t[j][len(t[j]) - 3:]:
            print(s[j], s[i])