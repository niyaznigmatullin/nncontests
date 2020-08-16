from math import sqrt

a = int(input())

def ans(a):
	z1 = (2 * a + 1 - sqrt(1 + 4 * a)) / 2
	y = (2 * a - 1 + sqrt(-3 + 4 * a)) / 2
	z2 = (2 * a + 1 + sqrt(1 + 4 * a)) / 2

	A = 1 / 3 * ((z2 - a) ** 3 - (y - a) ** 3)
	B = a * (y - z1) - 2 / 3 * (y ** (3 / 2) - z1 ** (3 / 2))
	C = 1 / 2 * (z2 ** 2 - z1 ** 2)

	return 2 * (C - A - B)

print(4 * a - 1, ".6666666667", sep = "")