class Solution:
    """
    使用两次二分法，先对列，后对行进行二分
    """
    def searchMatrix(self, matrix,target):
        up =0
        down = len(matrix)-1
        row = 0
        col = 0
        if (len(matrix) == 0) or (len(matrix[0])==0):
            return False
        while(up < down):
            temp = int((up+down)/2)+1
            if matrix[temp][0] < target:
                up = temp
            if matrix[temp][0] > target:
                down = temp-1
            if matrix[temp][0] == target:
                return True
        row = up
        left = 0
        right = len(matrix[0])-1
        while(left<right):
            temp = int((left+right)/2)+1
            if matrix[row][temp]<target:
                left = temp
            if matrix[row][temp]>target:
                right = temp - 1
            if matrix[row][temp] == target:
                return True
        col = left
        return matrix[row][col] == target