class Solution:
    def maxProfit(self, prices):
        '''
        只可以交易一次，那么只要判断最小值和最大交易值。
        如果当前值小于 历史最小值，那么就要更新最小值。
        如果大于最小值，就看看差值是否比历史最高利润大
        :param prices:
        :return:
        '''
        N = len(prices)
        if N <= 1:
            return 0
        maxValue = 0
        minNum = prices[0]
        for i in range(1,N):
            if prices[i] < minNum:
                minNum = prices[i]
            else:
                maxValue = max(prices[i] - minNum,maxValue)
        return maxValue
    def maxProfitUpdate(self, prices):
        '''
        只可以交易一次，那么只要判断最小值和最大交易值。
        如果当前值小于 历史最小值，那么就要更新最小值。
        如果大于最小值，就看看差值是否比历史最高利润大
        :param prices:
        :return:
        '''
        N = len(prices)
        if N <= 1:
            return 0
        maxValue = 0
        minNum = prices[0]
        for i in range(1,N):
            minNum = min(minNum,prices[i])
            maxValue = max(prices[i] - minNum,maxValue)
        return maxValue




if __name__ == '__main__':
    t1 = Solution()
