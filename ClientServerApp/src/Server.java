import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            System.out.println("сервер успешно подключен");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            final String name = in.readLine();

            out.println("Сервер получил ваше сообщение: " + name);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
