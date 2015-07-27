__author__ = 'Meemaw'

from random import randint


def Partition(aList,l,r):
    c = randint(l,r-1)
    aList[l], aList[c] = aList[c], aList[l]
    pivot = aList[l]
    i = l+1
    for j in range(l+1,r):
        if aList[j] <= pivot:
            aList[j], aList[i] = aList[i], aList[j]
            i+=1
    aList[l], aList[i-1] = aList[i-1], aList[l]
    return i-1



def Quicksort(aList,l,r):
    if l < r:
        splitPoint = Partition(aList,l,r)
        Quicksort(aList,l,splitPoint)
        Quicksort(aList,splitPoint+1,r)

