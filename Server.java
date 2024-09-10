import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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

        // *** Application Protocol *****
        String buffer = in.readLine();
        while (buffer.length() != 0) {
            System.out.println("Client: " + buffer);
            buffer = in.readLine();
        }
        out.printf("HTTP/1.1 200 OK\n");
        out.printf("Content Length: 34\n");
        out.printf("Content-Type: text_html\n\n");

        out.printf("<h1>Welcome to the Web Server</h1>");

        in.close();
        out.close();
    }

    public void run() throws IOException {
        boolean running = true;

        serverSocket = new ServerSocket(portNo);
        System.out.printf("Listen on Port: %d\n", portNo);
        while (running) {
            clientSocket = serverSocket.accept();
            // ** Application Protocol
            processConnection();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static void main(String[] args0) throws IOException {
        Server server = new Server(8080);
        server.run();
    }
}
