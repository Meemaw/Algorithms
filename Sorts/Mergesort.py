__author__ = 'Meemaw'


def MergeSort(aList):
    if(len(aList) > 1):
        mid = int(len(aList)/2)
        firstHalf = aList[:mid]
        secondHalf = aList[mid:]
        MergeSort(firstHalf)
        MergeSort(secondHalf)

        Merge(firstHalf,secondHalf,aList)



def Merge(firstHalf, secondHalf, aList):
    leftCount, rightCount = 0, 0
    for i in range(len(aList)):

        leftValue = firstHalf[leftCount] if leftCount < len(firstHalf) else None
        rightValue = secondHalf[rightCount] if rightCount < len(secondHalf) else None

        if (leftValue and rightValue and leftValue < rightValue) or rightValue is None:
            aList[i] = leftValue
            leftCount+=1

        elif (leftValue and rightValue and leftValue >= rightValue) or leftValue is None:
            aList[i] = rightValue
            rightCount+=1

        else:
            raise Exception("Could not merge sub arrays")

