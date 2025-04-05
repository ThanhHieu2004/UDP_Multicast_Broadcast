package socketModules;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		DatagramSocket client;
		String menu = "";
		String ex_prompt = "";
		String input, input2;
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		try {
			 int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
			// client = new Socket(host, port);
//			client = new Socket("hopper.proxy.rlwy.net", 32418);
			client = new DatagramSocket(port);
			InetAddress address = InetAddress.getByName("localhost");
			int serverPort = 2025;
			Scanner sc = new Scanner(System.in);
			int chonBaiTap = -1;
			while (true) {
				try {
					// Send a connection message to server
					String message = "Hello, a new client has connected!";
					sendData = message.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);
					client.send(sendPacket);
					// Read menu from server
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					client.receive(receivePacket);
					menu = new String(receivePacket.getData());
					System.out.println(menu);
					// Input choice
					chonBaiTap = sc.nextInt();
					// Prevent "" error
					sc.nextLine();
					sendData = String.valueOf(chonBaiTap).getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);
					client.send(sendPacket);
					// Get ex prompt
					client.receive(receivePacket);
					ex_prompt = new String(receivePacket.getData());
					System.out.println(ex_prompt);
					// Insert answer accordingly
					input = sc.nextLine();
					sendData = BytesStringConverter.stringToBytes(input);
					sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);
					client.send(sendPacket);
					// Since ex3 requires 2 args
					if (chonBaiTap == 3) {
						input2 = sc.nextLine();
						sendData = BytesStringConverter.stringToBytes(input2);
						sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);
						client.send(sendPacket);
					}
					if (chonBaiTap == 0) {
					    System.out.println("Exiting client...");
					    client.close();
					    System.exit(0);
					    break;
					}
					// get server response
					client.receive(receivePacket);
					String response = BytesStringConverter.bytesToString(receivePacket);
					System.out.println(response);

				} catch (Exception e) {
					System.out.println("End program client!");
					e.printStackTrace();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
