# -- coding: gbk --
def maoPaoSort(list1):
    N = len(list1)
    for i in range(N):
        for j in range(i,N):
            list1[i],list1[j] = min(list1[i],list1[j]),max(list1[i],list1[j])
    return list1

# 归并排序
def mergeTwoArray(list1,list2):
    res = []
    N1 = len(list1)
    N2 = len(list2)
    flag1 = 0
    flag2 = 0
    while flag1 < N1 or flag2 < N2:
        # print(flag1,N1,flag2,N2)
        if flag1 >= N1:
            res += list2[flag2:]
            break
        if flag2 >= N2:
            res += list1[flag1:]
            break
        if list1[flag1] < list2[flag2]:
            res.append(list1[flag1])
            flag1 += 1
            continue
        if list1[flag1] >= list2[flag2]:
            res.append(list2[flag2])
            flag2+=1
    return res
def guibing(list1):
    N = len(list1)
    if N <= 1:
        return list1
    tt = mergeTwoArray(guibing(list1[:N//2]),guibing(list1[N//2:]))
    return tt

def findValue(res,target):
    N2 = len(res)
    count = 0
    while(count < N2 and res[count] < target):
        count += 1
    return count
def charu(list1):
    N = len(list1)
    if N <= 1:
        return list1
    res = [list1[0]]
    for i in range(1,N):
        #找到目标值
        k = findValue(res,list1[i])
        # 插入
        res.insert(k,list1[i])
    return res

def selectSort(list1):
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

def tongSorted(list2):#桶排序
    pass

def jishuSorted(list2):#计数排序
    pass

def jishu2Sorted(list2): #基数排序
    pass


if __name__ == '__main__':
    # print(maoPaoSort([43,12,43,34,2,36,8,9]))
    list1 =[43,12,34,43,2,36,8,9]
    print(quickSort(list1))
    print(list1)
    kuaipai(list1,0,len(list1)-1)
    print(list1)
    print(mergeTwoArray([4],[5]))
    print(guibing([4,5,5,5,5,6,1,2,3]))
    print(charu([0,4,1,3,4,6,10]))

