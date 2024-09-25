import sys

l = int(sys.stdin.readline())
nList = [0] + list(map(int, sys.stdin.readline().split()))
d = [0] * (l+1)

M = d[1]
for i in range(1, l+1):
    tmpMax = 0
    for j in range(0, i):
        if nList[i] > nList[j]:
            tmpMax = max(tmpMax, d[j]+1)
    d[i] = tmpMax
    M = max(M, tmpMax)

print(M)