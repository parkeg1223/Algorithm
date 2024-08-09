import sys

l = int(sys.stdin.readline())
nList = [0] + list(map(int, sys.stdin.readline().split()))
d = [0] * (l+1)
s = [0] * (l+1)
maxIdx = 0
M = d[1]

for i in range(1, l+1):
    tmpMax = 0
    for j in range(0, i):
        if nList[i] > nList[j]:
            if tmpMax < d[j]+1:
                tmpMax = d[j]+1
                s[i] = j
    d[i] = tmpMax
    if M < tmpMax:
        M = tmpMax
        maxIdx = i

idxList = []
while maxIdx != 0:
    idxList.append(nList[maxIdx])
    maxIdx = s[maxIdx]

print(M)
print(*sorted(idxList))