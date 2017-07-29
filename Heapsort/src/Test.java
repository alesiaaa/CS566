import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



// Alesia Razumova
// Programming Assignment 1

public class Test {
	
	public Test(){}
	
	
	private int[] shuffle(int[] data){
		int temp;
		// Shuffle the array
		int num = data.length;
        for (int i = 0; i < num; i++) {
            int x = i + ((int) (Math.random() * (num-i)));
            temp = data[i];
            data[i] = data[x];
            data[x] = temp;
        }
		
		return data;	
	}
	
	public static void main (String[] args){
		
		String fileName = null;
		
		if (args.length > 0){
			fileName = args[0];
		} else {
			fileName = "empty";
		}
		
		System.out.println("Starting test..");
		
		//File file = new File("./data/random-10000.txt");
		File file = new File(fileName);
		//int lines = 10000;
		int lines = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
		// End of editable fields
		
		int[] data = null;
		int[] sorted = null;
		int x=0;
		int a=0;
		
		try {
			
			if(!file.exists()){
				System.out.println("No such file.");
				return;
			}
			
			Scanner scan = new Scanner (new FileReader(file));
			
			data = new int [lines];
		
			while(scan.hasNext()) {
			   
			    data[x] = scan.nextInt();    
			    x++;
			}			
			
			//Print out first 10 values
			/*System.out.println("Printing first 10 values of scanned data..");
			
			while (a < 10){
				
			    System.out.println(data[a]);
				a++;
			}
			System.out.println("Print completed.\n");*/
			
			
			if (scan != null){
				scan.close();
			}
			
		
		} catch (IOException e) {e.printStackTrace();}
		
		// to store output values
		sorted = new int [lines];
		
		//long totalElapsedTime = 0;
		long elapsedTime = 0;
		//long averageElapsedTime = 0;
		
		// Create new test object
		Test test = new Test();
		
		// Create new heapsort object
		Heapsort h = new Heapsort (test.shuffle(data));
	
		// Start timer
		long startTime = System.currentTimeMillis();
		
		// Execute heapsort operation
		sorted = h.heapsort();
		
		// Stop timer
		long endTime = System.currentTimeMillis();
			
		// Calculate elapased time
		elapsedTime = endTime - startTime;
		
		
		
		
		//totalElapsedTime = totalElapsedTime + elapsedTime ;
		//averageElapsedTime = totalElapsedTime / 10;
		
		
		//System.out.println("Average elapsed time: " + averageElapsedTime);
		
		//Print out first 10 values in sorted array
		System.out.println("\nPrinting first 10 values of sorted data..");
		
		a = 0;
		while (a < 10){
			
		    System.out.println(sorted[a]);
			a++;
		}
		System.out.println("Print completed.\n");
		
		System.out.println("Elements in array: " + data.length);
		System.out.println("Total elapsed time for single execution: " + elapsedTime + " milliseconds");
		
		System.out.println("\nEnd of test.");
		
	}

}
