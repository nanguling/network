import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class 一个简单的HTTP客户端 {
    public static void main(String[] args) throws IOException {
        //http请求格式：请求行 + 请求头 + 请求体
        //请求行：方法 + URL + 版本（get / http/1.0\r\n）
        String request = "GET /examples/servlets/ HTTP/1.0\r\n" +
                "\r\n";

        //想办法通过 OS 提供的服务（经由传输层，将数据发送给127.0.0.1主机上的8080端口代表的进程）
        Socket socket = new Socket("127.0.0.1",8080);
        InputStream is = socket.getInputStream();//获取对方发送过来的数据
        OutputStream out = socket.getOutputStream();//发送给对方的数据

        //发送数据
        byte[] message = request.getBytes("utf-8");
        out.write(message);
        out.flush();

        //接收数据
        Scanner scanner = new Scanner(is,"utf-8");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
