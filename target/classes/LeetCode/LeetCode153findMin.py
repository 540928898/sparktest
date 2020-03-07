class Solution:
    def findMin(self, nums):
        N = len(nums)
        left = 0
        right = N-1
        if N == 1 or nums[N - 1] > nums[0]:
            return nums[0]
        while(left < right):
            tmp = (left + right) // 2
            if nums[tmp]>nums[tmp+1]:
                return nums[tmp+1]
            if nums[tmp]> nums[-1]:
                left = tmp
            if nums[tmp]<nums[-1]:
                right  = tmp
        return nums[right]



