

n = int(input())
a = []
for i in range(n):
    x, y = map(float, input().split())
    a.append("{" + str(x) + "," + str(y) + "}")
print("{" + ",".join(a), "}")
exit()

n = int(input())
a = []
for i in range(n):
    x, y = map(float, input().split())
    a.append((x, y, i))
a.sort(key=(lambda x: x[0]))
for (x, y, i) in a:
    print(x, y, i)