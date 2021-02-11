package temp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class Nio {
    public static void main(String[] args) throws IOException {
        LinkedList<SocketChannel> list = new LinkedList<>();
        ServerSocketChannel channel = ServerSocketChannel.open();   //开启监听
        channel.bind(new InetSocketAddress(9090));
        channel.configureBlocking(false);   //开启内核的非阻塞

        while (true) {
            SocketChannel client = channel.accept();    //不会阻塞,立刻有返回值

            if (null != client) {
                client.configureBlocking(false);
                list.add(client);
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            for (SocketChannel s : list) {
                int i = s.read(buffer); // -1 0 >0 此处也非阻塞
                if (i > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    System.out.println(bytes.toString());
                    buffer.clear();
                }
            }
        }
    }
}
