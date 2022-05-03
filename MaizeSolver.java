
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * Program to find shortest way to reach source destination in maz3
 * 
 * @author Rishabh Kapoor
 *
 */

public class finexra {
	
	public static enum suffix{
		first(1), second(2),third(3), fourth(4) ,
		fifth(5), sixth(6), seventh(7),eigth(8), ninth(9)
		
		;
		public final Integer label;
		
		 private suffix(Integer label) {
		        this.label = label;
		    }
		 
		 public static String valueOfLabel(Integer label) {
			    for (suffix e : values()) {
			        if (e.label.equals(label)) {
			            return e.toString();
			        }else {
			        	if((label % 10) == e.label && label >20) {
			        		return String.valueOf(label)+ e.toString().substring(e.toString().length()-2) ;
			        	}
			        }
			        
			    }
			    return label.toString() +"th";
			}
	}
	
	// private PushbackReader in;
		static int inputX, inputY, outputX, outputY, min_dist;
	
		public static void main(String[] args)
				throws NumberFormatException, IOException, InterruptedException, CustomException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
			// example Input
			exampleInput();
	
			// x co-ordinate of maze
			int x = Integer.parseInt(br.readLine().trim());
			// y co-ordinate of maze
			System.out.println("Enter number of columnns in maze");
			int y = Integer.parseInt(br.readLine().trim());
	
			if (x < 1 ||  y < 1 ) {
				System.out.println("Please enter values greater than or equal to 1");
				return;
			}
			Character[][] array = new Character[x][y];
			for (int j = 0; j < x; j++) {
				for (int k = 0; k < y; k++) {
					
					System.out.println("Enter " + suffix.valueOfLabel(j + 1)  + " row element and " + suffix.valueOfLabel(k + 1) + " column element");
					String input = br.readLine().trim();
					if (input.charAt(0) != ' ' && (Character.toLowerCase(input.charAt(0)) == (char)'o'
							|| Character.toLowerCase(input.charAt(0)) == (char)'x')) {
						array[j][k] = input.charAt(0);
						System.out.println("------------------");
					} else {
						System.out.println("Please enter the valid character");
						return;
					}
				}
			}
	
			// Print Maze
			for (int j = 0; j < x; j++) {
				for (int k = 0; k < y; k++) {
					System.out.print(" " + array[j][k] + " ");
				}
				System.out.println();
			}
			Thread.sleep(100);
			System.out.println("Please enter the source S co-ordinate");
	
			// input co-ordinates
			String inputCoordinate = br.readLine();
	
			System.out.println("Enter the destination Z  co-ordinate");
	
			// destinate co-ordinates
			String destCoordinate = br.readLine();
	
			try {
				String[] inputs = inputCoordinate.split("\\s+");
	
				inputX = Integer.parseInt(inputs[0]);
				inputY = Integer.parseInt(inputs[1]);
	
				String[] outputs = destCoordinate.split("\\s+");
	
				outputX = Integer.parseInt(outputs[0]);
				outputY = Integer.parseInt(outputs[1]);
				
				int[][] arry = new int[x][y];
				
				for (int j = 0; j < x; j++) {
					for (int k = 0; k < y; k++) {
						arry[j][k]= array[j][k]=='x' ? 0 : 1;
						
					}
				}
	
				min_dist = findShortestPathLength(arry, inputX-1, inputY-1, outputX-1, outputY-1);

				if (min_dist != -1) {
					System.out.println("The shortest path from source to destination " + "has length " + min_dist);
				} else {
					System.out.println("Destination cannot be reached from source");
				}
	
			} catch(Exception e) {
				throw new CustomException("Please enter the source or destination coordinates correctly");
			}
			
			finally {
			System.out.println("-----------------End---------------------------");
			}
			
	
	
		}

	
		private static void exampleInput() throws InterruptedException {
			System.out.println("Please input the maze with o and x characters where x is path we cannot move. Example:");
			Thread.sleep(2000);
			Character[][] exampleArray = { { 'o', 'x', 'o' }, { 'o', 'x', 'o' }, { 'o', 'o', 'o' }, };
	
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
	
					System.out.println("Enter " +  suffix.valueOfLabel(j + 1) + " row element and " +  suffix.valueOfLabel (k + 1) + " column element");
	
					System.out.println(exampleArray[j][k]);
					System.out.println("------------------");
				}
				Thread.sleep(100);
			}
	
			Thread.sleep(1000);
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					System.out.print(" " + exampleArray[j][k] + " ");
				}
				System.out.println();
			}
			System.out.println("------------------");
	
			Thread.sleep(1000);
	
			System.out.println("Please enter the source S co-ordinate");
			System.out.print(1);
			Thread.sleep(1000);
			System.out.print(" " + 1);
			System.out.println();
			Thread.sleep(2000);
			System.out.println("Enter the destination Z  co-ordinate");
			System.out.print(3);
			Thread.sleep(1000);
			System.out.print(" " + 3);
			System.out.println();
			Thread.sleep(2000);
			System.out.print("Please start.");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.println(".");
			System.out.println("Enter number of rows in maze");
		}
	
	

		public static int findShortestPathLength(int[][] mat, int i, int j, int x, int y)
		{
		    // base case: invalid input
		    if (mat == null || mat.length == 0 || mat[i][j] == 0 || mat[x][y] == 0) {
		        return -1;
		    }
		
		   
		    int rows = mat.length;
		    int columns = mat[0].length;
		
		    int distance;
		
		    
		    boolean[][] visited = new boolean[rows][columns];
		
		    distance = findShortestPath(mat, visited, i, j, x, y, Integer.MAX_VALUE, 0);
		    if (distance != Integer.MAX_VALUE) {
		        return distance;
		    }
		    return -1;
		}

		public static int findShortestPath(int[][] mat, boolean[][] visited,
		        int i, int j, int x, int y, int minimum_distance, int dist)
		{
		// if the destination is found, update `minimum_distance`
		if (i == x && j == y) {
		return Integer.min(dist, minimum_distance);
		}
		
		// set (i, j) cell as visited
		visited[i][j] = true;
		
		// go to the bottom cell
		if (isSafeAndWithinMaze(mat, visited, i + 1, j))
		{
		minimum_distance = findShortestPath(mat, visited, i + 1, j, x, y,
		minimum_distance, dist + 1);
		}
		
		// go to the right cell
		if (isSafeAndWithinMaze(mat, visited, i, j + 1))
		{
		minimum_distance = findShortestPath(mat, visited, i, j + 1, x, y,
		minimum_distance, dist + 1);
		}
		
		// go to the top cell
		if (isSafeAndWithinMaze(mat, visited, i - 1, j))
		{
		minimum_distance = findShortestPath(mat, visited, i - 1, j, x, y,
		minimum_distance, dist + 1);
		}
		
		// go to the left cell
		if (isSafeAndWithinMaze(mat, visited, i, j - 1))
		{
		minimum_distance = findShortestPath(mat, visited, i, j - 1, x, y,
		minimum_distance, dist + 1);
		}
		
		// backtrack: remove (i, j) from the visited matrix
		visited[i][j] = false;
		
		return minimum_distance;
		}
		
		
	private static boolean isSafeAndWithinMaze(int[][] mat, boolean[][] visited, int x, int y) {
		
		
	    if( (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) &&
	            mat[x][y] == 1 && !visited[x][y])
			return true;
		return false;
	}

static class CustomException extends Exception {
	
		public CustomException(String message) {
			super(message);
		}
	
	}
