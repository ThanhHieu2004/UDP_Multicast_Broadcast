package exercises;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercise5 {
	public Exercise5() {
	}

	public Exercise5(DataOutputStream dos) {
		try {
			dos.writeUTF("Nhap 1 chuoi: ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String solve(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append("\nChuoi dao nguoc: " + new StringBuilder(input).reverse());
		sb.append("\nCHUOI IN HOA: " + input.toUpperCase());
		sb.append("\nchuoi in thuong: " + input.toLowerCase());
		sb.append("\nChUoI kI cUc: " + swapCase(input));
		sb.append("\nDem tu: " + input.split("\\s+").length);
		sb.append("\nCac nguyen am: " + extractVowels(input));
		return sb.toString();
	}

	public static String swapCase(String str) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (i % 2 == 0) {
				result.append(Character.toUpperCase(ch)); // Uppercase for even indices
			} else {
				result.append(Character.toLowerCase(ch)); // Lowercase for odd indices
			}
		}

		return result.toString();
	}

	public static String extractVowels(String s) {
		return s.replaceAll("[^AEIOUaeiou]", "");
	}

	public String printBaiTap() {
		// TODO Auto-generated method stub
		return "Nhap 1 chuoi: ";
	}
}
