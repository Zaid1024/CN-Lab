import java.io.*;
import java.net.*;

class Client {
		public static void main(String[] args) throws Exception {
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				DatagramSocket clientSocket = new DatagramSocket();
				InetAddress IPAddress = InetAddress.getByName("localhost");
				byte[] sendData;
				byte[] receiveData = new byte[1024]; // Corrected the name and added the size

				System.out.println("Enter the string:");
				String sentence = inFromUser.readLine();
				sendData = sentence.getBytes(); // Corrected the method name

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				clientSocket.send(sendPacket); // Corrected the method

				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);

				String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

				System.out.println("Received from server: " + modifiedSentence);

				clientSocket.close();
		}
}
