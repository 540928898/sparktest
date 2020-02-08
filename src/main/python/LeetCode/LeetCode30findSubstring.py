import copy


class Solution:
    def findSubstring(self, s, words):
        '''
        这个方法是错的，非常气，思路错了，直接这样切分字符串会导致错误
        :param s:
        :param words:
        :return:
        '''
        # dic1 = {i:0 if i in dic1 else i:dic1[i]+1 for i in words}
        dic1={}
        for i in words:
            if i in dic1:
                dic1[i] = dic1[i]+1
            else:
                dic1[i] = 1
        print(dic1)

        current = 0
        Next = 0
        res = []
        cat = len(words[0])
        total = len(words)
        totalcat = cat*total
        #一次探查 输入当前指针，返回最后匹配指针
        def caulateOne(current,dic):
            start = current
            count = 0
            tmpdic = dic
            print(current,tmpdic)
            while(True):
                tmpword = s[current:current+cat]
                print("Beforecurrent",current,tmpword,tmpdic)
                if  tmpword in tmpdic and tmpdic[tmpword] > 0:
                    tmpdic[tmpword] -= 1
                    count += 1
                    current +=cat
                    # print("Aftercurrent",current,tmpword,tmpdic)
                    # print("count",count)
                    if count == total:
                        res.append(start)
                        return start+cat
                elif tmpword in tmpdic and tmpdic[tmpword] == 0:
                    print("goodnum",tmpdic[tmpword])
                    #寻找第一个tmpword
                    while(s[start:start+cat] != tmpword):
                        start += cat


                    return start+1
                else:
                    return current
        print(len(s),totalcat)
        while(current <= len(s) - totalcat):
            print("当前循环",current)
            nextcurrent=caulateOne(current,copy.deepcopy(dic1))
            if current == nextcurrent:
                nextcurrent += 1
            current = nextcurrent
        return res
if __name__ == '__main__':
    t1 = Solution()
    print(t1.findSubstring("ababaab"
                     ,["ba","ba","ab"]))




