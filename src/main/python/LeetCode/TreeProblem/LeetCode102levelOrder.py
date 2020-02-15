# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from LeetCode.TreeProblem.TreeUtils import *

'''

二叉树的层次遍历 有两种方法，一种是递归，另一种是迭代

还有一种方法是判断第几层，如果是相同的一层就在res的第几层进行append，类似于一种DFS的BFS算法
'''
class Solution:
    def levelOrder(self, root: TreeNode) : # 迭代的做法
        if not root:return []
        res = []
        stack1 = [root]
        while stack1:
            res.append([])
            N = len(stack1)
            for i in range(N):
                tmp = stack1.pop(0)
                if tmp.left:
                    stack1.append(tmp.left)
                if tmp.right:
                    stack1.append(tmp.right)
                res[-1].append(tmp.val)
        return res
    def levelOrder2(self, root: TreeNode):
        res = []
        def findBFS(tmp):
            if not tmp :return
            reszi = []
            nexttmp = []
            for i in tmp:
                if i:
                    reszi.append(i.val)
                    if i.left:
                        nexttmp.append(i.left)
                    if i.right:
                        nexttmp.append(i.right)
            if reszi:
                res.append(reszi)
            findBFS(nexttmp)
        findBFS([root])
        return res

    def levelOrder3(self,root):
        '''
        树的遍历中加入深度
        :param root:
        :return:
        '''
        if not root: return []
        res = []
        def findLEvel(curRoot,level):
            if len(res) <=level:
                res.append([])
            res[level].append(curRoot.val)
            if curRoot.left:
                findLEvel(curRoot.left,level+1)
            if curRoot.right:
                findLEvel(curRoot.right,level+1)
        findLEvel(root,0)
        return res



