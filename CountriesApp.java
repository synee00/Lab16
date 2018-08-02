/*Jasmine S. Allen
* August 1, 2018
* Program: Displays a list of countries to user. Countries can be added
* 			or removed.
*/
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CountriesApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		List <String> countries = new ArrayList <String>();
		countries = CountriesTextFile.readFile();
		
		int choice = 0; 
		
		System.out.println("Welcome to the Countries App");
		
		do {
			choice = menu(scan);
			switch(choice)
			{
				case 1:	displayCountries(countries);
					break;
				case 2:	addCountries(scan, countries);
						appendFile(countries);
					break;
				case 3: removeCountries(scan, countries);
					break;
				case 4: System.out.println("\nGoodbye!");
					break;
			}
		}while(choice != 4);
		
		
	}

	private static void appendFile(List<String> countries) {
		try (
				// The `false` here tells the FileOutputStream to overwrite the file, rather than append to it
				FileOutputStream fileOutputStream = new FileOutputStream("New_Country_List.txt", false);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);
			) {
				// write to the file end of file
				fileWriter.println(countries.get(countries.size()));
				
			} catch (IOException e) {
				// If something else crazy goes wrong, print out the error.
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}		
	}

	private static void writeFile(List<String> countries) {
		try (
				// The `false` here tells the FileOutputStream to overwrite the file, rather than append to it
				FileOutputStream fileOutputStream = new FileOutputStream("New_Country_List.txt", false);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);
			) {
				// write to the file
				for (String item : countries) {
					// each item must be a string of text to write to the file
					fileWriter.println(item);
				}
				
			} catch (IOException e) {
				// If something else crazy goes wrong, print out the error.
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}		
	}

	private static void removeCountries(Scanner scan, List<String> countries) {
		String removed = countries.remove((Validator.getInt(scan, 
				"Please enter a country (1 - " + countries.size() +"):", 1, countries.size()-1))-1);
		System.out.println();
		System.out.println("The country (" + removed + ") has been removed! ");
		displayCountries(countries);		
	}

	private static void addCountries(Scanner scan, List<String> countries) {

		countries.add(Validator.getStringMatchingRegex(scan, "Please enter a country: ", "[a-zA-Z].*"));
		System.out.println();
		System.out.println("Your country (" + countries.get(countries.size()-1) + ") has been added! ");
		displayCountries(countries);
		
	}

	private static void displayCountries(List<String> countries) {
		// TODO Auto-generated method stub
		int count = 1;
		
		System.out.println();
		
		for(String item : countries)
		{
			System.out.println(count++ + ". " + item);
		}
		
	}

	private static int menu(Scanner scan) {
		int choice = 0;
		
		System.out.println();
		
		System.out.println("1. Display current list of countries \n"
							+"2. Add a new country to list \n"
							+"3. Remove a country to list \n"
							+"4. Exit and print to file \n\n");
		
		choice = Validator.getInt(scan, "Which option would you like to perform? ", 1, 4);
		
		
		return choice;
	}

}
