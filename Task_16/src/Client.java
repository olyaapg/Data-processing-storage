import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client implements Runnable {

  private final int PORT_P;
  private final String SERVER_HOST;

  Client(int PORT_P, String SERVER_HOST) {
    this.PORT_P = PORT_P;
    this.SERVER_HOST = SERVER_HOST;
  }

  @Override
  public void run() {
    Socket socket;
    try {
      socket = new Socket(SERVER_HOST, PORT_P);
      System.out.println(LocalDateTime.now() + " | Клиент: Подключено к серверу ");

      InputStream input = socket.getInputStream();
      OutputStream output = socket.getOutputStream();

      var message = "Wow, hi! What's up?";
      output.write(message.getBytes());
      System.out.println(LocalDateTime.now() + " | Клиент: Отправлено");

      byte[] buffer = new byte[1024];
      int bytesRead = input.read(buffer);
      String response = new String(buffer, 0, bytesRead);
      System.out.println(LocalDateTime.now() + " | Клиент: Получено от сервера: " + response);

      input.close();
      output.close();
      socket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(LocalDateTime.now() + " | Клиент: закончил");
  }
}
