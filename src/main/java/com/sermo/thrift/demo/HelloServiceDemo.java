package com.sermo.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.sermo.thrift.service.HelloWorldService;
import com.sermo.thrift.service.impl.HelloServiceImpl;

/**
 * TSimpleServer 服务端
 * 简单的单线程服务模型
 * @author rqq
 *
 */
public class HelloServiceDemo {
	
	public static final int SERVICE_PORT = 8090;
	
	public void startService(){
		try {
			System.out.println("TSimpleServer start ...");
			
			TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloServiceImpl());
			TServerSocket transport = new TServerSocket(SERVICE_PORT);
			TServer.Args tArgs = new TServer.Args(transport);
			tArgs.processor(tProcessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TSimpleServer(tArgs);
			server.serve();
			
		} catch (TTransportException e) {
			System.out.println("server start error!!");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		HelloServiceDemo service = new HelloServiceDemo();
		service.startService();
	}
}
