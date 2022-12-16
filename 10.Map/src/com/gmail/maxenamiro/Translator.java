package com.gmail.maxenamiro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Translator {

	private Dictionary dictionary;

	public Translator(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public void translate(String fileAdresFrom, String fileAdresTo) {

		if (fileAdresFrom == null || fileAdresTo == null) {
			throw new IllegalArgumentException("nulPointer");
		}

		File fileFrom = new File(fileAdresFrom);
		if (!fileFrom.exists() || !fileFrom.isFile()) {
			throw new IllegalArgumentException("notExists fileFrom");
		}

		File fileTo = new File(fileAdresTo);
		if (!fileTo.exists() || !fileTo.isFile()) {
			throw new IllegalArgumentException("notExists fileTo");
		}

		String txt1 = "";
		try (BufferedReader readerOne = new BufferedReader(new FileReader(fileFrom))) {
			for (String a = ""; (a = readerOne.readLine()) != null;) {
				txt1 += a + "\n";
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		String[] txtArr1 = txt1.split("[ -,;:.!?№%()]");
		String result = "";
		for (String s : txtArr1) {
			String translate = dictionary.getDictMap().get(s);
			if (translate != null) {
				result += translate + " ";
			} else {
				translate = dictionary.getDictMap().get(s.toLowerCase());

				if (translate != null) {
					result += translate.substring(0, 1).toUpperCase() + translate.substring(1) + " ";
				} else {
					result += s + " ";
				}
			}
		}

		try (BufferedWriter f = new BufferedWriter(new FileWriter(fileAdresTo))) {
			f.write(result);
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
