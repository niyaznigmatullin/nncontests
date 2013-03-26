
s = input()
t = input()
print(sum(1 if c != d else 0 for c, d in zip(s, t)))