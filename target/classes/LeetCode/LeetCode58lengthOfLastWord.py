class Solution:
    def lengthOfLastWord(self, s):
        res = 0
        for i in s[::-1]:
            if i != ' ':
                res+=1
            else:
                return res
        return res


if __name__ == '__main__':
    t1 = Solution()
    print(t1.lengthOfLastWord("a"))