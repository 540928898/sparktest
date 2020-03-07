class Solution:
    def longestValidParentheses(self, s) :
        dp = [0 for i in range(len(s))]
        print(dp)
        if len(s) == 0:
            return 0
        for i in range(1,len(s)):
            if s[i] == ')':
                if s[i-1] == '(':
                    dp[i] = dp[i-2]+2
                if s[i-1] == ')':
                    if i-dp[i-1]-1 <0:
                        continue
                    if s[i-dp[i-1]-1] == '(':
                        dp[i] = dp[i-1]+2+dp[i-dp[i-1]-2]
        return max(dp)
