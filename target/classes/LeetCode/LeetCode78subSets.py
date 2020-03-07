def subsets(nums) :
    res = [[]]
    def getres(nums0,nums1):
        if len(nums1) > 0:
            for i in range(len(nums1)):
                print(nums0)
                temp = nums0+[nums1[i]]
                res.append(temp)
                getres(temp,nums1[i+1:])
    getres([],nums)
    return res
nums = [1,2,3]
res = subsets(nums)