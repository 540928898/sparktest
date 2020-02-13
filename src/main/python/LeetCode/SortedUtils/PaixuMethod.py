# -- coding: gbk --
def maoPaoSort(list1):
    N = len(list1)
    for i in range(N):
        for j in range(i,N):
            list1[i],list1[j] = min(list1[i],list1[j]),max(list1[i],list1[j])
    return list1

def guibing(list1):
    pass

def charu(list1):
    pass


def partition(list1,left,right):
    '''
    这个partition：
    先找到右边比他小的第一个值
    再找到左边比他大的第一个值
    最后交换第一个值和right的值
    :param list1:
    :param left:
    :param right:
    :return:
    '''
    flag = list1[left]
    low = left
    while left < right:
        while list1[right] >= flag and right > left:
            right -= 1
        while list1[left] <= flag and right > left:
            left += 1
        list1[right],list1[left] = list1[left],list1[right]
    list1[low],list1[right] = list1[right],list1[low]
    return right

def kuaipai(list1,low,high):
    left = low
    right = high
    # print(list1)
    if low < high:
        p = partition(list1,left,right)
        # print(p)
        kuaipai(list1,left,p-1)
        kuaipai(list1,p+1,right)
        # print(list1)
        # return list1
def quickSort(list1):
    left = 0
    right = len(list1)-1
    if right <= 0:
        return list1
    p = partition(list1,left,right)
    return quickSort(list1[:p])+[list1[p]]+quickSort(list1[p+1:])


def xierSort(list1):
    pass

def duipai(list1):
    pass


if __name__ == '__main__':
    # print(maoPaoSort([43,12,43,34,2,36,8,9]))
    list1 =[43,12,34,43,2,36,8,9]

    print(quickSort(list1))
    print(list1)
    kuaipai(list1,0,len(list1)-1)
    print(list1)