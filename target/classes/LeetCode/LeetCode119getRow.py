class Solution:
    def getRow(self, rowIndex) :
        '''
        现在前面加一个0 然后迭代相加
        :param rowIndex:
        :return:
        '''
        res = [1]
        for i in range(1,rowIndex+1):
            res.insert(0,0)
            for j in range(i):
                res[j] = res[j]+res[j+1]
        return res
    def getRow2(self, rowIndex) :
        '''
        使用公式，后一个数是前一个数的（n-k+1)/k倍！！！
        :param rowIndex:
        :return:
        '''
        res = [1]
        for i in range(1,rowIndex+1):
            res.append(int(res[-1]*(rowIndex-i+1)/i))
        return res


