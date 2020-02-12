# Definition for a binary tree node.
from LeetCode.TreeProblem.TreeUtils import *
class Solution:
    def buildTree(self, inorder, postorder) :
        indexDic = {inorder[i]:i for i in range(len(postorder))}
        print(indexDic)
        def genTree(istart,iend,pstart,pend):
            print("Start")
            if istart > iend:
                return
            if istart== iend:
                return TreeNode(postorder[pend])
            rootNode = TreeNode(postorder[pend])
            indexNode = indexDic[postorder[pend]]
            rootNode.left = genTree(istart,indexNode-1,pstart,pend-(iend-indexNode+1))
            rootNode.right = genTree(indexNode+1,iend,indexNode,pend-1)
            return  rootNode
        return genTree(0,len(inorder)-1,0,len(postorder)-1)


if __name__ == '__main__':
    t1 = Solution()
    t1.buildTree([9,3,15,20,7],[9,15,7,20,3])


