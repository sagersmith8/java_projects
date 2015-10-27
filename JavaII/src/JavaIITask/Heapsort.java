package JavaIITask;

import java.util.ArrayList;

/**
 * Created by Sage on 4/9/2014.
 * This program perfoms a heapsort on employee list
 */
public class Heapsort {
    int n=0;

    /**
     * @param employeeArrayList list you want sorted
     * this constructor takes an array list and performs a sort
     */

    public Heapsort(ArrayList<Employee> employeeArrayList){
        n = employeeArrayList.size()-1;
        heapify(employeeArrayList);

        /*
            runs through all needed elements and performs heapsort on them
         */
        for(int i = n; i >= 0; i--){
            swap(employeeArrayList,0, i);
            n=n-1;
            maxheap(employeeArrayList, 0);
        }
    }

    /**
     * @param employeeArrayList list you want sorted
     * this method constructs a heap
     */
    public void heapify(ArrayList<Employee> employeeArrayList){

        /*
            evaluates heap
         */
        for(int i = n/2; i >= 0; i--){
            maxheap(employeeArrayList ,i);
        }
    }

    /**
     *
     * @param employeeArrayList list you want sorted
     * @param i specified index
     * this method finds the maximum index location for specified object
     */
    public void maxheap(ArrayList<Employee> employeeArrayList, int i){
        int left = 2*i;
        int right=2*i+1;
        int largest;

        /*
            checks to see if left is greater than specified object
            sets largest to left if it is otherwis sets it to i
         */
        if(left<=n&&employeeArrayList.get(left).compareTo(employeeArrayList.get(i))>0){
            largest = left;
        }

        else{
            largest = i;
        }

        /*
            checks to see if right is bigger than current largest
            if it is it sets largest to i
         */
        if(right <= n &&employeeArrayList.get(right).compareTo(employeeArrayList.get(largest))>0){
            largest = right;
        }

        /*
            checks to see if largest is not current location
            if it is not current location it switches the current element with
            the largest elements them calls itself
         */
        if(largest!=i){
            swap(employeeArrayList, i, largest);
            maxheap(employeeArrayList, largest);
        }
    }

    /**
     *
     * @param employeeArrayList list you want sorted
     * @param i index of first element
     * @param j index of second element
     * this method switches the indexes of specified elements
     */
    public void swap(ArrayList<Employee> employeeArrayList, int i, int j){
        Employee temp = employeeArrayList.get(i);
        employeeArrayList.set(i,employeeArrayList.get(j));
        employeeArrayList.set(j,temp);
    }
}
