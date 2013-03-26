from sys import stdin

s = [x.strip() for x in stdin.readlines()]
s = "".join(s[1:])
for c in range(len(s)):
    for i in range(2, 7):
        if c - i + 1 < 0 or c + i >= len(s):
            continue
        ok = True
        for j in range(i):
            if "ATGC"["TACG".find(s[c - j])] != s[c + j + 1]:
                ok = False
                break
        if ok:
            print(c + 2 - i, 2 * i)