package com.radcheb.nodesSimulator;

public interface INetwork extends Runnable{

	String registerNode(INode newNode);
	void removeNode(INode node);
	void sendMessage(Message msg, String dstAddr, int dstPort);
}
