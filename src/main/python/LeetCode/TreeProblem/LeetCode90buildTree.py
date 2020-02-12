# Definition for a binary tree node.
from LeetCode.TreeProblem.TreeUtils import *
'''
这道题的重点在于如何节约时间
也就是说我们在使用
indexHeader = inorder.index(preorder[0])
的时候可以缩小查询范围，加入left，right，缩小范围，可以加快查询速度
使用字典，直接查找
idx_map = {val:idx for idx, val in enumerate(inorder)} 
具体参考106的后续和中序键树
'''
class Solution:
    ROOT = TreeNode(-1);
    def buildTree(self, preorder, inorder):
        n = len(preorder)
        if n == 0:
            return
        if n == 1:
            return TreeNode(preorder[0])
        rootNode = TreeNode(preorder[0])
        indexHeader = inorder.index(preorder[0])
        rootNode.left = self.buildTree(preorder[1:indexHeader+1],inorder[:indexHeader])
        rootNode.right = self.buildTree(preorder[indexHeader+1:],inorder[indexHeader+1:])
        return rootNode
    def printTree(self,rootNode):
        if rootNode:
            print(rootNode.val)
            self.printTree(rootNode.left)
            self.printTree(rootNode.right)


if __name__ == '__main__':
    t1 = Solution();
    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]
    t1.ROOT = t1.buildTree(preorder,inorder)
    t1.printTree(t1.ROOT)