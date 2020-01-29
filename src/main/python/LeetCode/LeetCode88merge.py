class Solution:
    def merge(self, nums1, m, nums2, n) :
        """
        Do not return anything, modify nums1 in-place instead.
        """
        res1 = nums1[:m]
        nums1[:] = []
        i ,j = 0,0
        while(i<m or j < n):
            if i >= m :
                nums1.append(nums2[j])
                j+=1
                continue
            if j >=n :
                nums1.append(res1[i])
                i+=1
                continue
            if res1[i]<nums2[j]:
                nums1.append(res1[i])
                i+=1
            elif res1[i]>nums2[j]:
                nums1.append(nums2[j])
                j+=1
            else:
                nums1.append(res1[i])
                nums1.append(nums2[j])
                j+=1
                i+=1
        return nums1



if __name__ == '__main__':
    t1 = Solution()
    print(t1.merge([1,2,3,0,0,0],3,[2,5,6],3))