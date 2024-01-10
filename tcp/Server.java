import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        while (true) {
            ServerSocket sersock = new ServerSocket(4000);
            System.out.println("Server connected, waiting for Client");
            Socket sock = sersock.accept();
            System.out.println("Connection successful, waiting for filename");

            InputStream iStream = sock.getInputStream();
            BufferedReader namReader = new BufferedReader(new InputStreamReader(iStream));
            String fname = namReader.readLine();

            OutputStream oStream = sock.getOutputStream();
            PrintWriter pwrite = new PrintWriter(oStream, true);

            try {
                BufferedReader contentRead = new BufferedReader(new FileReader(fname));
                String str;

                while ((str = contentRead.readLine()) != null) {
                    pwrite.println(str);
                }

                contentRead.close();
            } catch (FileNotFoundException e) {
                pwrite.println("File doesn't exist");
            } finally {
                System.out.println("Closing connection");
                pwrite.close();
                namReader.close();
                sock.close();
                sersock.close();
            }
        }
    }
}
