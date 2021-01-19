import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        //CLOSED 抽象状态
        Socket socket = new Socket("127.0.0.1",8888);
        //已经是ESTABLISHED 状态

        socket.getInputStream().read();
    }
}
