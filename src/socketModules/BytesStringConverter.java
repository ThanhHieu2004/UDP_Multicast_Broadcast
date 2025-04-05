package socketModules;

import java.net.DatagramPacket;

public class BytesStringConverter {
	
	public static String bytesToString(DatagramPacket dataPacket) {
		String result = new String(dataPacket.getData());
		return result;
	}
	
	public static byte[] stringToBytes(Object data) {
		byte[] byteData = new byte[1024];
		byteData = ((String) data).getBytes();
		return byteData;
	}
}
