import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Alesia Razumova
// Extra credit assignment

public class Test {
	
	static Scanner scanner = null;
	
	// Data
	private static ArrayList<File> files = new ArrayList<>();
	private static ArrayList<String> inputNodes = new ArrayList<>();
	private static HashMap <String, Integer> distance = new HashMap<> ();
	private static HashMap <String, Integer> inputMap = new HashMap<> ();
	private static String[][] matrix = null;
	static int lenghtOfArray = 0;
	static int widthOfArray = -1;
	
	/**
	 * Function: main()
	 * Input: None.
	 * Output: None.
	 * Use: Starts main thread, gets input data 
	 * and points user to the menu.
	 */
	public static void main (String args[]){
		
		// Load necessary data
		// All data files to be found in the 'data' folder
		getInputFileData();
		
		//Greeting
		System.out.println("\n\tWelcome to the Path Finding System!\n");
		System.out.println("To exit the system please enter 'exit' anywhere.\n");
		
		scanner = new Scanner(System.in);
			
			try {
				// initial call for menu
				menu(scanner);
				
			} finally {
				scanner.close();
			}
			
	}
	
	/**
	 * Function: processUserInput()
	 * Input: Node selected by the user.
	 * Output: None.
	 * Use: Initializes Algorithm1 and Algorithm2.
	 * Retrieves the heuristic shortest paths.
	 * Prints respective output to the screen. 
	 */
	public static void processUserInput(String input){
		
			Algorithm1 alg1 = new Algorithm1(input, distance, inputMap);
			// Calculates shortest path using distance file for distance metric
			// Prints the path nodes
			// Prints the distance
			alg1.getShortestPath();
			
			
			Algorithm2 alg2 = new Algorithm2(input, distance, inputMap);
			// Calculates shortest path using both input and distance file for distance metric
			// Prints the path nodes
			// Prints the distance
			alg2.getShortestPath();
			
			
	}
	
	
	/**
	 * Function: menu()
	 * Input: Scanner object for retrieving user input.
	 * Output: None.
	 * Use: Recursive function that prompts the user
	 * for an input node. Checks for node validity.
	 * Also serves as a secondary check for input files.
	 */	
	public static void menu (Scanner scanner){
			
			System.out.print("Please enter a starting node: ");
			
			String input = scanner.nextLine();
			
			
			if (input.toLowerCase().matches("exit") ||
				input.toLowerCase().matches("exit ") ){
				
				System.out.println("\nGoodbye!");
				return; // exit
				
			} else if (distance.isEmpty()  ||  inputMap.isEmpty()){
				
				System.out.println("\nPlease check data files. Not enough data to complete"
						+ " calculations. Once files have been added, please restart the"
						+ " program. Thank you.");
				return; // exit
				
			} else if (inputNodes.contains(input.toUpperCase())) {
			
				// process user input
				processUserInput(input.toUpperCase());
			
			} else {
				
				System.out.println("\nThe node you requested is not a valid start node or"
						+ " was not found. Please try again.\n");
				
			}
			
			// Recursive call
			menu(scanner);
			
		}
		
	/**
	 * Function: getInputFileData()
	 * Input: None.
	 * Output: None.
	 * Use: Retrieves the files hard coded in the 
	 * function. Stores retrieved values in hashmaps.
	 */
	public static void getInputFileData(){
			
			// editable fields
			
			String directDistanceFile = "../data/direct_distance_1.txt";
			String graphInputFile = "../data/graph_input_1.txt";
			
			
			// end of editable fields
			
			
			File file1 = new File(directDistanceFile);
			File file2 = new File(graphInputFile);
			files.add(file1);
			files.add(file2);
			
			/*// Directory where data files are
			Path dataPath = Paths.get("../data");
			
			String path = dataPath.toAbsolutePath().toString();
			
			File dir = new File (path);
					
			if (dir.listFiles() == null){
				
				System.out.println("WARNING: No files found.");

			} else if (dir.listFiles() != null && dir.listFiles().length == 2) {
		
				for (File file : dir.listFiles()){
					try {
						files.add(file.getName());
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			
			} else {
				
				System.out.println("WARNING: Data folder may only contain two files: direct_distance and"
						+ " graph_input. Please modify contents accordingly before proceeding for alorithm to execute.");
				
			}
			*/
			
			for (File file: files){
				try {
					
					// store direct distances in a hashmap
					if (file.toString().contains("distance")){
						
						//FileReader fileReader = new FileReader(dataPath.toString() + "/" + file);
						FileReader fileReader = new FileReader(file);
				        BufferedReader reader = new BufferedReader(fileReader);
				        String line = null;
				     
				        while ((line = reader.readLine()) != null) {
				        	
				        	StringBuilder num = new StringBuilder();
				        	StringBuilder str = new StringBuilder();
				        
				        	for(char c : line.toCharArray()){
				        		//find the distance
				                if(Character.isDigit(c)){
				                   num.append(c);
				                } 
				                //find the associated letter
				                else if(Character.isLetter(c)){
				                   str.append(c);          
				                }
				        	}
				        	
				        	// add values into hashmap
				        	distance.put(str.toString(), Integer.parseInt(num.toString()));
				        	
				        }
				        
				        reader.close(); // close the reader
						
						//System.out.println(distance);
				        
				    } 
					
					// store inputs in a 
					else if (file.toString().contains("input")){
					
							
						//FileReader fileReader = new FileReader(dataPath.toString() + "/" + file);
						FileReader fileReader = new FileReader(file);
				        BufferedReader reader = new BufferedReader(fileReader);
				       
				        String line = null;
				        
				        int x=0; // keeps track of line to add
				        
				       
				        while ((line = reader.readLine()) != null) {
				        	String[] values = line.split("\\s+");
				        	
				        	
				        	 if (matrix == null) {
				        		 //instantiate matrix
				                 matrix = new String[widthOfArray = values.length]
				                		 			[lenghtOfArray = values.length];
				             }
				        	
				        	
				        	// add values into the matrix
				        	for (int i=0; i < values.length; i++){
				        		
				        		matrix[i][x] = values[i];
				        		
				        	}
				        	
				        	x++; // next line
				        	
				        	
				        }
				       
				        reader.close(); // close the reader
						
					}
					
					
					
					// Store input combinations in a hashmap
					int y=1; 
					while (y < lenghtOfArray){
						
						inputNodes.add(matrix[0][y]);
						
						for (int i=1; i < widthOfArray; i++){
											
							StringBuilder str = new StringBuilder();
							str.append(matrix[0][y]);
							str.append(matrix[i][0]);
							
							int inputValue = Integer.parseInt(matrix[i][y]);

							if (inputValue > 0){
								inputMap.put(str.toString(), inputValue);
							}
							
						}
						
						y++;
					}
					
					
				} catch (Exception e) {
					System.out.println("WARNING: Please check: "+ file.toString() + ". It was not found.");
				} 
				
			}
			
		}
		


}
