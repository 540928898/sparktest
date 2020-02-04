class Solution:
    '''
    使用两个
    '''
    def findPeakElement(self, nums):
        def solution_1(nums):
            N = len(nums)
            nums = [-float("inf")]+nums+[-float("inf")]
            res = []
            for i in range(1,N+1):
                if nums[i] > nums[i+1] and nums[i] > nums[i-1]:
                    return i-1
        def solution_2(nums):
            N = len(nums)
            left = 0
            right = N-1
            while left < right:
                tmp = (left+right) //2

                if nums[tmp] > nums[tmp+1]:
                    right =tmp
                else:
                    left = tmp+1
            return left

        return self.search(nums,0,len(nums)-1)
    def search(self,nums,left,right):
        if(left == right):
            return left
        mid = int((left+right)/2)
        if(nums[mid] > nums[mid+1]):
            return self.search(nums,left,mid)
        else:
            return self.search(nums,mid+1,right)

