__author__ = 'Meemaw'



def bubbleSort(aList):
    sortBound = int(len(aList)-1)
    for i in range(len(aList)):
        sorted = True
        for j in range(sortBound):
            if aList[j] > aList[j+1]:
                sorted = False
                aList[j], aList[j+1] = aList[j+1], aList[j]
        if sorted:
            return
        sortBound-=1

