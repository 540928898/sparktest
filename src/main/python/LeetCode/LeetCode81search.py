class Solution:
    def search(self, nums, target) :
        left,right = 0,len(nums)-1
        if len(nums) == 0:
            return False
        if len(nums) == 1:
            return nums[0] == target
        def binasearch(left,right):
            while(left < right):
                mid = int((left+right)/2)
                if nums[mid] == nums[right]: #害怕出现1131 或者3111这种情况 使用 if elseif 或者都是if  取决于前面right是否会发生改变
                    right = right -1
                else:
                    if target > nums[right]: # target 在左半部分
                        if nums[mid] > nums[right]:#中间值在左半部分
                            if nums[mid] > target:
                                right = mid-1
                            if nums[mid] < target:
                                left = mid+1
                            if nums[mid] == target:
                                return True
                        else :#中间值在右半部分
                            right = mid -1
                    elif target < nums[right]:#target在右半部分
                        if nums[mid] > nums[right]:#中间值在左半部分
                            left = mid+1
                        if nums[mid] < nums[right]:#中间值在右半部分
                            if nums[mid] > target:
                                right = mid-1
                            if nums[mid] < target:
                                left = mid+1
                            if nums[mid] == target:
                                return True
                    else:
                        return True
            return nums[left] == target
        return binasearch(left,right)



if __name__ == '__main__':
    t1 = Solution()
    print(t1.search([4,5,6,7,0,1,2],4))

