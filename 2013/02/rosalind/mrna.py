
s = (" ".join(x.strip() for x in open("alpha", "r"))).split()
s = [x for x in filter(lambda c: len(c) != 3, s)]
ans = 1
t = input()
for c in t:
    ans *= s.count(str(c))
    ans %= 1000000
ans = (ans * s.count('Stop')) % 1000000
print(ans)
