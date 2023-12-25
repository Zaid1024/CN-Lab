import java.net.*;
// Remove the extra import for DatagramSocket
// Remove extra semicolon after import statement

class Server {
		public static void main(String args[]) throws Exception {
				DatagramSocket serverSocket = new DatagramSocket(9876);

				byte[] receiveData = new byte[1024]; // Add a semicolon here to terminate the statement
				byte[] sendData; // Remove the unnecessary initialization

				while (true) {
						System.out.println("Server is up");
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						serverSocket.receive(receivePacket);

						// Convert the received data to a string
						String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

						InetAddress ipAddress = receivePacket.getAddress();
						int port = receivePacket.getPort(); // Correct the method name

						String capitalizedSentence = sentence.toUpperCase();
						sendData = capitalizedSentence.getBytes();

						// Create a new DatagramPacket for sending the response
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
						serverSocket.send(sendPacket);
				}
		}
}
