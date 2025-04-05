package exercises;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercise2 {
	public Exercise2() {}
	
	public Exercise2(DataOutputStream dos) {
		try {
			dos.writeUTF("Nhap so nguyen n: ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String solve(int m) {
		StringBuilder sb = new StringBuilder();
		int sum = 0, product = 1, temp = m;
		while (temp > 0) {
			int digit = temp % 10;
			sum += digit;
			product *= digit;
			temp /= 10;
		}
		sb.append("\nTong cac chu so: " + sum);
		sb.append("\nTich cac chu so: " + product);
		return sb.toString();
	}

	public String printBaiTap() {
		// TODO Auto-generated method stub
		return "Nhap so nguyen n: ";
	}
}
