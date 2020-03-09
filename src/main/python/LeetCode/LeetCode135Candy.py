class Solution:
    def candy(self, ratings):
        N = len(ratings)
        res = 1
        up = 1
        down = 0
        if N <= 1:
            return N
        for i in range(1,N):
            if ratings[i] >=ratings[i-1]:
                if down > 0 :
                    res += (1+down)*down/2
                    if up <= down: res +=  down-up+1
                    up = 1
                    down = 0
                if ratings[i] == ratings[i-1]:
                    up = 1
                else:
                    up += 1
                res += up
            if ratings[i] < ratings[i-1]:
                down += 1
        if down > 0:
            res += (1+down)*down/2
            if up <= down: res = res + down-up+1
        return int(res)


if __name__ == '__main__':
    t1 = Solution()
    print (t1.candy([1,2,87,87,87,2,1]))

