package com.radcheb.nodesSimulator;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lombok.Getter;

public class Node implements INode {

	private boolean ready;
	@Getter
	private String address;
	@Getter
	private int port;
	private BlockingQueue<Message> queue;
	private INetwork network;
	private final static int CAPACITY = 100;
	private final static int SLEEP = 10000;

	public Node() {
		this.ready = false;
		this.queue = new ArrayBlockingQueue<>(CAPACITY);
	}

	@Override
	public Queue<Message> getQueue() {
		return queue;
	}

	@Override
	public void run() {
		while (true) {
			if (!queue.isEmpty()) {
				Message receivedMsg = queue.poll();
//				log.info("Received msg={} from node {}:{}", new String(receivedMsg.content), receivedMsg.sendeAdr,
//						receivedMsg.senderPort);
				System.out.println("Received msg=" + new String(receivedMsg.content) + " from node "
						+ receivedMsg.sendeAdr + ":" + receivedMsg.senderPort);
			}
			Random rand = new Random();
			String dstAddress = "1.0.0." + rand.nextInt(3);
			Message msg = new Message(address, port, "testing".getBytes());
			System.out.println("Sending msg to node "+ dstAddress);
			network.sendMessage(msg, dstAddress, 0);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void setReady(String addr, int port, INetwork network) {
		this.network = network;
		this.address = addr;
		this.port = port;
		this.ready = true;
	}
}