
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileParser {

//   public static void main(String[] args) {
// 	FileParser fileParser = new FileParser();
// 	System.out.println(fileParser.getFile("resources/file/test.txt"));		
//   }

  public String getFile(String fileName) {

	StringBuilder result = new StringBuilder("");

	//Get file from resources folder
	// ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(fileName);

	try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line).append("\n");
		}

		scanner.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result.toString();

  }

}
