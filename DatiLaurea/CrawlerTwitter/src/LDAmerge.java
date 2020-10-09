import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LDAmerge {

	public static void main(String[] args) throws IOException {
		
		BufferedReader topics1 = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/abc/topics1.txt"));
		BufferedReader topics2a = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/abc/topics2a.txt"));
		BufferedReader topics2b = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/abc/topics2b.txt"));
		BufferedReader topics3a = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/abc/topics3a.txt"));
		BufferedReader topics3b = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/abc/topics3b.txt"));
	
		PrintWriter writer =  new PrintWriter("/Users/francescodipalma/Desktop/abc/topicsTotal.txt");
		
		ArrayList<String> parole = new ArrayList<String>();
		
		String line = "";
		while(line != null) {
			line = topics1.readLine();
			if(line != null && !parole.contains(line)) {
				writer.append(line + "\n");
				parole.add(line);
			}
		}
		
		line = "";
		while(line != null) {
			line = topics2a.readLine();
			if(line != null && !parole.contains(line)) {
				writer.append(line + "\n");
				parole.add(line);
			}
		}
		
		line = "";
		while(line != null) {
			line = topics2b.readLine();
			if(line != null && !parole.contains(line)) {
				writer.append(line + "\n");
				parole.add(line);
			}
		}
		
		line = "";
		while(line != null) {
			line = topics3a.readLine();
			if(line != null && !parole.contains(line)) {
				writer.append(line + "\n");
				parole.add(line);
			}
		}
		
		line = "";
		while(line != null) {
			line = topics3b.readLine();
			if(line != null && !parole.contains(line)) {
				writer.append(line + "\n");
				parole.add(line);
			}
		}
		
		writer.close(); topics1.close(); topics2a.close(); topics2b.close(); topics3a.close(); topics3b.close();
		System.out.println(parole.size());

	}

}
