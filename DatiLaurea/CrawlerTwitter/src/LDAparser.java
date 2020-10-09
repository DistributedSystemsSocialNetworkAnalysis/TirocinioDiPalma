import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LDAparser {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		BufferedReader reader = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/abc/model-final.twords"));
		PrintWriter writer =  new PrintWriter("/Users/francescodipalma/Desktop/abc/topicsFine.txt");
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(new FileReader("/Users/francescodipalma/Desktop/Dati_finali/stopwords.json"));
		JSONArray j = (JSONArray) json.get("words");
		@SuppressWarnings("unchecked")
		ArrayList<String> stopwords = new ArrayList<String>(j);
		
		String line = " ";
		
		for(int i = 0; i < 100; i++) {
			for(int k = 0; k < 101; k++) {
				line = reader.readLine();
				
				if(k == 0) writer.append(line + "\n");
				else {
					StringTokenizer st = new StringTokenizer(line, " \t");
					
					String s = st.nextToken();
					if(!stopwords.contains(s)) writer.append(s + "\n");
					
				}
				
			}
		}
		
		reader.close(); writer.close();

	}

}
