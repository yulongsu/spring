package cn.su.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * java sdk the famous epoll bug. 著名的epoll-bug也可能会导致无效的状态选择和100%的CPU利用率。
 * 要解决epoll-bug的唯一方法是回收旧的选择器，将先前注册的通道实例转移到新创建的选择器上。
 *
 * @author suyulong
 * @date 2018/12/5 2:45 PM
 */
public class EpollBug {

    public static void server(int port) throws IOException {
        System.out.println("listen for connections on port:" + port);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        serverSocket.bind(inetSocketAddress);

        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();

        //SelectionKey.OP_ACCEPT —— 接收连接继续事件，表示服务器监听到了客户连接，服务器可以接收这个连接了
        //SelectionKey.OP_CONNECT —— 连接就绪事件，表示客户与服务器的连接已经建立成功
        //SelectionKey.OP_READ —— 读就绪事件，表示通道中已经有了可读的数据，可以执行读操作了（通道目前有数据，可以进行读操作了）
        //SelectionKey.OP_WRITE —— 写就绪事件，表示已经可以向通道写数据了（通道目前可以用于写操作）
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                //这里发生的是，不管有没有已选择的SelectionKey，Selector.select()方法总是不会阻塞并且会立刻返回。
                //这违反了Javadoc中对Selector.select()方法的描述，
                //Javadoc中的描述：Selector.select() must not unblock if nothing is selected.
                //(Selector.select()方法若未选中任何事件将会阻塞。)
                System.out.println("......");
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            Set readKeys = selector.selectedKeys();
            Iterator iterator = readKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey)iterator.next();
                iterator.remove();

                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("accepted connection from " + client);
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE, ByteBuffer.allocate(100));
                    }
                    if(key.isReadable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output =  (ByteBuffer) key.attachment();
                        client.read(output);
                    }
                    if(key.isWritable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer input = (ByteBuffer) key.attachment();
                        input.flip();
                        client.write(input);
                        //将缓冲区的当前位置和界限之间的字节（如果有）,复制到缓冲区的开始处。即将索引 p = position() 处的字节复制到索引 0 处，
                        //将索引 p + 1 处的字节复制到索引 1 处，依此类推，直到将索引 limit() - 1 处的字节复制到索引 n = limit() - 1 - p 处。
                        //然后将缓冲区的位置设置为 n+1，并将其界限设置为其容量。如果已定义了标记，则丢弃它。
                        //将缓冲区的位置设置为复制的字节数，而不是零，以便调用此方法后可以紧接着调用另一个相对 put 方法。
                        //从缓冲区写入数据之后调用此方法，以防写入不完整。例如，以下循环语句通过 buf 缓冲区将字节从一个信道复制到另一个信道：
                        input.compact();
                    }
                } catch (IOException e){
                    key.cancel();
                    key.channel().close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        server(11116);
    }

}
