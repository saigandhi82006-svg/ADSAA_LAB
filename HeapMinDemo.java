// Min Heap implementation using arrays
class Heap{
    int[] heap;
    int capacity;
    int size;

    // Constructor
    Heap(int capacity){
        this.capacity = capacity;
        heap = new int[capacity + 1];
        size = 0;
    }
    // insertion
    public void insert(int item){
        if(size == capacity){
            System.out.println("Heap is full");
            return;
        }
        int i = ++size;
        while(i > 1 && (item < heap[i / 2])){
            heap[i] = heap[i / 2];
            i = i / 2;
        }
        heap[i] = item;
    }
    // Delete Min
    public int delMin(){
        int min = heap[1];
        heap[1] = heap[size];
        size = size - 1;
        adjust(1, size);
        return min;
    }
    // adjust method
    public void adjust(int i, int n){
        int item = heap[1];
        int j = 2 * i;
        while(j <= n){
            if(j < n && (heap[j] > heap[j + 1])){
                j = j + 1;
            }
            if(j < n && (item < heap[j])){
                break;
            }
            heap[j / 2] = heap[j];
            j = 2 * j;
        }
        heap[j / 2] = item;

    }
    // display
    public void display(){
        if(size == 0){
            System.out.println("heap is empty");
            return;
        }
        for(int i = 1; i <= size; i++){
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
public class HeapMinDemo{
    public static void main(String[] args){
        Heap h = new Heap(6);

        h.insert(50);
        h.display();
        h.insert(45);
        h.display();
        h.insert(90);
        h.display();
        h.insert(35);
        h.display();
        h.insert(80);
        h.display();
        h.insert(40);
        h.display();

        System.out.println("deleted min : " + h.delMin());
        h.display();
    }
}