import java.io.*;
import java.net.*;

public class Main {

  public static void messaging(Socket clientSocket, Socket destinationServerSocket) {
    try {
      InputStream clientInput = clientSocket.getInputStream();
      OutputStream clientOutput = clientSocket.getOutputStream();
      InputStream destinationServerInput = destinationServerSocket.getInputStream();
      OutputStream destinationServerOutput = destinationServerSocket.getOutputStream();

      byte[] buffer = new byte[1024];
      int fromClientToServer;
      int fromServerToClient;

      do {
        if ((fromClientToServer = clientInput.read(buffer)) != -1) {
          destinationServerOutput.write(buffer, 0, fromClientToServer);
        }
        if ((fromServerToClient = destinationServerInput.read(buffer)) != -1) {
          clientOutput.write(buffer, 0, fromServerToClient);
        }
      } while (fromServerToClient != -1 || fromClientToServer != -1);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    if (args.length < 3) {
      return;
    }

    final int PORT_P = Integer.parseInt(args[0]);
    final String DESTINATION_SERVER = args[1];
    final int PORT_P1 = Integer.parseInt(args[2]);

    try (ServerSocket serverSocket = new ServerSocket(PORT_P)) {
      System.out.println("Сервер запущен");

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Подключился клиент: " + clientSocket.getInetAddress().getHostName());

        try (Socket destinationServerSocket = new Socket(DESTINATION_SERVER, PORT_P1)) {
          System.out.println("Установлено соединение с целевым сервером");
          clientSocket.setSoTimeout(1000);
          destinationServerSocket.setSoTimeout(1000);
          messaging(clientSocket, destinationServerSocket);
          clientSocket.close();
        } catch (ConnectException e) {
          clientSocket.close();
          System.out.println("Целевой сервер отказал в соединении");
        }
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
