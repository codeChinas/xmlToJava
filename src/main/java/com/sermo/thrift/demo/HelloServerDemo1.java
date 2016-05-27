package com.sermo.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import com.sermo.thrift.service.HelloWorldService;
import com.sermo.thrift.service.impl.HelloServiceImpl;

/**
 * TThreadPoolServer 服务模型
 * 线程池服务模型, 使用标准的阻塞式 IO, 预先创建一组线程处理请求
 * @author rqq
 *
 */
public class HelloServerDemo1 {
	public static final int SERVER_PORT = 8090;
	
	public void startServer(){
		try {
			System.out.println("TThreadPoolServer start ...");
			
			TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloServiceImpl());
			
			TServerSocket tServerSocket = new TServerSocket(SERVER_PORT);
			TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(tServerSocket);
			tArgs.processor(tProcessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			
			// 线程池服务模型, 使用标准的阻塞式 IO, 预先创建一组线程处理请求
			TServer server = new TThreadPoolServer(tArgs);
			server.serve();
			
		} catch (Exception e) {
			System.out.println("server start error !!!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		HelloServerDemo1 demo1 = new HelloServerDemo1();
		demo1.startServer();
	}
}
