class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
class GenList:
    head = ListNode(-1)
    def createListNode(self,list1):
        for i in list1:
            newNode = ListNode(i)
            newNode.next = self.head.next
            self.head.next = newNode
    def printList(self,head):
        temp = head
        while temp:
            print(temp.val)
            temp = temp.next
    def reverseList(self,head):
        pre = None
        cur =head
        nextNode = cur
        while cur:
            nextNode = cur.next
            cur.next = pre
            pre = cur
            cur = nextNode
        self.head = pre
    def reverseListOne(self,a,b):
        pre = None
        cur = a
        nextNode = cur
        while cur != b:
            nextNode = cur.next
            cur.next = pre
            pre = cur
            cur = nextNode
        return b
    def duizheList(self):
        pass

    def mergeTwoLists(self,head1,head2):
        temp1 = head1
        temp2 = head2
        NewHead = ListNode(-2)
        while temp2 or temp1:
            if temp1:
                next1 = temp1.next
                temp1.next = NewHead.next
                NewHead.next = temp1
                temp1 = next1
            if temp2:
                next2 = temp2.next
                temp2.next = NewHead.next
                NewHead.next = temp2
                temp2 = next2
        return NewHead


if __name__ == '__main__':
    t1 = GenList()
    t2 = GenList()
    t1.createListNode([1,2,3,4,5])
    # t2.createListNode([5,4,3,2,1])
    t1.printList(t1.head)
    # t1.printList(t1.mergeTwoLists(t1.head,t2.head))
