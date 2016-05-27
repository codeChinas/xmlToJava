package com.sermo.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.sermo.thrift.service.HelloWorldService;
import com.sermo.thrift.service.impl.HelloServiceImpl;

/**
 * TFramedTransport 
 * 半同步半异步的服务端模型，需要指定为： TFramedTransport 数据传输的方式。
 * @author rqq
 *
 */
public class HelloServerDemo3 {
	public static final int SERVER_PORT = 8090;
	
	public void startServer(){
		try {
			System.out.println("TNonblockingServer start ...");
			
			TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloServiceImpl());
			
			TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(SERVER_PORT);
			THsHaServer.Args tArgs = new THsHaServer.Args(tNonblockingServerSocket);
			tArgs.processor(tProcessor);
			tArgs.transportFactory(new TFramedTransport.Factory());
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			
			TServer server = new TNonblockingServer(tArgs);
			server.serve();
			
		} catch (Exception e) {
			System.out.println("server start error !!!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		HelloServerDemo3 server = new HelloServerDemo3();
		server.startServer();
	}
}
