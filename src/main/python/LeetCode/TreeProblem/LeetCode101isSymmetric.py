# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from LeetCode.TreeProblem.TreeUtils import *

class Solution:
    '''
    采用两种方法，一种递归，一种迭代，迭代的话主要是二叉树的层次遍历，使用一个栈，一个一个往后加就可以了。
    '''
    def isSymmetric(self, root: TreeNode) -> bool:
        def findMirror(root1,root2):
            if root1 == None or root2 == None :
                if root1 == root2:
                    return True
                else:
                    return False
            return  root1.val == root2.val and findMirror(root1.left,root2.right) and findMirror(root1.right,root2.left)
        if not root: return True
        return findMirror(root.left,root.right)
    def isSymmetric2(self,root):
        if not root: return True
        stacks = [root.left,root.right]
        # level = 0
        # tmp = []
        while(stacks):
            tmp1 = stacks.pop(0)
            tmp2 = stacks.pop(0)
            if tmp1 == None  or tmp2 == None :
                if tmp1 == tmp2 :
                    continue
                else: return False
            if tmp1.val == tmp2.val:
                stacks.extend([tmp1.left,tmp2.right,tmp1.right,tmp2.left])
            else: return False
        return True




        # def check(list1):
        #     N = len(list1)
        #     if N <= 1:
        #         return True
        #     for i in range(N//2):
        #         if list1[i] != list1[N-1-i]:
        #             return False
        #     return True
        # #二叉树的层次遍历
        # if not root: return True
        # stacks = [root]
        # level = 0
        # tmp = []
        # for i in range(len(stacks)):
        #     tmpNode = stacks.pop(0)
        #     if tmpNode != None:
        #         tmp.append(tmpNode.val)
        #         stacks.append(tmpNode.left if tmpNode.left else None)
        #         stacks.append(tmpNode.right if tmpNode.right else None)
        #     else:
        #         tmp.append(None)

        # check(tmp)
