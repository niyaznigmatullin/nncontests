from sys import stdin

s = [x.strip() for x in stdin.readlines()]
n = int(s[0])
print(n - len(s))