import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CountText {

	
	public static void main(String[] args) throws IOException{
	
		BufferedReader reader = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/Dati_finali/datiTesto.txt"));
		PrintWriter writer =  new PrintWriter("/Users/francescodipalma/Desktop/ultime_analisi/testoFinale.txt");
		
		String line = reader.readLine();
		int count = 0;
		
		while (line != null) {
			line = reader.readLine();
			
			if(line != null && !line.equals("null")) {
				writer.append(line);
				writer.append("\n");
				count++;
			}
		}
		
		reader.close(); writer.close();
		System.out.println(count);
		
	}
	
}

