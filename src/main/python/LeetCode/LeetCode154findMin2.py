class Solution:
    def findMin2(self, nums) -> int:
        '''
        主要是考虑重复值
        :param nums:
        :return:
        '''
        N = len(nums)
        left = 0
        right = N-1
        while(left < right):
            tmp = (left+right)//2
            if nums[tmp] >nums[right]:
                left = tmp+1
            elif nums[tmp] < nums[right]:
                right = tmp
            else: right -= 1
        return nums[left]

