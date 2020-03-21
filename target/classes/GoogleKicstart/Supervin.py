
def findOne1():
    res = 0
    wordOne= input()
    count = 0
    N = len(wordOne)
    # print(N)
    if N == 1:
        return 1
    for i in range(N):
        # print(i)
        if i == N-1:
            return  1
        if int(wordOne[i])%2 == 0:
            continue
        else:
            if int(wordOne[i+1]) >=5:
                return 10**(N-1-i)-int(wordOne[i+1:])
            else:
                print(int(wordOne[i+1:])+1+int(''.join(['1' for k in range(N-i-1)])),10**(N-1-i)-int(wordOne[i+1:]))
                return min(int(wordOne[i+1:])+1+int(''.join(['1' for k in range(N-i-1)])),10**(N-1-i)-int(wordOne[i+1:]))
print(findOne1())

