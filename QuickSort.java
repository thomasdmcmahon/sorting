import java.util.Random;
import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = new Random().ints(20, 1, 100).toArray();
        System.out.println("Original array:");
        printArray(array);

        // Call Sort method to sort the array
        Sort(array);

        System.out.println("Sorted array:");
        printArray(array);
    }

    // Public method to be called by users for sorting
    public static void Sort(int[] array) {
        _QuickSort(array, 0, array.length - 1);
    }

    // Method to choose the pivot as the median of the first, middle, and last values
    private static int _ChoosePivot(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        int[] pivots = {array[low], array[mid], array[high]};
        Arrays.sort(pivots);
        if (pivots[1] == array[low]) return low;
        if (pivots[1] == array[mid]) return mid;
        return high;
    }

    // Method to partition the array and return the pivot index
    private static int _Partition(int[] array, int low, int high) {
        int pivotIndex = _ChoosePivot(array, low, high);
        swap(array, pivotIndex, high);  // Move pivot to end for partitioning
        
        int pivot = array[high];
        int left = low;
        int right = high - 1;

        while (left <= right) {
            while (left <= right && array[left] <= pivot) {
                left++;
            }
            while (right >= left && array[right] >= pivot) {
                right--;
            }
            if (left < right) {
                swap(array, left, right);  // Swap elements out of place
            }
        }

        swap(array, left, high);  // Move pivot to its correct place
        return left;
    }

    // QuickSort method
    private static void _QuickSort(int[] array, int low, int high) {
        while (low < high) {
            int pivotIndex = _Partition(array, low, high);

            // Recursively sort the smaller part to reduce stack depth
            if (pivotIndex - low < high - pivotIndex) {
                _QuickSort(array, low, pivotIndex - 1);
                low = pivotIndex + 1;  // Tail call optimization by iterating on the larger part next
            } else {
                _QuickSort(array, pivotIndex + 1, high);
                high = pivotIndex - 1;  // Tail call optimization by iterating on the larger part next
            }
        }
    }

    // Helper method to swap elements in the array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Helper method to print the array
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
