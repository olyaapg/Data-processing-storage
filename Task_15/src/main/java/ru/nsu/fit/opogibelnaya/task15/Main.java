package ru.nsu.fit.opogibelnaya.task15;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Main {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Error input");
      return;
    }
    String url = args[0];

    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(url);
      HttpResponse response = httpClient.execute(httpGet);

      HttpEntity entity = response.getEntity();
      if (entity != null) {
        String content = EntityUtils.toString(entity);
        String[] lines = content.split(System.lineSeparator());
        int pageSize = 25;
        int startIndex = 0;
        int endIndex = Math.min(pageSize, lines.length);
        while (startIndex < lines.length) {
          for (int i = startIndex; i < endIndex; i++) {
            System.out.println(lines[i]);
          }
          if (endIndex < lines.length) {
            System.out.println("Press enter to scroll down...");
            int b = System.in.read();
            while (b != 10) {
              b = System.in.read();
            }
          } else {
            break;
          }
          startIndex = endIndex;
          endIndex = Math.min(endIndex + pageSize, lines.length);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
