import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.util.Scanner;

public class crc2 {
	public static String calculateCRC(String data) {
		Checksum CRC32 = new CRC32();
		CRC32.update(data.getBytes());
		long crcValue = CRC32.getValue();
		return Long.toHexString(crcValue);
	}

	public static boolean validateCRC(String receivedData, String receivedCRC) {
		Checksum CRC32 = new CRC32();
		CRC32.update(receivedData.getBytes());
		long receivedCRCValue = Long.parseLong(receivedCRC, 16);

		return receivedCRCValue == CRC32.getValue();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Sender side
		System.out.print("Enter the data for CRC calculation: ");
		String inputData = scanner.nextLine().trim();
		String crcChecksum = calculateCRC(inputData);
		String dataWithCRC = inputData + crcChecksum;
		System.out.println("Transmitting data with CRC: " + dataWithCRC);

		// Receiver side
		System.out.print("Enter the received data (message + CRC): ");
		String receivedData = scanner.nextLine().trim();
		String receivedMessage = receivedData.substring(0, receivedData.length() - 8); // Assuming a 8-character CRC
		String receivedCRCString = receivedData.substring(receivedData.length() - 8);
		boolean isValid = validateCRC(receivedMessage, receivedCRCString);

		if (isValid) {
			System.out.println("CRC Check: Data is intact. Received message: " + receivedMessage);
		} else {
			System.out.println("CRC Check: Data is corrupted. Discarding the message.");
		}

		scanner.close();
	}
}
