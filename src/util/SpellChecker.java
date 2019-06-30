package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class SpellChecker {
	
	private Map<String, String> dictionary = new HashMap<String, String>();
	
	public static SpellChecker getInstance(){
		return SpellCheckerHolder.INSTANCE;
	}
	
	public void LoadDictionary(String dictionaryPath){
		BufferedReader reader =  null;
		try {
			String word;
			reader = new BufferedReader(new FileReader(dictionaryPath));
			while ((word = reader.readLine()) != null) {
				this.dictionary.put(word, word);
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public Boolean isMisspelled(String word){
		return !this.dictionary.containsKey(word);
	}

	private static class SpellCheckerHolder {
		public static final SpellChecker INSTANCE = new SpellChecker();
	}
}
