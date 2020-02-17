class Solution:
    def trap(self, height):
        '''
        按照行 这个方法会超时
        :param height:
        :return:
        '''
        N = len(height)
        if N <= 1:
            return 0
        res = 0
        Max =  max(height)
        for level in range(1,Max+1):
            # print(res)
            left = 0
            right = N-1
            while height[left] < level or height[right] <level:
                left += height[left] < level
                right -= height[right] < level
            for i in range(left,right+1):
                res += height[i] <level
        return res





