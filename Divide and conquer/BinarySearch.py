__author__ = 'Meemaw'



def binarySearch(aList, element):
    if len(aList) == 1:
        return aList[0] == element
    mid = int(len(aList)/2)
    if aList[mid] > element:
        return binarySearch(aList[:mid],element)
    if aList[mid] < element:
        return binarySearch(aList[mid:], element)
    return True