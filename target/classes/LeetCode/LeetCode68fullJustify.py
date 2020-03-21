class Solution:
    def fullJustify(self, words, maxWidth) :
        res = []
        def findOne(words,maxWidth):
            resTmp = ''
            total = 0
            if len(words) == 0:
                return
            if len(words) == 1:
                res.append(words[0]+''.join([' ' for i in range(maxWidth-len(words[0]))]))
                return
            print(words)
            for i,j  in enumerate(words):
                if total+ len(j)+i <= maxWidth:
                    total+= len(j)
                else:
                    if i == 1 and total+ len(j)+1>maxWidth:
                        res.append(words[i-1]+''.join([' ' for k in range(maxWidth-len(words[i-1]))]))
                        break
                    # print(i,total)
                    # total+i-1 #当前总长度
                    # maxWidth - total-i+1 # 还需要的空格数
                    # i-1 #间隔数
                    needAdd = int((maxWidth - total) / (i-1)) #每个间隔需要增加的空格
                    stilAdd =(maxWidth-total) %(i-1) # 前几个间隔需要增加1个空格
                    for k in range(i-1):
                        resTmp = resTmp+words[k]+''.join([' ' for tt in range(needAdd+1)]) if k < stilAdd else resTmp+words[k]+''.join([' ' for mm in range(needAdd)])
                    resTmp += words[i-1]
                    # print(resTmp)
                    res.append(resTmp)
                    break
            findOne(words[i:],maxWidth)
        findOne(words,maxWidth)
        return res


if __name__ == '__main__':
    t1 = Solution()
    print(t1.fullJustify(["What","must","be","acknowledgment","shall","be"],16))






