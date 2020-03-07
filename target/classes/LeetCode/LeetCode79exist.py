class Solution:
    def exist(self, board, word):
        row = len(board)
        col = len(board[0])
        def startSearch(word,i,j,used,row = row,col = col):
            flag = False
            t1 = False
            t2 = False
            t3 = False
            t4 = False
            if(len(word)==0):
                return True
            #从四个方向开始搜索：
            #上
            if (i > 0) and (board[i-1][j] == word[0])and([i-1,j] not in used):
                t1 = startSearch(word[1:],i-1,j,used+[[i-1,j]])
                if t1 == True:
                    return True
            #左
            if(j > 0) and (board[i][j-1] == word[0])and([i,j-1] not in used):
                # used.append([i,j-1])
                t2=startSearch(word[1:],i,j-1,used+[[i,j-1]])
                if t2 == True:
                    return True
            #右
            if(j <= col-2) and (board[i][j+1] == word[0])and([i,j+1] not in used):
                # used.append([i,j+1])
                t3=startSearch(word[1:],i,j+1,used+[[i,j+1]])
                if t3 == True:
                    return True
            #下
            if(i <= row-2) and (board[i+1][j] == word[0])and([i+1,j] not in used):
                # used.append([i+1,j])
                t4=startSearch(word[1:],i+1,j,used+[[i+1,j]])
                if t4 == True:
                    return True
            return flag
        flag = False
        for i in range(row):
            for j in range(col):
                if board[i][j] == word[0]:
                    flag = startSearch(word[1:],i,j,[[i,j]])
                if flag == 1:
                    return True
        return False

t1 = Solution()
board = [["A","A"]]

print(t1.exist(board,"AAA"))






