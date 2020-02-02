class Solution:
    def maxProfit(self, prices):
        '''
        可以交易多次，没有冷冻期
        :param prices:
        :return:
        '''
        res = 0
        N = len(prices)
        if N <= 1:
            return 0
        for i in range(1,N):
            if prices[i-1] < prices[i]:
                res += prices[i] - prices[i-1]
        return res

