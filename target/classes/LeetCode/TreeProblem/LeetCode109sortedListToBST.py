# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from LeetCode.TreeProblem.TreeUtils import *

class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:
        list1 = []
        start = head
        while(start):
            list1.append(start.val)
            start = start.next
        def sortedArrayToBST(nums) :
            right = len(nums)
            mid = (0+right)//2
            if right == 0:
                return
            root = TreeNode(nums[mid])
            root.left = sortedArrayToBST(nums[:mid])
            root.right = sortedArrayToBST(nums[mid+1:])
            return root
        return sortedArrayToBST(list1)