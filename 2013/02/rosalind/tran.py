
s = input()
t = input()
c = sum(0 if c == d or ("AG".find(c) >= 0) != ("AG".find(d) >= 0) else 1 for (c, d) in zip(s, t))
d = sum(0 if c == d or ("AG".find(c) >= 0) == ("AG".find(d) >= 0) else 1 for (c, d) in zip(s, t))
print(1. * c / d) 