class Solution:
    def solveSudoku(self, board):
        """
        Do not return anything, modify board in-place instead.
        """
        N = len(board)
        list1 = [str(i) for i in range(1,10)]
        rowdic = {i:[] for i in range(N)}
        colDic = {i:[] for i in range(N)}
        quanDix = {(i,j):[] for i in range(N//3)for j in range(N//3)}
        sudoku_solved = False
        for i in range(N):
            for j in range(N):
                if board[i][j] != '.':
                    rowdic[i].append(board[i][j])
                    colDic[j].append(board[i][j])
                    quanDix[(i//3,j//3)].append(board[i][j])
        def isValid(row,col,i):
            if i in rowdic[row] or i in colDic[col] or i in quanDix[row//3,col//3]:
                return False
            rowdic[row].append(i)
            colDic[col].append(i)
            quanDix[(row//3,col//3)].append(i)
            return True
        # def removeDic(row,col,)
        def resDfs(row,col,board):
            # print(row,col,N)
            if row ==N-1 and col == N-1:
                nonlocal sudoku_solved
                sudoku_solved = True
                return
            if board[row][col] != '.':
                if col == N-1:
                    resDfs(row+1,0,board)
                else:
                    resDfs(row,col+1,board)
            else:
                for i in list1:
                    if not isValid(row,col,i):
                        continue
                    board[row][col] = i
                    if col == N-1:
                        resDfs(row+1,0,board)
                    else:
                        resDfs(row,col+1,board)
                    if not sudoku_solved:
                        del rowdic[row][-1]
                        del colDic[col][-1]
                        del quanDix[(row//3,col//3)][-1]
        resDfs(0,0,board)


t1 = Solution()
t1.solveSudoku([["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]])
