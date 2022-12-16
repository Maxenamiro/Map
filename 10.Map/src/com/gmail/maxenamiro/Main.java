package com.gmail.maxenamiro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		File folder = new File("translateTest");
		folder.mkdirs();

		File englishInFile = new File("translateTest/English.in");
		File ukrainianOutFile = new File("translateTest/Ukrainian.out");
		File dictionaryFile = new File("translateTest/EngUkr.dict");
		try {
			englishInFile.createNewFile();
			ukrainianOutFile.createNewFile();
			dictionaryFile.createNewFile();
		} catch (IOException e) {
			System.out.println(e);
		}

		String txt = "Hello world! Welcome to Java:)";
		try (BufferedWriter f = new BufferedWriter(new FileWriter(englishInFile))) {
			f.write(txt);
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println(txt);

		Dictionary dictionary1 = new Dictionary();
		dictionary1.putNewWord("hello", "привіт");
		dictionary1.putNewWord("world", "світ");
		dictionary1.putNewWord("welcome", "ласкаво просимо");
		dictionary1.putNewWord("to", "до");
		dictionary1.saveDictionary("translateTest/EngUkr.dict");
		Dictionary dictionary2 = Dictionary.loadDictionary("translateTest/EngUkr.dict");
		System.out.println(dictionary2);

		Translator engUkrTranslator = new Translator(dictionary2);

		engUkrTranslator.translate("translateTest/English.in", "translateTest/Ukrainian.out");

		String result = "";
		try (BufferedReader readerOne = new BufferedReader(new FileReader("translateTest/Ukrainian.out"))) {
			for (String a = ""; (a = readerOne.readLine()) != null;) {
				result += a;
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println(result);
	}
}