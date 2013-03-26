k, m, n = map(int, input().split())
print((k * (k - 1) + 2 * k * m + 2 * k * n + m * (m - 1) * .75 + 2 * m * n * .5) / (k + m + n) / (k + m + n - 1))