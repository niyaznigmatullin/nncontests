from sys import stdin

m = {}
s = [x.strip() for x in stdin.readlines()]
s = s[1:]
s = "".join(s)
for i in range(len(s) - 3):
    t = s[i:i+4]
    if t in m:
        m[t] += 1
    else:
        m[t] = 1
ans = []
for a in "ACGT":
    for b in "ACGT":
        for c in "ACGT":
            for d in "ACGT":
                e = "".join([a, b, c, d])
                if e in m:
                    ans.append(m[e])
                else:
                    ans.append(0)
print(" ".join(str(x) for x in ans))