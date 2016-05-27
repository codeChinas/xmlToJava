package com.sermo.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.sermo.thrift.service.HelloWorldService;

public class HelloClientDemo2 {
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;
	
	public void startClient(String userName){
		TTransport transport = null;
		try {
			
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			// 协议和服务端一致
			TProtocol protocol = new TCompactProtocol(transport);
			HelloWorldService.Client client = new HelloWorldService.Client(protocol);
			transport.open();
			String result = client.sayHello(userName);
			System.out.println("thrift client result = " + result);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
	
	public static void main(String[] args) {
		HelloClientDemo2 client = new HelloClientDemo2();
		client.startClient("sermo");
	}
}
