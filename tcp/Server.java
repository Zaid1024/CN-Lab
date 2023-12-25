import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
		public static void main(String[] args) {
				try {
						ServerSocket serverSocket = new ServerSocket(12345);

						System.out.println("Server is listening on port 12345...");

						while (true) {
								Socket socket = serverSocket.accept();
								System.out.println("Client connected: " + socket.getInetAddress());

								Thread clientHandler = new Thread(new ClientHandler(socket));
								clientHandler.start();
						}
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
}

class ClientHandler implements Runnable {
		private Socket socket;

		public ClientHandler(Socket socket) {
				this.socket = socket;
		}

		@Override
		public void run() {
				try {
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

						String fileName = in.readLine();
						System.out.println("Client requested file: " + fileName);

						File file = new File(fileName);

						if (file.exists()) {
								BufferedReader fileReader = new BufferedReader(new FileReader(file));
								String line;
								while ((line = fileReader.readLine()) != null) {
										out.println(line);
								}
								fileReader.close();
						} else {
								out.println("File not found");
						}

						socket.close();
						System.out.println("Connection closed.");
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
}
