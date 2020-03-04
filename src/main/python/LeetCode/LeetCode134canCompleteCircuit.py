class Solution:
    def canCompleteCircuit(self, gas, cost):
        N = len(gas)
        temp = [gas[i] - cost[i] for i in range(N)]
        cur = 0
        curIndex = 0
        total = 0
        for i in range(N):
            cur += temp[i]
            total += temp[i]
            if cur < 0:
                cur = 0
                curIndex =i+1
        if total <0:
            return -1
        else:
            return curIndex