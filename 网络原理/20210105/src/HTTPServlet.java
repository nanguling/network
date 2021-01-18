import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServlet {

    public static void main(String[] args) throws IOException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();


        String body = "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<title>薇姐</title>"
                    + "<meta charset='utf-8'>"
                    + "</head>"
                    + "<body>"
                    + "<h1>薇尔莉特</h1>"
                    + "<p>薇姐</p>"
                    + "</body>"
                    + "</html>";
        System.out.println(body.getBytes("utf-8").length);
        String response = "HTTP/1.0 200 OK\r\n"
                        + "Content-Type: text/html;charset=utf-8\r\n"
                        + "Content-Length: 131 \r\n"
                        + "\r\n"
                        + body;

        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            //开始一次通信
            Socket socket = serverSocket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try{
                        InputStream is = socket.getInputStream();
                        OutputStream out = socket.getOutputStream();

                        //读取请求
                        Scanner scanner = new Scanner(is, "utf-8");
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            System.out.println(line);
                            if (line.isEmpty()) {
                                //读取到空行请求结束
                                break;
                            }
                        }

                        Thread.sleep(5000);//模拟处理响应慢

                        //发送响应
                        byte[] bytes = response.getBytes("utf-8");
                        out.write(bytes);
                        out.flush();

                        socket.close();
                        //结束一次通信
                    }catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(task);
        }

    }
}
