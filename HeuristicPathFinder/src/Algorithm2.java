import java.util.ArrayList;
import java.util.HashMap;

public class Algorithm2 {

	/** 
	 * Algorithm 2: Among all nodes v that 
	 * are adjacent to the node n, choose 
	 * the one for which w(n, v) + dd(v) 
	 * is the smallest. 
	 */
	
	private String node;
	private HashMap <String, Integer> distance;
	private HashMap <String, Integer> inputMap;
	
	private String lastAddedNodeSet;
	private String lastAddedNode;
	
	private ArrayList <String> pathSequence = new ArrayList <>();
	private ArrayList <String> pathSequenceNoBackTrack = new ArrayList <>();
	private ArrayList <String> bannedList = new ArrayList <>();
	private int pathDistance = 0;
	
	
	/**
	 * Function: Algorithm2()
	 * Input: User input node, hashmap containing
	 * distance data and hashmap containing map data.
	 * Output: None.
	 * Use: Constructor for Algorithm2.
	 */
	public Algorithm2 (String node, HashMap <String, Integer> distance, 
			HashMap <String, Integer> inputMap){
		this.node = node;
		this.distance = distance;
		this.inputMap = inputMap;
		
		System.out.println("~~ Algorithm #2 ~~");
	}
	
	
	
	/**
	 * Function: getShortestPath()
	 * Input: None.
	 * Output: None.
	 * Use: Publicly facing function to start
	 * Algorithm1 work.
	 */
	public void getShortestPath(){
		// Initial call for the getDistance function
		getDistance(this.node);
	}
	
	
	/**
	 * Function: getAdjacentSet()
	 * Input: String of a node.
	 * Output: ArrayList of nodes adjacent 
	 * to the input node. Ex: AB
	 * Use: Private function to make finding
	 * adjacency nodes easier.
	 */
	private ArrayList <String> getAdjacentSet (String node){
		
		ArrayList <String> keys = new ArrayList<>();
		keys.addAll(inputMap.keySet());
		
		ArrayList <String> adjacent = new ArrayList<>();
		
		// Go through all the nodes on the map
		// find the ones that are adjacent to the input node
		for (String key: keys){
			
			char c2[] = key.toCharArray();
			String node1 =  Character.toString(c2[0]);
			
			if (node1.equals(node)){
				adjacent.add(key);
			}
	
		}
		
		return adjacent;
		
		
	}
	
	
	/**
	 * Function: getShortestSet()
	 * Input: String of a node.
	 * Output: String of the node that is adjacent
	 * to specified input node with the 
	 * shortest combined distance (from input map 
	 * and direct distance files). Ex: AB
	 * Use: Private function to pinpoint 
	 * closest node to input.
	 */
	private String getShortestSet (String node){
		
		
		ArrayList <String> adjacent= getAdjacentSet(node);
		
		
		String shortestSet = null;
		int miles = 1000000000;
		char ch[] = null;
		
		// find shortest
		for (String s: adjacent){
			
			ch = s.toCharArray();
			
			// Check to find the smallest distance
			// and make sure selected node has not been used
			if ((distance.get(Character.toString(ch[1])) + inputMap.get(s)) < miles 
					&& !this.bannedList.contains(Character.toString(ch[1]))
				){
				
				miles = distance.get(Character.toString(ch[1])) + inputMap.get(s);
				shortestSet = s;
				
			} 
			
			
		}
		
		return shortestSet;
	}
	
	
	/**
	 * Function: getDistance()
	 * Input: String of a node.
	 * Output: None.
	 * Use: Private function used to retrieve the 
	 * distance of next node until node "Z" is reached.
	 * Implements backtracking as a way to avoid dead paths.
	 * Used recursively until final path is found.
	 */
	private void getDistance (String node){
		
		String set = getShortestSet(node);
		
		// Node requested was "Z"
		if (node.equals("Z")){
		
			this.lastAddedNode = node;
			
			//update paths
			pathSequence.add(lastAddedNode);
			pathSequenceNoBackTrack.add(lastAddedNode);
			
			//distance already set to zero
		
		} 
		// if returned set is null, there is nowhere to go
		// back track
		else if (set == null && !node.equals("Z")){
									
			// remove last added node from the final path
			this.pathSequence.remove(lastAddedNode);
					
			// update last added node
			this.lastAddedNode = pathSequence.get(pathSequence.size()-1);
						
			// update the complete sequence path
			this.pathSequenceNoBackTrack.add(lastAddedNode);
										
			// subtract last added node from distance
			pathDistance -= inputMap.get(lastAddedNodeSet);
						
			
		} else {
		
			// record the complete set
			lastAddedNodeSet = set;
			
			// record only the last node
			char currentSet[] = set.toCharArray();
			lastAddedNode = Character.toString(currentSet[1]);
			
		
			// add visited nodes to the banned list
			// this serves as a check for future search
			for (char c: currentSet){
				if (!this.bannedList.contains(Character.toString(c))){
					this.bannedList.add(Character.toString(c));
				}
			}
				
			//also add to final path sequence
			if(!pathSequence.isEmpty()){
				pathSequence.add(lastAddedNode);
			} else {
				pathSequence.add(node);
				pathSequence.add(lastAddedNode);
			}
				
			//and add to complete path sequence of visited nodes
			if(!pathSequenceNoBackTrack.isEmpty()){
				pathSequenceNoBackTrack.add(lastAddedNode);
			} else {
				pathSequenceNoBackTrack.add(node);
				pathSequenceNoBackTrack.add(lastAddedNode);
			}
			
			
			// add new value to the final distance
			pathDistance += inputMap.get(set);
		
		}
		
		
		
		
		// Check to see if we are done
		if (lastAddedNode.equals("Z")){
					
			System.out.println("Sequence of all nodes: " + getPathSequence(this.pathSequenceNoBackTrack));
			System.out.println("Shortest path: " + getPathSequence(this.pathSequence));
			System.out.println("Shortest path length: " + getPathDistance());
			System.out.println(" ");
		} 
				
		// Not done
		else {					
			// Continue to look for path recursively
			getDistance(lastAddedNode);
					
		}
		
		
		
	}
	
	
	/**
	 * Function: getPathSequence()
	 * Input: ArrayList of a nodes.
	 * Output: Formated string of a path sequence.
	 * Use: Retrieve string representation of 
	 * a path sequence.
	 */
	private String getPathSequence(ArrayList <String> sequence){
		
		StringBuilder sb = new StringBuilder();
		int finalElement = sequence.size()-1;
		
		for (int i = 0; i < sequence.size(); i++){
			
			if (i == finalElement){
				sb.append(sequence.get(i));
			} else {
				sb.append(sequence.get(i) + " -> ");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Function: getPathSequence()
	 * Input: None.
	 * Output: Integer of total distance 
	 * for the path taken.
	 * Use: Getter for the final distance. 
	 */
	public int getPathDistance(){
		return pathDistance;
	}
	
	/**
	 * Function: getPathSequence()
	 * Input: Starting node. 
	 * Output: None.
	 * Use: Setter for the starting node.
	 */
	public void setStartingNode(String node){
		this.node = node;
	}
}
