class Solution:
    def maxProduct(self, nums):
        '''
        使用动规，也就是
        :param nums:
        :return:
        '''
        N = len(nums)
        if N == 1:
            return nums[0]
        dpmax = [0 for i in range(N)]
        dpmin = [0 for i in range(N)]
        dpmax[0] = nums[0]
        dpmin[0] = nums[0]
        res = nums[0]
        for i in range(1,N):
            dpmax[i] = max(dpmax[i-1]*nums[i],dpmin[i-1]*nums[i],nums[i])
            dpmin[i] = min(dpmax[i-1]*nums[i],dpmin[i-1]*nums[i],nums[i])
            res = max(res,dpmax[i])
        return res
    def maxProduct(self,nums):
        '''
        使用正序和倒叙  两个计算方式，其实是使用
        :param nums:
        :return:
        '''
        N = len(nums)
        numsTemp = nums[::-1]
        res = nums[0]
        tmp = nums[0]
        for i in range(1,N):
            if nums[i-1] == 0:
                tmp = nums[i]
            else:
                tmp *= nums[i]
            res = max(tmp,res)
        tmp = numsTemp[0]
        res2 = numsTemp[0]
        for i in range(1,N):
            if numsTemp[i-1] == 0:
                tmp = numsTemp[i]
            else:
                tmp *= numsTemp[i]
            res2 = max(tmp,res2)
        return max(res,res2)


    def maxProduct(self,nums):
        '''
        为什么最后是加号？？？？
        :param nums:
        :return:
        '''
        reverse_nums = nums[::-1]
        for i in range(1, len(nums)):
            nums[i] *= nums[i - 1] or 1
            reverse_nums[i] *= reverse_nums[i - 1] or 1
        return max(nums + reverse_nums)

