
from LeetCode.TreeProblem.TreeUtils import *

class Solution:
    def rotateRight(self, head, k) :
        if not head:
            return head
        N = 1
        end = head
        newHead = head
        while(end.next):
            N += 1
            end = end.next
        end.next = newHead

        count = 1
        xunhuan = N- k%N
        while count <= xunhuan:
            end = end.next
            newHead = newHead.next
            count += 1
        end.next = None
        return newHead
