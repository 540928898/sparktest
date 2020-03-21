
from LeetCode.TreeProblem.TreeUtils import *

class Solution:
    def sortedArrayToBST(self, nums) :
        right = len(nums)
        mid = (0+right)//2
        if right == 0:
            return
        root = TreeNode(nums[mid])
        root.left = self.sortedArrayToBST(nums[:mid])
        root.right = self.sortedArrayToBST(nums[mid+1:])
        return root