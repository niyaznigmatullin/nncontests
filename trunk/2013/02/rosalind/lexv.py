

def go(x, k, t, s):
    print(t)
    if x == k:
        return
    for c in s:
        go(x + 1, k, t + c, s)
        
        
s = [c[0] for c in input().split()]
k = int(input())
go(0, k, "", s)