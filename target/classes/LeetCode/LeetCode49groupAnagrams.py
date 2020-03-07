class Solution:
    def groupAnagrams(self, strs):
        dic={}
        for i in strs:
            a =tuple(sorted(i))
            if a not in dic:
                dic[a] = [i]
            else:
                dic[a].append(i)
        m=[i for i in dic.values()]
        return m
    def groupAnagrams2(self,strs):
        '''
        使用位图的思想
        :param strs:
        :return:
        '''