class Solution:
    def solveNQueens(self, n) :
        res = []
        board = [['.' for i in range(n)] for k in range(n)]
        def dfs1(CountQue,board):
            if CountQue == n:
                tmp = []
                for i in board:
                    tmp.append(''.join(i))
                res.append(tmp)
                #
                return
            for i in range(n):
                if not isValied(board,CountQue,i):
                    continue
                board[CountQue][i] = "Q"
                dfs1(CountQue+1,board)
                board[CountQue][i] = '.'
        def isValied(board,CountQue,i):
            for k in range(CountQue):
                if board[k][i] == 'Q':
                    return False
            #只要保证右上和左上就可以保证
            for  k in range(i+1,n):
                if board[CountQue+i-k][k] == 'Q':
                    return  False
            for k in range(0,i):
                if board[CountQue-i+k][k] == 'Q':
                    return False
            return True
        dfs1(0,board)
        return res
if __name__ == '__main__':
    t1 = Solution()
    print(t1.solveNQueens(4))



