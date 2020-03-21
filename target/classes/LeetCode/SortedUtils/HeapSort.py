import math


class HeapClass:
    def maxHeapOne(self,list2,index1):
        if index1*2+2 <= len(list2)-1:
            leftChild = index1*2+1
            rightChild = index1*2+2
            # print(list2,index1,leftChild,rightChild)
            if list2[index1] < list2[leftChild]:  #判断 左子节点和右节点谁大，然后调用递归函数
                list2[leftChild],list2[index1] = list2[index1],list2[leftChild]
                self.maxHeapOne(list2,leftChild)
            if list2[rightChild] > list2[index1]:
                list2[rightChild],list2[index1] = list2[index1],list2[rightChild]
                self.maxHeapOne(list2,rightChild)
    def createHeap(self,list2):
        N = len(list2)
        cur = N-1
        start = (cur-1)>>1
        while start >= 0:#对最后一个子节点的父节点开始遍历。对每一个节点都构建最大堆。
            # print(start)
            self.maxHeapOne(list2,start)
            start -= 1
        return  list2
    def headSorted(self,list2):
        if len(list2) <= 1:
            return list2
        # list2 = self.createHeap(list2)
        list2[0],list2[-1] = list2[-1],list2[0]
        tmp = list2.pop()
        self.maxHeapOne(list2,0)
        return self.headSorted(list2)+[tmp]
def printTree(array):
    array = [0] + array
    index = 1
    depth = math.ceil(math.log(len(array), 2))
    sep = ' '
    for i in range(int(depth)):
        offset = 2 ** i
        print(sep * (2 ** (int(depth) - i - 1) - 1), end = ' ')
        line = array[index:index+offset]
        for j, x in enumerate(line):
            print("{:>{}}".format(x, len(sep)), end = ' ')
            interval = 0 if i == 0 else 2 ** (int(depth) - i) - 1
            if j < len(line) - 1:
                print(sep * interval, end = ' ')
        index += offset
        print()

if __name__ == '__main__':
    t1 = HeapClass()

    tmp = t1.createHeap([2 ,5, 3, 9, 7, 1, 6])
    print(t1.headSorted(tmp))

    printTree([2 ,5, 3, 9, 7, 1, 6,12,23,11,1,1,2,1])
    # print(t1.createHeap()
    # list2 = t1.createHeap([2 ,5, 3, 9, 7, 1, 6])
