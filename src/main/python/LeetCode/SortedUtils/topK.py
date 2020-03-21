#TOPK问题 第一种是快速选择
#快速选择
import random
def partition(nums,left,right):
    target = nums[left]
    raw = left
    left=left+1
    while left<= right:
        while left<=right and nums[right] > target:
            right -= 1
        while left<= right and nums[left] < target:
            left += 1
        if left <= right:#这个需要注意。
            nums[left],nums[right] = nums[right],nums[left]
    nums[raw],nums[left-1] = nums[left-1],nums[raw]
    return left -1

def randomPartition(nums,left,right):
    target = random.randint(left,right)
    #随机取一个数然后交换他们的位置！！不然会出bug
    nums[target],nums[right] = nums[right],nums[target]
    pivo = partition(nums,left,right)
    return  pivo

def selectMinNum(nums,left,right,k):
    pivo = randomPartition(nums,left,right)
    if k == pivo - left + 1:
        return ;
    if k < pivo - left+1:
        selectMinNum(nums,left,pivo-1,k)
    if k > pivo - left+ 1:
        selectMinNum(nums,pivo+1,right,k-pivo+left-1)

def selectMaxNum(nums,left,right,k):
    pivo = randomPartition(nums,left,right)
    if k ==right-pivo+1:
        return;
    if k < right-pivo+1:
        selectMinNum(nums,pivo+1,right,k)
    if k > right-pivo+1:
        selectMinNum(nums,left,pivo-1,k-right+pivo-1)


#第二种是堆排序
if __name__ == '__main__':
    nums = [1,10,3,4,5,6,7,8]
    selectMinNum(nums,0,7,3)
    selectMaxNum(nums,0,7,3)
    print(nums[:3])
    print(nums[-3:])
