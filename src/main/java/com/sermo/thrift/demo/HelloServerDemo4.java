package com.sermo.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.sermo.thrift.service.HelloWorldService;
import com.sermo.thrift.service.impl.HelloServiceImpl;

/**
 * TNonblockingServer 
 * 使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式。
 * @author rqq
 *
 */
public class HelloServerDemo4 {
	public static final int SERVER_PORT = 8090;
	
	public void startServer(){
		try {
			System.out.println("TNonblockingServer start ...");
			
			TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloServiceImpl());
			
			TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(SERVER_PORT);
			TNonblockingServer.Args tArgs = new TNonblockingServer.Args(tNonblockingServerSocket);
			tArgs.processor(tProcessor);
			tArgs.transportFactory(new TFramedTransport.Factory());
			tArgs.protocolFactory(new TCompactProtocol.Factory());
			
			TServer server = new TNonblockingServer(tArgs);
			server.serve();
			
		} catch (Exception e) {
			System.out.println("server start error !!!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		HelloServerDemo4 server = new HelloServerDemo4();
		server.startServer();
	}
}
