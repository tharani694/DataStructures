public class Array<T> implements Iterable<T> {
    T[] arr;
    int len = 0;
    int capacity = 0;

    public Array(){
        this(16);
    }

    public Array(int capacity){
        if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity ":+capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size(){
        return len;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T get(int index){
        if(index >= len || index<0) throw new IndexOutOfBoundsException();
        return arr[index];
    }

    public void set(int index,T element){
        if(index >= len || index<0) throw new IndexOutOfBoundsException();
        arr[index] = element;
    }


    public void clear(){
        for(int i = 0; i< len;i++){
            arr[i] = null;
        }
        len=0;
    }

    //add() method
    public void add(T element){
        if(len+1 >= capacity){
            if(capacity == 0 )capacity=1;
            else capacity*=2;
            T[] new_arr = (T[]) new Object[capacity];
            for(int i = 0; i < len ; i++)
                new_arr[i] = arr[i];
            arr=new_arr;
        }
        arr[len++] = element;
    }

    //removeAt() method
    public T removeAt(int rm_index)
    {
        if(rm_index < 0 || rm_index >= len) throw IndexOutOfBoundsException();
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[len-1];
        for(int i = 0, j =0; i<len;i++,j++ ){
            if( rm_index== i ) j--;
            else new_arr[j] = arr[i];
        }
        arr = new_arr;
        capacity = --len;
        return data;
    }

    //remove method
    public boolean remove(Object obj){
        int index = indexOf(obj);
        if(index ==-1) return false;
        removeAt(index);
        return true;
    }

    //IndexOf() method

    public int indexOf(Object obj){
        for(int i = 0; i < len; i++){
            if(obj==null){
                if(arr[i] == null) return i;
            }else {
                if (obj.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    //Contains() method
    public int contains(Object obj){
        return indexOf(obj) !=-1;
    }

    @Override
    public java.util.Iterator <T> iterator(){
        return new java.util.Iterator<T> {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next(){
                return arr[index++];
            }

            @Override
                    public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString(){
        if(len == 0)  return "[]";
        else{
            StringBuilder s = new StringBuilder(len).append("[");
            for(int i = 0; i < len-1; i++){
                sb.append(arr[i]+",");
            }
            return sb.append(arr[len-1],"]").toString();
        }
    }
}