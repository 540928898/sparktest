class Solution:
    def addBinary(self, a, b):
        Na = len(a)
        Nb = len(b)
        count = 1
        flag = 0
        res = ''
        while(count <= Na or count <= Nb):
            tmpA = a[-count] if count <=Na else 0
            tmpB = b[-count] if count <=Nb else 0
            # print(tmpA,tmpB)
            tmpRes = int(tmpA)+int(tmpB)+flag
            res = str(tmpRes%2)+res
            flag = int(tmpRes/2)
            # print(flag)
            count += 1
        if flag == 1:
            res ='1'+res
        return res
