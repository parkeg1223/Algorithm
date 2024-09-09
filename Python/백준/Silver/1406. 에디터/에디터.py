import sys
from collections import deque

s = list(sys.stdin.readline().rstrip())
N = int(sys.stdin.readline())

cLeft = deque()
cRight = deque()
cLeft.extend(s)

for i in range(N):
    cList = sys.stdin.readline().split()
    if cList[0] == "L":
        if len(cLeft) > 0:
            cRight.appendleft(cLeft.pop())
    elif cList[0] == "D":
        if len(cRight) > 0:
            cLeft.append(cRight.popleft())
    elif cList[0] == "B":
        if len(cLeft) > 0:
            cLeft.pop()
    elif cList[0] == "P":
        cLeft.append(cList[1])

print("".join(list(cLeft+cRight)))