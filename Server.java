import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int portNo;

    public Server(int portNo) {
        this.portNo = portNo;
    }

    private void processConnection() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String docroot = "docroot/";

        String buffer = in.readLine();

        if (buffer == null) {
            System.out.println("Received empty request");
            return;
        }

        // Checks if not a valid request

        System.out.println("Received request: " + buffer);

        String[] tokens = buffer.split(" ");
        String filePath = tokens[1];

        if ("/".equals(filePath)) {
            filePath = "/home.html"; // Serve home.html if root is requested
        }

        // File built from filepath specified in the buffer and our docroot that I
        // hardcoded lol
        File file = new File(docroot + filePath);

        // Consume the remaining headers
        String str;
        while (!(str = in.readLine()).isEmpty()) {
            System.out.println("Client: " + str);
        }

        if (file.exists() && !file.isDirectory()) {
            // This is where the file actually gets sent
            System.out.println("Sending file: " + file.getAbsolutePath());
            String contentType = Files.probeContentType(file.toPath());

            out.printf("HTTP/1.1 200 OK\r\n");
            out.printf("Content-Length: %d\r\n", file.length());
            out.printf("Content-Type: %s\r\n", contentType);
            out.printf("\r\n"); // End of headers

            Files.copy(file.toPath(), clientSocket.getOutputStream());
        } else {
            // This is to get it to stop blowing up when there's no file
            String notFoundMessage = "<h1>404 Not Found</h1>";
            out.printf("HTTP/1.1 404 Not Found\r\n");
            out.printf("Content-Length: %d\r\n", notFoundMessage.length());
            out.printf("Content-Type: text/html\r\n");
            out.printf("\r\n"); // End of headers
            out.print(notFoundMessage);
        }
        in.close();
        out.close();
    }

    public void run() throws IOException {
        boolean running = true;

        serverSocket = new ServerSocket(portNo);
        System.out.printf("Listening on Port: %d\n", portNo);
        while (running) {
            clientSocket = serverSocket.accept();
            // ** Application Protocol
            processConnection();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.run();
    }
}
