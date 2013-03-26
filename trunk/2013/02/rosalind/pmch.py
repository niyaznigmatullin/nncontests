
def f(n):
    return 1 if n == 0 else n * f(n - 1)

s = input()
print(f(s.count('A')) * f(s.count('C')))
