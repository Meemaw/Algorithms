

def SelectionSort(aList):
	for i in range(len(aList)):
		minIndex = i
		for j in range(i+1,len(aList)):
			if aList[j] < aList[minIndex]:
				minIndex = j

		aList[i], aList[minIndex] = aList[minIndex], aList[i]
