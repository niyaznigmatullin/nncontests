

def read():
    s = input()
    s = s.replace('{', '').replace('}', '').replace(',', '')
    ret = {}
    for x in map(int, s.split()):
        ret[x] = 1
    return ret
    
def write(a):
    print(str(a).replace('[', '{').replace(']', '}'))
    
n = int(input())
a = read()
b = read()
ans = []
ans2 = []
ans3 = []
ans4 = []
ans5 = []
ans6 = []
for i in range(1, n + 1):
    if i in a or i in b:
        ans.append(i)
    if i in a and i in b:
        ans2.append(i)
    if (i in a) and (not i in b):
        ans3.append(i)
    if (not i in a) and (i in b):
        ans4.append(i)
    if not (i in a):
        ans5.append(i)
    if not (i in b):
        ans6.append(i)
write(ans)
write(ans2)
write(ans3)
write(ans4)
write(ans5)
write(ans6)

        