def Sort(A): # Call with unsorted array as arg to sort
    return _QuickSort(A, 0, len(A) - 1)

def _ChoosePivot(A, low, high):
    # Choose the pivot as the median of three (low, mid, high) to avoid worst-case scenarios
    mid = (low + high) // 2
    pivots = [(A[low], low), (A[mid], mid), (A[high], high)]
    pivots.sort(key=lambda x: x[0])
    return pivots[1][1]

def _Partition(A, low, high):
    p = _ChoosePivot(A, low, high)
    A[p], A[high] = A[high], A[p] # Move pivot to end for partitioning
    pivot = A[high]
    left = low
    right = high - 1

    while left <= right:
        while left <= right and A[left] <= pivot:
            left = left + 1
        while right >= left and A[right] >= pivot:
            right = right - 1
        if left < right:
            A[left], A[right] = A[right], A[left] # Swap elements out of place
    
    A[left], A[high] = A[high], A[left] # Move pivot to its correct place
    return left

def _QuickSort(A, low, high):
    while low < high:
        
        p = _Partition(A, low, high)

        # Recursively sort the smaller parts to reduce stack depth
        if p - low < high - p:
            _QuickSort(A, low, p-1)
            low = p+1 # Tail call optimization by iterating on the larger part next
        else:
            _QuickSort(A, p+1, high)
            high = p-1 # Tail cal optimization by iterating on the larger part next
    return A
