/*
 * Copyright (c) 2023 QESTIT
 * QesteniumX
 */

package com.qestit.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LanguageUtils {

	private LanguageUtils() {
		super();
	}

	// Updated character arrays with German characters
	private static final char[] SOURCE_CHARACTERS = { 'Ä', 'Ö', 'Ü', 'ä', 'ö', 'ü', 'ß' };
	private static final String[] DESTINATION_CHARACTERS = { "Ae", "Oe", "Ue", "ae", "oe", "ue", "ss" };

	public static String removeGermanAccent(char ch) {
	    int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
	    if (index >= 0) {
	        return DESTINATION_CHARACTERS[index];
	    }
	    return String.valueOf(ch);
	}

	public static String removeAccent(String str) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < str.length(); i++) {
	        char ch = str.charAt(i);
	        sb.append(removeGermanAccent(ch)); // Utilize removeGermanAccent for each character
	    }
	    return sb.toString();
	}


	public static String convertCharset_ISO_8859_1_To_UTF8(String text) {
		return new String(text.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
	}

	public static String convertCharset_UTF8_To_ISO_8859_1(String text) {
		return new String(text.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
	}

	public static String convertCharset_US_ASCII_To_UTF8(String text) {
		return new String(text.getBytes(StandardCharsets.US_ASCII), StandardCharsets.UTF_8);
	}
}
