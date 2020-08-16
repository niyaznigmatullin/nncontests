

s = {}

def f(x, n):
	if len(s) > 1000:
		return
	if (x > n):
		s[x] = 1
		return
	for i in [2, 3, 5, 7]:
		f(i * x, n)

f(1, 1000000000000000000)
print(s)