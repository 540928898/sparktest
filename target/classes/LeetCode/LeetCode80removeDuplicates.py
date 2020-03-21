class Solution:
    def removeDuplicates(self, nums):
        if len(nums) <=0:
            return len(nums)
        current = nums[0]
        count = 1
        jump = 0
        for i in range(1,len(nums)):
            if nums[i] != current:
                count = 1
                current = nums[i]
            else:
                count+=1
            if count >2:
                jump+=1
            else:
                nums[i-jump],nums[i] = nums[i],nums[i-jump]
        return len(nums)-jump

if __name__ == '__main__':
    s1 =  Solution()
    print(s1.removeDuplicates([1,2,2,3,4,4]))