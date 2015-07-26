__author__ = 'Meemaw'




def choosemedian(lst, a, b, c):
    v = sorted([lst[a], lst[b], lst[c]])[1]
    return lst.index(v)



def Partition(aList,l,r):
    global count
    ## Swaping for last element aList[l], aList[r-1] = aList[r-1], aList[l]
    index = choosemedian(aList,l,r-1,(r+l)//2)
    pivot = aList[index]
    aList[l], aList[index] = aList[index] , aList[l]



    i = l+1
    for j in range(l+1,r):
        count+=1
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



count = 0
aList = []
with open("odpri.txt") as file:
    for line in file:
        aList.append(int(line))

test = [3, 0, 2, 6, 1, 5, 9, 4, 8, 7]
Quicksort(test,0,len(test))
print(count)
