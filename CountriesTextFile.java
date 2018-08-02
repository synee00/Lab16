/*Jasmine S. Allen
* August 1, 2018
* Program: Displays a list of countries to user. Countries can be added
* 			or removed.
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountriesTextFile {

	public static List<String> readFile() {
		List<String> items = new ArrayList<>();
				
				try (
					// Open/prepare the resources in the try resources block
					FileInputStream fileInputStream = new FileInputStream("Country_List.txt");
					Scanner fileScanner = new Scanner(fileInputStream)
				) {
					// loop until the end of the file
					while (fileScanner.hasNextLine()) {
						// read each line as a string
						String line = fileScanner.nextLine();
						// then convert it to an object
						items.add(line.trim());
					}
					
				} catch (FileNotFoundException ex) {
					// If the file doesn't exist, there just aren't any items.
					System.out.println("No countries in list.");
					return items;
				} catch (IOException e) {
					// If something else crazy goes wrong, print out the error.
					System.err.println("Something unexpected happended.");
					e.printStackTrace();
				}
				
				// Finally return the array of items.
				return items;
	}
	
	

}
