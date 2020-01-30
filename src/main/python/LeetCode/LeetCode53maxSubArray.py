class Solution:
    def maxSubArray(self, nums):
        '''
        这种方法用的是动态规划，主要参考值不能是nums[i] 而应该是nums[i-1]
        :param nums:
        :return:
        '''
        ans = nums[0]
        length = len(nums)
        if len(nums) == 1:
            return nums[0]
        sum1 = nums[0]
        for i in range(1,length):
            if nums[i-1] >=0:
                nums[i] +=nums[i-1]
            if ans < nums[i]:
                ans = nums[i]
        return nums
    def maxSubArray2(self, nums):
        '''
        这种方法用的是动态规划，主要参考值不能是nums[i] 而应该是nums[i-1]找出其序列
        :param nums:
        :return:
        '''
        ans = nums[0]
        length = len(nums)
        left = 0
        right = 0
        resleft = 0
        resright = 0
        if len(nums) == 1:
            return nums[0]
        sum1 = nums[0]
        for i in range(1,length):
            if nums[i-1] >=0:
                nums[i] +=nums[i-1]
                right += 1
            else:
                left = right = i
            if ans < nums[i]:
                ans = nums[i]
                resleft = left
                resright = right
        return ans,left,right

if __name__ == '__main__':
    t1 = Solution()
    print(t1.maxSubArray2([1,2,-5,4,4]))






