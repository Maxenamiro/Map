package com.gmail.maxenamiro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary implements Serializable {

	private HashMap<String, String> dictMap = new HashMap<>();

	public Dictionary() {
	}

	public HashMap<String, String> getDictMap() {
		return dictMap;
	}

	public void putNewWord() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.println("Enter english word:");
				String engWord = reader.readLine();
				System.out.println("Enter ukraine translate:");
				String ukrWord = reader.readLine();
				dictMap.put(engWord.toLowerCase(), ukrWord.toLowerCase());
				System.out.println("Enter more? y/n:");
				String result = reader.readLine();
				if (!result.equals("y")) {
					break;
				}
			} catch (IOException e) {
				System.out.println("e");
			}
		}
	}

	public void putNewWord(String engWord, String ukrWord) {
		dictMap.put(engWord.toLowerCase(), ukrWord.toLowerCase());
	}

	public void saveDictionary(String adress) throws IllegalArgumentException {
		if (adress == null) {
			throw new IllegalArgumentException("nulPointer");
		}
		File dictFile = new File(adress);
		if (!dictFile.exists() || !dictFile.isFile()) {
			throw new IllegalArgumentException("notExists");
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dictFile))) {
			oos.writeObject(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static Dictionary loadDictionary(String adress) throws IllegalArgumentException {
		if (adress == null) {
			throw new IllegalArgumentException("nulPointer");
		}
		File dictFile = new File(adress);
		if (!dictFile.exists() || !dictFile.isFile()) {
			throw new IllegalArgumentException("notExists");
		}
		Dictionary result = new Dictionary();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dictFile))) {
			result = (Dictionary) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dictionary:\n");
		Set<Map.Entry<String, String>> setPair = dictMap.entrySet();
		for (Map.Entry<String, String> pair : setPair) {
			sb.append(pair.getKey());
			sb.append(" = ");
			sb.append(pair.getValue());
			sb.append("\n");
		}
		return sb.toString();

	}
}
