class Solution:
    def countAndSay(self, n):
        if n == 1:
            return "1"
        if n == 2:
            return "11"
        lastAnser = self.countAndSay(n-1)
        print(n)
        print(lastAnser)
        res = ''
        count = 1
        lastAnser = lastAnser+"-"
        pre = lastAnser[0]
        for i in lastAnser[1:]:
            if i == pre:
                count+=1
            if i != pre:
                res =res+"{}{}".format(count,pre)
                count = 1
                pre = i
        return res
if __name__ == '__main__':
    t1 = Solution()
    t1.countAndSay(4)


