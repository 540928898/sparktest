class Solution:
    res = 0
    def maxDepth(self, root) :
        # res = 1
        def findDepth(root,curdepth):
            self.res = max(curdepth,self.res)
            if root:
                findDepth(root.left,curdepth+1)
                findDepth(root.right,curdepth+1)
        findDepth(root,0)
        return self.res