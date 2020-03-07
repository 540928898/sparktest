class Solution:
    def majorityElement(self, nums) :

        def solution_1(nums):
            r=None
            c=0
            for num in nums:
                if c==0:
                    r=num
                c+=1 if num==r else -1
            return r
        def solution_2(nums):
            r = None
            count = 0
            for i in nums:
                if count == 0:
                    r = i
                count += 1 if i == r else -1
            return r
