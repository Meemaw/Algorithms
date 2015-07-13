__author__ = 'Meemaw'



def InsertionSort(aList):
    for i in range(1,len(aList)):
        temp = aList[i]
        j = i
        while j > 0 and aList[j-1] > temp:
            aList[j] = aList[j-1]
            j -= 1
        aList[j] = temp
