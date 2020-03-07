class Solution:
    def twoSum(self, numbers, target) :
        dic1 = {}
        for i,j in enumerate(numbers):
            if  j in dic1:
                return [dic1[j]+1,i+1]
            dic1[target-j] = i