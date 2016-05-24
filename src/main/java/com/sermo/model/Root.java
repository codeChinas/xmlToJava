package com.sermo.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("root")
public class Root {
	
	private Head head;
	private DisasterdataOrder disasterdataOrder;
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public DisasterdataOrder getDisasterdataOrder() {
		return disasterdataOrder;
	}
	public void setDisasterdataOrder(DisasterdataOrder disasterdataOrder) {
		this.disasterdataOrder = disasterdataOrder;
	}
	
}
