__author__ = 'Meemaw'


def median(aList,l,r):
    a = aList[l]
    b = aList[r]
    mid = ((l+r)//2)-1 if (l+r) % 2 == 0 else (l+r)//2
    c = aList[mid]
    temp = [a,b,c]
    median = sorted([a,b,c])[1]
    if median == temp[1]:
        aList[l], aList[r] = aList[r], aList[l]
    elif median == temp[2]:
        aList[l], aList[mid] = aList[mid], aList[l]
    return median


def Partition(aList,l,r):
    pivot = median(aList,l,r-1)

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

