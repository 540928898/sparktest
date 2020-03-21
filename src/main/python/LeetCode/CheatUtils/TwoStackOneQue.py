def TwoStackOneQuere(list1):
    stack1 = []
    stack2 = []
    for i in list1:
        stack1.append(i)
    if not stack2:
        while stack1:
            stack2.append(stack1.pop())
    while stack2:
        print(stack2.pop())
def TwoQueOneStack(listRaw):
    list1 = []
    list2 = []
    N = len(listRaw)
    for i in listRaw:
        list1.append(i)
    #正式开始
    while(list1):
        n1 = len(list1)
        for k in range(n1-1):
            list2.append(list1.pop(0))
        print(list1.pop())
        list1,list2 = list2,list1
TwoStackOneQuere([1,2,3,4,5,6])
TwoQueOneStack([1,2,3,4,5,6])




