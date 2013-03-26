
def canpair(c, d):
    if ord(c) > ord(d):
        c, d = d, c
    return c == 'A' and d == 'U' or c == 'C' and d == 'G' or c == 'G' and d == 'U'

s = input()

dp = []
for i in range(len(s)):
    dp.append([0]*(len(s)))
for i in range(len(s)):
    dp[i][i] = 1
for d in range(2, len(s) + 1):
    for i in range(len(s) - d + 1):
        j = i + d - 1
        dp[i][j] = dp[i + 1][j]
        for k in range(i + 4, j + 1):
            if not canpair(s[i], s[k]):
                continue
            dp[i][j] += dp[i + 1][j - 1] if j == k else dp[i + 1][k - 1] * dp[k + 1][j]
print(dp[0][len(s) - 1])