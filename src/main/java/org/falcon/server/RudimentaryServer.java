package org.falcon.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class RudimentaryServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            Selector selector = Selector.open();
            ssc.bind(new InetSocketAddress(12345));

            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            sc.register(selector, SelectionKey.OP_READ);

            while(true){
                selector.select();
                for(Iterator i = selector.selectedKeys().iterator(); i.hasNext();) {
                    SelectionKey key = (SelectionKey) i.next();
                    if(key.isAcceptable()) handleConnectionRequest(key);
                    if(key.isReadable()) handleClientRequest(key);
                    i.remove();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnectionRequest(SelectionKey key) throws IOException{
        SocketChannel csc = ((ServerSocketChannel) key.channel()).accept();
        csc.configureBlocking(false);
        csc.register(key.selector(),SelectionKey.OP_READ);
    }

    private static void handleClientRequest(SelectionKey key) throws IOException {
        try {
            SocketChannel csc = ((SocketChannel) key.channel());
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            csc.read(buffer);
            buffer.flip();
            byte[] byteArray = new byte[11];
            buffer.get(byteArray);
            new MessageManagment(new String(byteArray));
            buffer.clear();



        } catch (Exception eW) {

        }
    }

}
