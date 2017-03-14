package com.radcheb.nodesSimulator;

import java.util.Queue;

public interface INode extends Runnable{

	String getAddress();
	
	int getPort();

	default void PrettyPrint(){
		System.out.println("Node "+getAddress());
	}
	
	Queue<Message> getQueue();
	
	void setReady(String addr, int port, INetwork network);
}

