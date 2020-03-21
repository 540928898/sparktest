class Solution:
    def isNumber(self, s) :
        '''
        状态机转换
        :param s:
        :return:
        '''
        finals = [0,0,0,1,0,1,1,0,1]
        transfer = [[ 0, 1, 6, 2,-1,-1],
                    [-1,-1, 6, 2,-1,-1],
                    [-1,-1, 3,-1,-1,-1],
                    [ 8,-1, 3,-1, 4,-1],
                    [-1, 7, 5,-1,-1,-1],
                    [ 8,-1, 5,-1,-1,-1],
                    [ 8,-1, 6, 3, 4,-1],
                    [-1,-1, 5,-1,-1,-1],
                    [ 8,-1,-1,-1,-1,-1]]
        dic1 = {" ":0,"+":1,"-":1,".":3,"e":4}
        for i in range(10):
            dic1["{}".format(i)] = 2
        print(dic1)
        nowstate = 0
        cur=transfer[nowstate]
        for i in s:
            if i in dic1:

                nowstate = cur[dic1[i]]

                cur = transfer[nowstate]
                if nowstate == -1:
                    return False
            else:
                return False
        print(nowstate)
        return finals[nowstate]
if __name__ == '__main__':
    t1 = Solution()
    print(t1.isNumber("0"))
