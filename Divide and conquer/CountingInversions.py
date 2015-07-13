__author__ = 'Meemaw'


def countInversion(aList):
    total = 0
    if len(aList) == 1:
        return 0
    mid = int(len(aList)/2)
    firstHalf = aList[:mid]
    secondHalf = aList[mid:]
    leftInv = countInversion(firstHalf)
    rightInv = countInversion(secondHalf)
    splitInv = Merge(firstHalf,secondHalf,aList)
    return leftInv+rightInv+splitInv


def Merge(firstHalf, secondHalf, aList):
    leftCount, rightCount, globalCount, total = 0, 0, 0, 0
    while leftCount < len(firstHalf) and rightCount < len(secondHalf):
        if firstHalf[leftCount] < secondHalf[rightCount]:
            aList[globalCount] = firstHalf[leftCount]
            leftCount+=1
            globalCount+=1
        else:
            aList[globalCount] = secondHalf[rightCount]
            total += len(firstHalf) - leftCount
            rightCount+=1
            globalCount+=1

    remaining = firstHalf if leftCount < rightCount else secondHalf
    zacni = leftCount if remaining == firstHalf else rightCount
    while(zacni < len(remaining)):
        aList[globalCount] = remaining[zacni]
        zacni+=1
        globalCount+=1
    return total

