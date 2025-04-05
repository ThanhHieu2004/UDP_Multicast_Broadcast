package exercises;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Exercise6 {
	public Exercise6() {
	}

	public Exercise6(DataOutputStream dos) {
		try {
			dos.writeUTF("Nhap 1 chuoi: ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String solve(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append("\nTừ:");
		for (String word : input.split("\\s+")) {
			sb.append("\n" + word);
		}
		sb.append("\nTần số xuất hiện:");
		Map<Character, Integer> frequency = new TreeMap<>();
		for (char c : input.toCharArray()) {
			if (c == ' ') {
				continue;
			}
			frequency.put(c, frequency.getOrDefault(c, 0) + 1);
		}
		frequency.forEach((k, v) -> sb.append("\n" + k + ": " + v));
		return sb.toString();
	}

	public String printBaiTap() {
		// TODO Auto-generated method stub
		return "Nhap 1 chuoi: ";
	}
}
