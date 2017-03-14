package com.radcheb.nodesSimulator;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class SimpleNetwork implements INetwork{

	private final static int CAPACITY = 500;
	private Queue<Tuple<Message, String>> depositQueue;
	private Map<String, Queue<Message>> backend;
	private long sequence;
	private Boolean ready;

	public SimpleNetwork() {
		this.sequence = 0;
		this.backend = new HashMap<String, Queue<Message>>();
		this.depositQueue = new ArrayBlockingQueue<>(CAPACITY);
		this.ready = false;
	}

	@Override
	public synchronized String registerNode(INode newNode) {
		String key = "1.0.0." + sequence;
		backend.put(key, newNode.getQueue());
		newNode.setReady(key, 0, this);
		sequence++;
		return key;
	}

	@Override
	public void removeNode(INode node) {
		backend.remove(node.getAddress());
	}

	@Override
	public void sendMessage(Message msg, String dstAddr, int dstPort) {
		depositQueue.offer(new Tuple<Message, String>(msg, dstAddr));
	}

	private void dispatchMesg(Tuple<Message, String> msg) {
		System.out.println("Dispactching msg to "+msg.y);
		Queue<Message> queue = backend.get(msg.y);
		queue.offer(msg.x);

	}

	@Override
	public void run() {
		ready = true;
		while (ready) {
			if(!depositQueue.isEmpty()){
				System.out.println("dispatching msg");
				Tuple<Message, String> msg = depositQueue.poll();
				dispatchMesg(msg);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
