import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Question 1
// This nearest neighbor method uses 2-d grid of arraylists of StarbucksLocation.
// Each space in the grid represents one longitude latitude pair, so it is 361 by 181 (-180 to 180 for longitude, -90 to 90 for latitude).
// Each starbucks location is inserted into a space's array list based on its lng and lat values.  This makes searching for the nearest neighbor
// very quick.  All that is required is picking a space based on the lng and lat given and looping through the arraylist of that space and finding the closest distance.
// It also requires searching the neighboring spaces to see if any starbucks in those spaces are closer than the ones in the given space.

// Question 2
// Building the grid of latitude and longitude buckets takes O(n) time where n is the number of starbucks locations in the array.
// This can be seen in the build method as the array is only looped through once to add all of the items to the grid.
// In the getNearest method, choosing the correct space in the grid takes O(1) time because getting from a 2d array is constant time.
// However it is not constant time to loop through the array at each space and compare each distance to the current best.
// This takes O(9m) time where m is the number of Starbucks locations in each space. The 9 is there because after the space originally
// chosen is searched, its 8 neighbors are searched to see if there are any closer matches in the neighbors.
// The worst case of this theoretically could be O(n) with n being the length of the array of all starbucks locations if all the data was in a single space in the grid.  
// However, this is extremely unlikely to happen. Our data was much more spread out, as the average starbucks location per space being around 
// .12 (7568 locations divided by a 360x180 array, 7568/64800 = .118).  This can be a little misleading as most spaces around the edges were empty, 
// while those towards the center of the grid were much fuller (20-30 locations).
// Overall, This means that the average running time of the getNearest method is O(9m) or O(m).

public class StudentStarbucks extends Starbucks {
	private ArrayList<StarbucksLocation>[][] grid;

	// constructor
	public StudentStarbucks() {
		super();
		// creates grid and initializes all arraylists in grid
		grid = (ArrayList<StarbucksLocation>[][]) new ArrayList[360][180];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new ArrayList<StarbucksLocation>();
			}
		}
	}

	@Override
	public void build(StarbucksLocation[] allLocations) {
		// Deep copies contents of allLocations into new array
		StarbucksLocation[] copy = new StarbucksLocation[allLocations.length];
		for (int i = 0; i < allLocations.length; i++) {
			copy[i] = new StarbucksLocation(allLocations[i]);
		}
		// Adds all elements of copied array to 2d grid base on lng and lat values
		for (int i = 0; i < copy.length; i++) {
			int x = (int) copy[i].lng + 180;
			int y = (int) copy[i].lat + 90;
			if (!grid[x][y].contains(copy[i])) {
				grid[x][y].add(copy[i]);
			}
		}
	}

	@Override
	public StarbucksLocation getNearest(double lng, double lat) {
		int x = (int) lng + 180;
		int y = (int) lat + 90;
		double bestDist = Double.MAX_VALUE;
		StarbucksLocation best = null;
		
		// used to search neighboring spaces as well as chosen one
		int temp1 = x - 1;
		int temp2 = x + 2;
		int temp3 = y - 1;
		int temp4 = y + 2;
		while (best == null) {
			// Searches through grid determined by x and y and all of its neighbors
			for (int a = temp1; a < temp2; a++) {
				for (int b = temp3; b < temp4; b++) {
					// Makes sure there are no index out of bounds errors
					if (a >= 0 && a < 360 && b >= 0 && b < 180) {
						// searches through the arraylist in each grid, comparing locations to the current best
						for (int i = 0; i < grid[a][b].size(); i++) {
							if (Starbucks.distance(lng, lat, grid[a][b].get(i).lng, grid[a][b].get(i).lat) < bestDist) {
								bestDist = Starbucks.distance(lng, lat, grid[a][b].get(i).lng, grid[a][b].get(i).lat);
								best = grid[a][b].get(i);
							}
						}
					}
				}
			}
			// If nothing is found, incrememnt variables in for loops to extend search outwards
			temp1--;
			temp2++;
			temp3--;
			temp4++;
		}
		// returns a copy of the closest starbucks
		return new StarbucksLocation(best);
	}
}
