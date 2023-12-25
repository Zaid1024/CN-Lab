import java.util.Scanner;

public class crc {
	private static final int polynomial = 0x1021; // CRC-CCITT polynomial

	public static String calculateCRC(String data) {
		int crc = 0x0000; // Initial CRC value

		for (char c : data.toCharArray()) {
			int byteValue = (int) c;
			crc ^= (byteValue << 8) & 0xFFFF;
			for (int i = 0; i < 8; i++) {
				if ((crc & 0x8000) != 0) {
					crc = (crc << 1) ^ polynomial;
				} else {
					crc <<= 1;
				}
				crc &= 0xFFFF; // Ensure it's a 16-bit value
			}
		}
		 return String.format("%04X", crc);
	}

	public static boolean validateCRC(String receivedData, String receivedCRC) {
		int crc = Integer.parseInt(receivedCRC,16);

		for (char c : receivedData.toCharArray()) {
			int byteValue = (int) c;
			crc ^= (byteValue << 8) & 0xFFFF;
			for (int i = 0; i < 8; i++) {
				if ((crc & 0x8000) != 0) {
					crc = (crc << 1) ^ polynomial;
				} else {
					crc <<= 1;
				}
				crc &= 0xFFFF; // Ensure it's a 16-bit value
			}
		}
		return crc == 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new java.util.Scanner(System.in);

		// Sender side
		System.out.print("Enter the data for CRC calculation: ");
		String inputData = scanner.nextLine().trim();
		String crcChecksum = calculateCRC(inputData);
		String dataWithCRC = inputData + crcChecksum;
		System.out.println("Transmitting data with CRC: " + dataWithCRC);

		// Receiver side
		System.out.print("Enter the received data (message + CRC): ");
		String receivedData = scanner.nextLine().trim();
		String receivedMessage = receivedData.substring(0, receivedData.length() - 5);
		String receivedCRCString = receivedData.substring(receivedData.length() - 5);
		boolean isValid = validateCRC(receivedMessage, receivedCRCString);

		if (isValid) {
			System.out.println("CRC Check: Data is intact. Received message: " + receivedMessage);
		} else {
			System.out.println("CRC Check: Data is corrupted. Discarding the message.");
		}

		scanner.close();
	}
}
