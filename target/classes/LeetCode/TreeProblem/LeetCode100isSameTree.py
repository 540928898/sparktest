# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from LeetCode.TreeProblem.TreeUtils import *
class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if p != None and q != None:
            return  self.isSameTree(p.left,q.left) and (p.val == q.val)  and self.isSameTree(p.right,q.right)
        else:
            return p == q

# if __name__ == '__main_
