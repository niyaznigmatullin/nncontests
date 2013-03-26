
n = int(input())
c = []
for i in range(n):
    input()
    s = input()
    if i == 0:
        for j in range(4):
            c.append([0]*len(s))
    for j in range(len(s)):
        c["ACGT".find(s[j])][j] += 1
z = []
for i in range(len(c[0])):
    m = -1
    for j in range(4):
        if m < 0 or c[j][i] > c[m][i]:
            m = j
    z.append(m)
print("".join("ACGT"[x] for x in z))
for i in range(4):
    f = ""
    f += "ACGT"[i]
    f += ": "
    f += " ".join(str(x) for x in c[i])
    print(f)