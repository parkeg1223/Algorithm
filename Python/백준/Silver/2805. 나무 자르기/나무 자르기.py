import sys

N, M = map(int, sys.stdin.readline().split())
hList = list(map(int, sys.stdin.readline().split()))

hList.sort(reverse=True)
hList.append(0)
ans = hList[0]
k = 0
for i in range(N):
    if M <= (i+1) * (hList[i] - hList[i+1]):
        if M % (i+1) == 0:
            temp = ans * (i+1)
            ans -= M // (i+1)
        else:
            ans -= (M // (i+1)) + 1
        break
    else:
        M -= (i+1) * (hList[i] - hList[i+1])
        ans = hList[i+1]

print(ans)