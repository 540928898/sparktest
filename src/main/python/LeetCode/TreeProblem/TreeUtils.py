class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
def findOneValue(root,target):
    pass
def createBinaryTree(list1,order = "inorder"):
    root = TreeNode(0);
    for num in list1[1:]:
        tmp = TreeNode(num)
        tmpIndex = root
        while(tmpIndex):
            if num < tmpIndex.val:
                tmpIndex = tmpIndex.left
            if num > tmpIndex.val:
                tmpIndex = tmpIndex.right
        if num < root.val:
            pass
    pass
def printTree(root,order = "inorder"):
    res = []
    if root :
        printTree(root.left)
        res.append(root.val)
        printTree(root.left)
    else:
        return
    print(res)
def in_order_digui(root):

    pass
def in_order_diedai(root):
    pass
def pre_order(root):
    pass
def after_order(root):
    pass
