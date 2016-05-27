package com.sermo.thrift.service.impl;

import org.apache.thrift.TException;

import com.sermo.thrift.service.HelloWorldService;

public class HelloServiceImpl implements HelloWorldService.Iface{

	public HelloServiceImpl() {
	}

	@Override
	public String sayHello(String username) throws TException {
		return "Hi, " + username + " ,welcome to thrift";
	}

}
