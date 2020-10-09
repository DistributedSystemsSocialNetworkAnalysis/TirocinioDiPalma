import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.parser.ParseException;

public class LDA_parsing {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		BufferedReader reader = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/abc/topicsFine.txt"));
		PrintWriter writer =  new PrintWriter("/Users/francescodipalma/Desktop/abc/topics.txt");
		
		String line = " ";
		int count = 0;
		
		while(line != null) {
			count++;
			line = reader.readLine();
				
			if(line != null && line.length() > 3) writer.append(line + "\n");
		}
		
		reader.close(); writer.close();
		System.out.println(count);

	}

}
