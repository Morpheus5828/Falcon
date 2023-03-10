package org.falcon.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class CentralMicroblog {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            Selector selector = Selector.open();
            ssc.bind(new InetSocketAddress(12345));
            System.out.println("\t\nSTARTING CentralMicroblog FALCON");
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
        FluxManagment flux = new FluxManagment();
        try {
            System.out.println("New Client connection");
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(2058);
            client.read(buffer);
            MessageManagment mm = new MessageManagment(new String(buffer.array()).trim());
            if(mm.messageToClient().get(0).equals("true")) {
                flux.addNewFlux(mm.getUsername());
            } else {
                // return error to client because no in database
                buffer.clear();
                buffer.put(mm.messageToClient().get(0).getBytes());
                buffer.flip();
                client.write(buffer);
                buffer.clear();
            }

            client.close();

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static void setUpFlux(SelectionKey key) throws IOException {

    }



}
