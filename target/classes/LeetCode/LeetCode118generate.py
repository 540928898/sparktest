
'''
杨辉三角
'''
class Solution:

    def generate(self, numRows) :
        if numRows == 0:
            return []
        if numRows == 1:
            return [[1]]
        if numRows == 2:
            return [[1],[1,1]]
        res = [[1],[1,1]]
        for k in range(1,numRows-1):
            res.append([1]+[res[-1][i]+res[-1][i+1] for i in range(k)]+[1])
        return res