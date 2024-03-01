import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class TargetServer implements Runnable {

  private final int PORT_P1;

  TargetServer(int PORT_P1) {
    this.PORT_P1 = PORT_P1;
  }

  @Override
  public void run() {
    try {
      ServerSocket serverSocket = new ServerSocket(PORT_P1);
      System.out.println(
          LocalDateTime.now() + " | Таргет: Целевой сервер запущен. Ожидание подключений...");

      Socket clientSocket = serverSocket.accept();
      System.out.println(LocalDateTime.now() + " | Таргет: Подключился клиент");

      InputStream clientInput = clientSocket.getInputStream();
      OutputStream clientOutput = clientSocket.getOutputStream();

      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = clientInput.read(buffer)) != -1) {
        System.out.println(LocalDateTime.now() + " | Таргет: Данные получил");
        clientOutput.write(buffer, 0, bytesRead);
        System.out.println(LocalDateTime.now() + " | Таргет: Данные отправил");
      }

      clientInput.close();
      clientOutput.close();
      clientSocket.close();
      serverSocket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(LocalDateTime.now() + " | Таргет: закончил");
  }
}
