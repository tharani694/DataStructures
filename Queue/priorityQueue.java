import java.util.*;
public class priorityQueue<T extends Comparable<T>>{
    //private int heapSize = 0;
    //private int heapCapacity = 0;
    private List<T> heap = null;
    //private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public priorityQueue(){
        this(1);
    }

    public priorityQueue(int sz){
        heap = new ArrayList<>(sz);
    }

    public priorityQueue(T[] elems){
        heapSize = elems.length;
        heap = new ArrayList<T>( heapSize );

        for(int i = 0; i < heapSize; i++ ){
            //mapAdd(elems[i],i);
            heap.add(elems[i]);
        }

        for(int i = Math.max(0, (heapSize/2)-1); i>=0; i--){
            sink(i);
        }
    }

    public priorityQueue(Collection <T> elems){
        //this(elems.size());

        int heapSize = elems.size();
        heap = new ArrayList<T>(heapSize);
        heap.addAll(elems);

        for(int i = Math.max(0, (heapSize/2)-1); i>=0; i--){
            sink(i);
        }
    }


    public boolean isEmpty(){
        return size()==0;
    }

    public void clear(){
        heap.clear();
    }

    public int size(){
        return heap.size();
    }

    public T peek(){
        if(isEmpty()) return null;
        return heap.get(0);
    }

    public T poll(){
        return heap.removeAt(0);
    }

    public boolean contains(T elem){
        for(int i = 0; i < size(); i++) {
            if(heap.get(i).equals(elem)) return true;
        }
        return false;
    }

    public void add(T elem){
        if(elem == null) throw new IllegalArgumentException();

        heap.add(elem);

        int indexOfLastElem = size() - 1;
        swim(indexOfLastElem);
    }

    private boolean less(int i , int j){
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        return node1.compareTo(node2) <= 0;
    }

    private void swim(int k){
        int parent = (k - 1)/2;

        while(k > 0 && less(k,parent)){
            swap(parent, k);
            k = parent;
            parent = (k-1)/2;
        }
    }

    private void sink(int k){
        int heapSize = size();
        while(true){
            int left = 2 * k + 1;
            int right = 2 *k + 2;
            int smallest = left;
        if(right < heapSize && less(right,left)) smallest = right;

        if(left >= heapSize || less(k,smallest)) break;

        swap(smallest, k);
        k = smallest;
        }
    }

    private void swap(int i, int j){
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }

    public boolean remove(T element){
        if(element == null) return false;

        for(int i = 0; i< size(); i++){
            if(element.equals(heap.get(i))){
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    public T removeAt(int i){
        if(isEmpty()) return null;

        int indexOfLastElem = size()-1;
        T removed_data = heap.get(i);

        swap(i,indexOfLastElem);

        heap.remove(indexOfLastElem);

        if(i == indexOfLastElem) return removed_data;
        T elem = heap.get(i);

        sink(i);

        if(heap.get(i).equals(elem)) swim(i);

        return removed_data;
    }

    public boolean isMinHeap(int k){
        int heapSize = size();
        if(k >= heapSize) return true;

        int left = 2*k + 1;
        int right = 2*k + 2;

        if(left < heapSize && !less(k,left)) return false;
        if(right < heapSize && !less(k,right)) return false;

        return isMinHeap(left) && isMinHeap(right);
    }

    @Override public String toString(){
        return heap.toString();
    }

}