public class MergeSortDemo {

    public static int[] arr;

    static void mergeSort(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;

            mergeSort(low, mid);
            mergeSort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    // merge method
    static void merge(int low, int mid, int high) {

        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] a = new int[high - low + 1];
        int[] lArr = new int[n1];
        int[] rArr = new int[n2];

        for (int x = 0; x < n1; x++) {
            lArr[x] = arr[low + x];
        }

        for (int x = 0; x < n2; x++) {
            rArr[x] = arr[mid + 1 + x];
        }

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (lArr[i] < rArr[j]) {
                a[k] = lArr[i];
                i++;
            } else {
                a[k] = rArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = lArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = rArr[j];
            j++;
            k++;
        }

        for (int x = 0; x < a.length; x++) {
            arr[low + x] = a[x];
        }
    }

    public static void main(String[] args) {

        arr = new int[]{25, 53, 46, 32, 16};

        System.out.println("Before mergeSort:");
        for (int ele : arr) {
            System.out.print(ele + " ");
        }

        mergeSort(0, arr.length - 1);

        System.out.println("\nAfter mergeSort:");
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }
}
