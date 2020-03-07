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
    def trap2(self, height) :
        '''
        使用双指针，记录最大值
        :param height:
        :return:
        '''
        left = 0
        right = len(height)-1
        left_max = 0
        right_Max = 0
        res = 0
        while(left < right):
            if height[left] < height[right]:
                if height[left] >= left_max:
                    left_max = height[left]
                else:
                    res += left_max-height[left]
                left += 1
            else:
                if height[right] >= right_Max:
                    right_Max = height[right]
                else:
                    res += right_Max-height[right]
                right -= 1
        return res







