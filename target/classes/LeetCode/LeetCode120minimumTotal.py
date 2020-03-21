class Solution:
    def minimumTotal(self, triangle) :
        '''
        常规解法  最少需要2N的复杂度，希望实现最根本的O（N)的复杂度需要从后向前进行dp
        :param triangle:
        :return:
        '''
        N = len(triangle)
        if N == 1:
            return triangle[0][0]
        if N == 0:
            return 0
        dp = [[0 for k in range(i+1)] for i in range(N)]
        dp[0][0] = triangle[0][0]
        for i in range(1,N):
            tmp = len(triangle[i])
            for k in range(tmp):
                if k == 0:
                    dp[i][k] = dp[i-1][k]+triangle[i][k]
                elif k == tmp -1 :
                    dp[i][k] = dp[i-1][k-1]+triangle[i][k]
                else:
                    dp[i][k] += min(dp[i-1][k],dp[i-1][k-1])+triangle[i][k]
        return min(dp[N-1])
    def minimumTotal(self, triangle) :
        '''
        从后向前的dp 实现了On的控件复杂度
        :param triangle:
        :return:
        '''
        N = len(triangle)
        if N == 1:
            return triangle[0][0]
        if N == 0:
            return 0
        dp = triangle[N-1]
        for i in range(N-1-1,-1,-1):
            tmp = len(triangle[i])
            for k in range(tmp):
                dp[k] = min(dp[k],dp[k+1])+triangle[i][k]
        return dp[0]


