/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.socket.WebSocketServerInitializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {

    private int port = 8442;
    private InetAddress hostAddress;
    private static Logger logger = Logger.getLogger(Server.class);
    private EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    ChannelFuture channelFuture = null;
    Channel channel = null;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws CertificateException, SSLException, UnknownHostException {

        final BlockingQueue<String> qq = new LinkedBlockingQueue<>(1);

        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            final ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(final SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new WebSocketServerInitializer(qq));
                        }
                    });

            try {
                channelFuture = b.bind(port).sync().await();
                System.out.println(Server.class.getName() +
                        " started and listen on " + channelFuture.channel().localAddress());
                System.out.println("Web socket server started at port " + port + '.');
                System.out.println("Open your browser and navigate to http " + port + '/');
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                logger.error("InterruptedException " + ex.getLocalizedMessage());
            }
            // Wait until the server socket is closed.
            try {
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        } finally {
            close();
        }
    }

    private void close() {
        try {
            bossGroup.shutdownGracefully().sync();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            logger.log(Level.ERROR, "InterruptedException ", ex);
        }
        try {
            workerGroup.shutdownGracefully().sync();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            logger.log(Level.ERROR, "InterruptedException ", ex);
        }
    }

    public static void main(String[] args) throws Exception {
        new Server(8442).run();
        }
    }

