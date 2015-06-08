import math
import sys

def go(n, x, y):
    global w
    nn = n - x - y * math.pi
    if nn < 4 - 0.000001:
        return 1
    if not w[x][y] is None:
        return w[x][y]
    f = go(n, x + 1, y) + go(n, x, y + 1)
    w[x][y] = f
    return f

sys.setrecursionlimit(20000)
while True:
    n = int(input())
    w = [[None] * n for i in range(n)]
    if n == -1:
        break
    print(go(n, 0, 0))