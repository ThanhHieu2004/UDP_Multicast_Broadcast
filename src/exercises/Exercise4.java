package exercises;

import java.io.DataOutputStream;
import java.io.IOException;

public class Exercise4 {
	public Exercise4() {
	}

	public Exercise4(DataOutputStream dos) {
		try {
			dos.writeUTF("Nhap 1 chuoi: ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String solve(String input) {
		return ("Chuoi in dao nguoc: " + new StringBuilder(input).reverse());
	}

	public String printBaiTap() {
		// TODO Auto-generated method stub
		return "Nhap 1 chuoi: ";
	}
}
