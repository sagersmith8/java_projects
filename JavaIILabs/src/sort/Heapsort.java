package sort;

public class Heapsort {
	int n=0;
	
	public Heapsort(int [] array){
		n = array.length-1;
		heapify(array);
		
		for(int i = n; i >= 0; i--){
			swap(array,0, i);
			n=n-1;
			maxheap(array, 0);
		}
	}
	
	public void heapify(int [] array){
		for(int i = n/2; i >= 0; i--){
			maxheap(array ,i);
		}
	}
	
	public void maxheap(int [] array, int i){
		int left = 2*i;
		int right=2*i+1;
		int largest;
		
		if(left<=n&&array[left]<array[i]){
			largest = left;
		}
		
		else{
			largest = i;
		}
		
		if(right <= n &&array[right]<array[largest]){
			largest = right;
		}
		if(largest!=i){
			swap(array, i, largest);
			maxheap(array, largest);
		}
	}
	
	public void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i]=array[j];
		array[j]=temp;
	}
}
