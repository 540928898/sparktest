class Solution:
    def numIslands(self, grid) :
        col = len(grid[0])
        row = len(grid)
        hisSearch=[[0]*col for i in range(row)]

        def dfs(curRow,curCol):
            if hisSearch[curRow][curCol] == 1:
                return
            if grid[curRow][curCol] == 1:
                hisSearch[curRow][curCol] = 1
                #上
                if curRow >= 1:
                    dfs(curRow-1,curCol)
                #下
                if curRow <row-1:
                    dfs(curRow+1,curCol)
                #左
                if curCol >=1:
                    dfs(curRow,curCol-1)
                #右
                if curCol < col -1:
                    dfs(curRow,curCol+1)
            return True
        res = 0
        for i in range(row):
            for j in range(col):
                if grid[i][j] == 0:
                    continue
                if dfs(row,col):
                    res += 1
        return res

t1 = Solution()