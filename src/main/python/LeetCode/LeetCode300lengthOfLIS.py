class Solution:
    def lengthOfLIS(self, nums):
        N = len(nums)
        res = 1
        if N <=1:
            return N
        dp = [1 for i in range(N)]
        dp[0] = 1
        for i in range(1,N):
            for k in range(i):
                if nums[k] < nums[i]:
                    dp[i] = max(dp[i],dp[k]+1)
            res = max(dp[i],res)
        return res


if __name__ == '__main__':
    t1 = Solution()
    print (t1.lengthOfLIS([1,3,6,7,9,4,10,5,6]))