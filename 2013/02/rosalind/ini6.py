
a = {}
for s in input().split():
  a[s] = 1 if not s in a else a[s] + 1
print('\n'.join(k + ' ' + str(v) for k, v in a.items()))