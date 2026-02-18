// Max Heap implementation using Arrays
import java.util.Scanner;
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
    // Insertion
    public void insert(int item){
        if(size == capacity){
            System.out.println("Heap is full");
            return;
        }
        int i = ++size;
        while(i > 1 && item > heap[i / 2]){
            heap[i] = heap[i / 2];
            i = i / 2;
        }
        heap[i] = item;
    }
    // Delete Max
    public int delMax(){
        if(size == 0){
            System.out.println("Heap is empty");
            return -1;
        }
        int max = heap[1];
        heap[1] = heap[size];
        size = size - 1 ;
        adjust(1, size);
        return max;
    }
    // adjust method
    public void adjust(int i, int n){
        int item = heap[i];
        int j = 2 * i;
        while(j <= n){
            if(j < n && (heap[j] < heap[j + 1])){
                j = j + 1;
            }
            if(j < n && (item > heap[j])){
                break;
            }
            heap[j / 2] = heap[j];
            j = 2 * j;
        }
        heap[j / 2] = item;
    }
    // Display
    public void display(){
        if(size == 0){
            System.out.println("Heap is empty");
            return;
        }
        for(int i = 1; i <= size; i++){
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
public class HeapMaxDemo{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Heap h = new Heap(6);
        
        h.insert(40);
        h.display();
        h.insert(80);
        h.display();
        h.insert(35);
        h.display();
        h.insert(90);
        h.display();
        h.insert(45);
        h.display();
        h.insert(50);
        h.display();
        System.out.println("Deleted Max : " + h.delMax());
        h.display();

    }
}