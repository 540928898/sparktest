class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
class Solution:
    def mergeTwoLists(self, l1,l2) :
        r1 = l1
        r2 = l2
        res = ListNode(-1)
        current = res
        while r1 or r2:
            if r1 == None :
                current.next = r2
                r2 = r2.next
                current = current.next
                continue
            if r2 == None :
                current.next = r1
                r1 = r1.next
                current = current.next
                continue
            if r1.val >= r2.val:
                current.next = r2
                r2 = r2.next
                current = current.next
                continue
            if r1.val < r2.val:
                current.next = r1
                r1 = r1.next
                current = current.next
                continue
        return res.next
