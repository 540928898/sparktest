class Solution:
    """
    第一种是交换排序，效率比较低
    第二种可以使用三路快排方法。
    """
    def sortColors(self, nums):
        """
        Do not return anything, modify nums in-place instead.
        """
        if len(nums)>1:
            left = 1
            right = len(nums)-1
            while(left<=right):
                temp = left
                while((nums[temp]<nums[temp-1])and(temp>=1)):
                    nums[temp],nums[temp-1] = nums[temp-1],nums[temp]
                    temp -= 1
                left+=1






