class Solution:
    def lemonadeChange(self, bills) :
        '''
        使用贪心，先找10 后找5
        :param bills:
        :return:
        '''
        dic1 = {5:0,10:0}
        for i in bills:
            if i == 5:
                dic1[5] +=1
            if i == 10:
                if dic1[5] >=1:
                    dic1[5] -=1
                    dic1[10]+=1
                else:
                    return False
            if i == 20:
                if dic1[10] >= 1:
                    if dic1[5] >= 1:
                        dic1[10] -=1
                        dic1[5] -=1
                    else:
                        return False
                else:
                    if dic1[5] >= 3:
                        dic1[5] -=3
                    else:
                        return False
        return True