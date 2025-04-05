package exercises;


import java.io.DataOutputStream;
import java.io.IOException;

public class Exercise1 {
	public Exercise1() {
	}

	public Exercise1(DataOutputStream dos) {
		try {
			dos.writeUTF("Nhập 1 số: ");
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String solve(int n) {
		StringBuilder sb = new StringBuilder();
		sb.append("\nLa so nguyen to?: " + isPrime(n));
		sb.append("\nLa so chinh phuong?: " + isPerfectSquare(n));
		sb.append("\nLa so hoan hao?: " + isPerfectNumber(n));
		sb.append("\nLa so amstring?: " + isArmstrong(n));
		return sb.toString();
	}

	public static boolean isPrime(int n) {
		if (n < 2)
			return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static boolean isPerfectSquare(int n) {
		int sqrt = (int) Math.sqrt(n);
		return sqrt * sqrt == n;
	}

	public static boolean isPerfectNumber(int n) {
		int sum = 1;
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0)
				sum += i;
		}
		return sum == n;
	}

	public static boolean isArmstrong(int n) {
		int sum = 0, temp = n, digits = String.valueOf(n).length();
		while (temp > 0) {
			sum += Math.pow(temp % 10, digits);
			temp /= 10;
		}
		return sum == n;
	}

	public String printBaiTap() {
		return "Nhập 1 số: ";
	}
}
