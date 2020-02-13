# -- coding: gbk --
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def insertBSTNode(ROOT,data):
    tmp = ROOT
    while(True):
        if data > tmp.val:
            if tmp.right == None:
                tmp.right = TreeNode(data)
                break
            else:
                tmp = tmp.right
        else :
            if tmp.left == None:
                tmp.left = TreeNode(data)
                break
            else:
                tmp = tmp.left

def createBSTree(ROOT,list1):
    for i in list1:
        insertBSTNode(ROOT,i)

def finfValue(ROOT,target):
    pass

def printTree(root,order = "inorder"):
    if root :
        printTree(root.left)
        print(root.val)
        printTree(root.right)

def in_order_digui(root):
    res = []
    if root:
        return in_order_digui(root.left) + [root.val]+in_order_digui(root.right)
    else:
        return []
def in_order_diedai(root):
    '''
    这是深度优先遍历
    :param root:
    :return:
    '''
    res = []
    stack = []
    node = root
    while(node or stack):
        while node:
            stack.append(node)
            node = node.left
        node = stack.pop()
        res.append(node.val)
        node = node.right
    return res


if __name__ == '__main__':
    ROOT = TreeNode(-float("inf"))
    createBSTree(ROOT,[1,2,3,4,-5])
    printTree(ROOT)
    print(in_order_diedai(ROOT))
    print(in_order_digui(ROOT))

