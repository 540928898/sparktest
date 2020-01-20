class Solution:
    def largestRectangleArea(self, heights):
        '''
        可惜超时了！！ 分治的思想
        :param heights:
        :return:
        '''
        def maxyou(heights):
            if len(heights) == 2:
                return max(heights[0],min(heights)*2,heights[1])
            if len(heights) == 1:
                return heights[0]
            if len(heights) == 0:
                return 0
            minnum = min(heights)
            minindex = heights.index(minnum)
            return max(maxyou(heights[:minindex]),len(heights)*minnum,maxyou(heights[minindex+1:]))
        return maxyou(heights)
    def largestRectangleArea2(self,heights):
        stack = []
        heights = [0] + heights + [0]
        res = 0
        for i in range(len(heights)):
            #print(stack)
            while stack and heights[stack[-1]] > heights[i]:
                tmp = stack.pop()
                res = max(res, (i - stack[-1] - 1) * heights[tmp])
            stack.append(i)
        return res


        # stack1 = [(-1,0)]
        # res = 0
        # for i,j in enumerate(heights):
        #     if j >= stack1[-1][1]:
        #         stack1.append((i,j))
        #     else:
        #         temp = stack1.pop()
        #         index0 = temp[0]
        #         index1 = temp[1]
        #         while(len(stack1) >1 and stack1[-1][1] > j):
        #             temp = stack1.pop()
        #             res = max(index1,(index0-temp[0]+1)*temp[1],res)
        #         res = max(res,index1)
        #         stack1.append((i,j))
        # temp = stack1.pop()
        # index0 = temp[0]
        # index1 = temp[1]
        # while(len(stack1) > 1):
        #     temp = stack1.pop()
        #     res = max(index1,(index0-temp[0]+1)*temp[1],res)
        # return max(res,index1)


if __name__ == '__main__':
    t1 = Solution()
    print(t1.largestRectangleArea2([2,1,2]))
