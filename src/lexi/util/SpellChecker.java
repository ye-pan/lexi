package lexi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class SpellChecker {
	
	private Map<String, String> dictionary = new HashMap<String, String>();
	
	public static SpellChecker getInstance(){
		return SpellCheckerHolder.INSTANCE;
	}
	
	public void loadDictionary(String dictionaryPath){
		try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryPath))){
			String word;
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
		static final SpellChecker INSTANCE = new SpellChecker();
	}
}
