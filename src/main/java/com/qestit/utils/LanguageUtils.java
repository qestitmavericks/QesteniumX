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
	private static final char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ',
			'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ä', 'Ö',
			'Ü', 'ß', 'ä', 'ö', 'ü' };

	private static final char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O',
			'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
			'O', 'U', 's', 'a', 'o', 'u' };

	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
		if (index >= 0) {
			ch = DESTINATION_CHARACTERS[index];
		}
		return ch;
	}

	public static String removeAccent(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case 'Ä':
			case 'ä':
				sb.append("ae");
				break;
			case 'Ö':
			case 'ö':
				sb.append("oe");
				break;
			case 'Ü':
			case 'ü':
				sb.append("ue");
				break;
			case 'ß':
				sb.append("ss");
				break;
			default:
				int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
				if (index >= 0) {
					sb.append(DESTINATION_CHARACTERS[index]);
				} else {
					sb.append(ch);
				}
				break;
			}
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
