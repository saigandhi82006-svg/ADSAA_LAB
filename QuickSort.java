public class QuickSortDemo {

    static int[] arr;

    static void quickSort(int low, int high) {
        if (low < high) {
            int j = partition(low, high);
            quickSort(low, j - 1);
            quickSort(j + 1, high);
        }
    }

    static int partition(int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        int j = high;

        while (i <= j) {

            while (i <= high && arr[i] <= pivot) {
                i++;
            }

            while (j > low && arr[j] >= pivot) {
                j--;
            }

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        arr[low] = arr[j];
        arr[j] = pivot;

        return j;
    }

    public static void main(String[] args) {

        arr = new int[]{40, 20, 10, 25, 70, 60, 80, 45, 85, 50};

        System.out.println("Before sorting:");
        for (int ele : arr) {
            System.out.print(ele + " ");
        }

        System.out.println();

        quickSort(0, arr.length - 1);

        System.out.println("After sorting:");
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }
}
