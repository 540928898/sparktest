def inputRow():
    s1  = int(input())
    res = []
    for i in range(s1):
        s2 = [ int(i) for i in input().split(" ")]
        s3 = [int(i) for i in input().split(" ")]
        res.append(findOne(s2,s3))
    return res

def findOne(s2,s3):
    cur = s3[0]
    window = []
    minWindow = s3[0]
    res = 0
    for i,j in enumerate(s3):
        if i <s2[1]:

            if cur < j:
                res = res + (j-cur)*i
                cur = j
            if cur >=j:
                minWindow = min(j,minWindow)
                res = res + cur - j
        else:
            if res == 0:
                return 0
            if cur < j:
                continue
            else:
                if  j > minWindow:
                    res  = res - (cur-minWindow)+(cur-j)
                    minWindow = j
                else:
                    continue
    return res

# print(findOne([4,3],[3,1,9,100]))
print(inputRow())