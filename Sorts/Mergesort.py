__author__ = 'Meemaw'


def MergeSort(aList):
    if(len(aList) > 1):
        mid = int(len(aList)/2)
        firstHalf = aList[0:mid]
        secondHalf = aList[mid:]
        MergeSort(firstHalf)
        MergeSort(secondHalf)
        Merge(firstHalf,secondHalf,aList)



def Merge(firstHalf, secondHalf, aList):
    leftCount, rightCount, globalCount = 0, 0, 0
    while leftCount < len(firstHalf) and rightCount < len(secondHalf):
        if firstHalf[leftCount] < secondHalf[rightCount]:
            aList[globalCount] = firstHalf[leftCount]
            leftCount+=1
            globalCount+=1
        else:
            aList[globalCount] = secondHalf[rightCount]
            rightCount+=1
            globalCount+=1

    remaining = firstHalf if leftCount < rightCount else secondHalf
    zacni = leftCount if remaining == firstHalf else rightCount
    while(zacni < len(remaining)):
        aList[globalCount] = remaining[zacni]
        zacni+=1
        globalCount+=1
    return aList

