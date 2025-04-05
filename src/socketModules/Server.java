package socketModules;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import exercises.Exercise1;
import exercises.Exercise2;
import exercises.Exercise3;
import exercises.Exercise4;
import exercises.Exercise5;
import exercises.Exercise6;

public class Server {
	public static String PrintMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nPham Thanh Hieu DANH SÁCH BÀI TẬP:\n");
		sb.append("1. Kiểm tra số nguyên tố, chính phương, hoàn hảo, amstrong.\n");
		sb.append("2. Tính tổng và tích các chữ số của số nguyên dương.\n");
		sb.append("3. Tìm ước chung lớn nhất và bội chung nhỏ nhất của hai số nguyên dương.\n");
		sb.append("4. Nhập và in chuỗi đảo ngược của một chuỗi ký tự.\n");
		sb.append("5. Xử lý chuỗi ký tự (đảo ngược, chữ hoa, chữ thường, đổi chữ, đếm từ, tìm nguyên âm).\n");
		sb.append("6. Xử lý chuỗi ký tự (in từng từ, đếm tần số ký tự).\n");
		sb.append("0. Thoát chương trình.\n");
		sb.append("--> Nhập lựa chọn của bạn: ");
		return sb.toString();
	}

	public static String inLenhBaiTap(int choice) {
		String outputBaiTap = "";
		switch (choice) {
		case 1:
			outputBaiTap = new Exercise1().printBaiTap();
			break;
		case 2:
			outputBaiTap = new Exercise2().printBaiTap();
			break;
		case 3:
			outputBaiTap = new Exercise3().printBaiTap();
			break;
		case 4:
			outputBaiTap = new Exercise4().printBaiTap();
			break;
		case 5:
			outputBaiTap = new Exercise5().printBaiTap();
			break;
		case 6:
			outputBaiTap = new Exercise6().printBaiTap();
			break;
		case 0:
			outputBaiTap = "Thoat chuong trinh...\n";
			System.out.println("Thoat chuong trinh...\n");
			break;
		default:
			outputBaiTap = "Lựa chọn in bài tập không hợp lệ!\n";
			System.out.println("Lựa chọn in bài tập không hợp lệ!\n");
		}
		return outputBaiTap;
	}

	public static String ThucHienBaiTap(int choice, String... value) {
		String result = "If this one doesn't change, sth wrong";
		switch (choice) {
		case 1:
			result = new Exercise1().solve(Integer.parseInt(value[0]));
			break;
		case 2:
			result = new Exercise2().solve(Integer.parseInt(value[0]));
			break;
		case 3:
			result = new Exercise3().solve(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
			break;
		case 4:
			result = new Exercise4().solve(value[0]);
			break;
		case 5:
			result = new Exercise5().solve(value[0]);
			break;
		case 6:
			result = new Exercise6().solve(value[0]);
			break;
		case 0:
			System.out.println("Thoat chuong trinh...\n");
			break;
		default:
			System.out.println(choice);
			System.out.println("Lựa chọn không hợp lệ!\n");
		}
		return result;
	}

	public static void main(String[] args) {
		try {
			String menu = "";
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "2025"));
			DatagramSocket server = new DatagramSocket(port);
			System.out.println("Pham Thanh Hieu: Server has started on port: " + port);
			int choice = -1;
			while (true) {
				try {
					menu = PrintMenu();
					// Wait for client to connect
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					server.receive(receivePacket);
					String clientMessage = new String(receivePacket.getData());
					System.out.println(clientMessage);
					// Get client InetAddress and Port
					InetAddress clientInet = receivePacket.getAddress();
					int clientPort = receivePacket.getPort();
					System.out.println("ClientPOrt: " + clientPort);
					// Send menu to client
					sendData = menu.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientInet, clientPort);
					server.send(sendPacket);
					// Read choice from client
					server.receive(receivePacket);
					choice = Integer.valueOf(BytesStringConverter.bytesToString(receivePacket));
					if (choice == 0) {
						System.out.println("Client disconnected.");
						break; // Exit the loop when client exits
					}
					// Send Lenh bai tap
					String lenhBaiTap = inLenhBaiTap(choice);
					sendData = lenhBaiTap.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, clientInet, clientPort);
					server.send(sendPacket);
					// Read input here
					server.receive(receivePacket);
					String input = new String(receivePacket.getData());
					String input2 = "";
					if (choice == 3) {
						server.receive(receivePacket);
						input2 = new String(receivePacket.getData());
					}
					String result = ThucHienBaiTap(choice, input, input2);
					sendData = result.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, clientInet, clientPort);
					server.send(sendPacket);
				} catch (IOException e) {
					System.out.println("Client disconnected. Closing connection...");
			        break; // Exit the loop if the client disconnects unexpectedly
				}
			}
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Client disconnected. Closing connection...");
			e.printStackTrace();
		}

	}
}
