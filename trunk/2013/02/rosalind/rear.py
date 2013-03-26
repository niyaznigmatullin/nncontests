

ans = []

while True:
    try:
        a = "".join(chr(x + ord('a')) for x in map(int, input().split()))
    except EOFError:
        break
    b = "".join(chr(x + ord('a')) for x in map(int, input().split()))
    input()
    c = {}
    q = []
    head = 0
    q.append(a)
    c[a] = 0
    if b in c:
        ans.append(0)
        continue
    while head < len(q):
        v = q[head]
        head += 1
        if b in c:
            break
        print(len(c))
        for i in range(len(v)):
            for j in range(i, len(v)):
                u = ""
                if not (u in c):
                    q.append(u)
                    c[u] = c[v] + 1
    ans.append(c[b])
print(" ".join(str(x) for x in ans))