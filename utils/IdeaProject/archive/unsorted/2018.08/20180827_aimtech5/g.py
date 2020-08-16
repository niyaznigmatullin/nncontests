from sys import stdout

def search(left, right, k):
	while left < right - k:
		a = []
		s = str(k)
		for i in range(1, k + 1):
			a.append(left + i * (right - left + 1) // (k + 1)) 
		s += ' '
		s += " ".join(str(x) for x in a)
		print(s)
		stdout.flush()
		x = int(input())
		if x < 0:
			exit()
		a = [left - 1] + a + [right + 1]
		mx = 0
		for i in range(1, len(a)):
			mx = max(mx, a[i] - a[i - 1] - 1)
		# print('mx = ', mx)
		left = a[x] + 1
		right = a[x + 1] - 1
	print(right - left, " ".join(str(x) for x in range(left, right)))
	stdout.flush()

print("1 10000")
stdout.flush()

x = int(input())

if x < 0:
	exit()
if x == 0:
	print("1 40")
	stdout.flush()
	x = int(input())
	if x < 0:
		exit()
	if x == 0:
		print("1 6")
		stdout.flush()
		x = int(input())
		if x < 0:
			exit()
		if x == 0:
			print("1 2")
			stdout.flush()
			x = int(input())
			if x < 0:
				exit()
			if x == 0:
				print("1 1")
			else:
				print("3 3 4 5")
			stdout.flush()
			x = int(input())
		else:
			search(7, 40, 7)
	else:
		search(41, 10000, 41)
else:
	search(10001, 10004205361450475, 10000)
