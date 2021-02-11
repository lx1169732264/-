package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Bio {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9090);
        while (true) {
            Socket client = server.accept();    //等待客户端连接时阻塞,占用时间片但不运行
            new Thread(() -> {  //每个客户端连接都新开辟线程进行处理
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        String s = reader.readLine();
                        if (null == s) {
                            client.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
