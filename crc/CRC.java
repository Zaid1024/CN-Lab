import java.util.Scanner;

public class CRC {

		public static String crc(String data, String poly, boolean errChk) {
				if (!errChk) data += "0".repeat(poly.length() - 1);

				for (int i = 0; i < data.length() - poly.length() + 1; i++) {
						if (data.charAt(i) == '1') {
								for (int j = 0; j < poly.length(); j++) {
										data = data.substring(0, i + j) + (data.charAt(i + j) == poly.charAt(j) ? '0' : '1') + data.substring(i + j + 1);
								}
						}
				}
				return data.substring(data.length() - poly.length() + 1);
		}

		public static void main(String[] args) {
				Scanner scanner = new Scanner(System.in);
				String poly = "10000100010001010";

				System.out.print("Enter Data to be sent: ");
				String data = scanner.nextLine();

				String rem = crc(data, poly, false);
				String codeword = data + rem;

				System.out.println("Remainder: " + rem);
				System.out.println("Codeword: " + codeword);

				System.out.print("Enter received codeword: ");
				String recvCodeword = scanner.nextLine();

				String recvRem = crc(recvCodeword, poly, true);

				System.out.println(Integer.parseInt(recvRem) == 0 ? "No Error" : "Error Detected");

				scanner.close();
		}
}
