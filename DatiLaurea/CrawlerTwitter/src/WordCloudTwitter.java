import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.kennycason.kumo.*;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.*;
import com.kennycason.kumo.palette.ColorPalette;

public class WordCloudTwitter {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		Collection<String> list = new ArrayList<String>();
		String path = "/Users/francescodipalma/Desktop/Dati_finali/stopwords.json";
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(new FileReader(path));
		list = (Collection<String>) json.get("words");
		
		final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		frequencyAnalyzer.setWordFrequenciesToReturn(400);
		frequencyAnalyzer.setMinWordLength(5);
		frequencyAnalyzer.setStopWords(list);

		//path del file dove è salvato il testo da studiare
		final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load("/Users/francescodipalma/Desktop/ultime_analisi/terza_parte.txt");
		final Dimension dimension = new Dimension(600, 488);
		final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);
		//path dell'immagine su cui creare la wordcloud in png
		wordCloud.setBackgroundColor(new Color(255,255,255));
		wordCloud.setBackground( new PixelBoundryBackground("/Users/francescodipalma/Desktop/Dati_finali/twitterLogo.png"));
		wordCloud.setColorPalette(new ColorPalette(new Color(0x1DA1F2),new Color(207, 43, 54), new Color(0, 146, 71)));
		wordCloud.setFontScalar(new LinearFontScalar(10, 60));
		wordCloud.build(wordFrequencies);
		//path della wordcloud finale
		wordCloud.writeToFile("/Users/francescodipalma/Desktop/ultime_analisi/terzoWC.png");
	}

}


