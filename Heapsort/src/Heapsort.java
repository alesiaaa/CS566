// Alesia Razumova
// Programming Assignment 1

public class Heapsort {

	private int[] data = null;
	private int l = 0;
	private int r = 0;
	private int largest;
	
	private int smaller;
	private int bigger;
	
	private int [] finalArray = null;
	
	
	
	public Heapsort(int[] data1){
		this.data = data1;	
		this.finalArray = new int [this.data.length];
	}
	
	
	private void maxHeapify (int i){		
		largest = i;
		
		while (i < data.length/2){
			
			
			l = (i*2)+1;
	
			if (l != data.length-1){
				r = (i*2)+2;
			} else {
				r = -1;
			}
			
			if (data[l] > data[i] && l < data.length){
				largest = l;
			
			}
			
			if (r !=-1 ){
				if (data[r] > data[largest] && r < data.length){
				largest = r;
				}
			} 
		
			
			if (largest != i){
				
				smaller = data[i];
				bigger = data[largest];
				
				// swap
				
				data[i] = bigger;
				data[largest] = smaller;
				
			} else {
				return;
			}
			
			i = largest;

		} // end while
	}
	
	private void buildMaxHeap (){
		
		
		for (int i = data.length/2-1; i >=0; i--){
			
			maxHeapify(i);
		}
		
	}
	
	
	public int[] heapsort(){
		
		buildMaxHeap();
		
		for (int i = data.length-1; i >=0; i--){
			
			//System.out.println(data[0]);
			//System.out.println(Arrays.toString(finalArray));
			
			finalArray[i] = data[0];
			data[0] = -1;
			maxHeapify(0);
		}
		
		return finalArray;
	}
	
	
	
	
}
