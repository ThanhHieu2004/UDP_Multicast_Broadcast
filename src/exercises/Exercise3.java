package exercises;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercise3 {
	public Exercise3() {
	}

	public Exercise3(DataOutputStream dos) {
		try {
			dos.writeUTF("Nhap 2 so a, b: ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String solve(int a, int b) {
		StringBuilder sb = new StringBuilder();
		sb.append("\nUCLN: " + ucln(a, b));
		sb.append("\nBCNN: " + (a * b / ucln(a, b)));
		return sb.toString();
	}

	public static int ucln(int a, int b) {
		return b == 0 ? a : ucln(b, a % b);
	}

	public String printBaiTap() {
		// TODO Auto-generated method stub
		return "Nhap 2 so a, b: ";
	}
}
