package com.radcheb.nodesSimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		int nbrNodes = 3;
		INetwork network = new SimpleNetwork();
		List<INode> nodes = new ArrayList<INode>();
		for (int i = 0; i < nbrNodes; i++) {
			INode newNode = new Node();
			nodes.add(newNode);
			network.registerNode(newNode);
			System.out.println("Node "+i+" introduced at address "+newNode.getAddress()+":" +newNode.getPort());
//			log.info("Node {} introduced at address {}:{}", i, newNode.getAddress(), newNode.getPort());
		}

		System.out.println("Submitting Network");
		executorService.submit(network);
		for (int i = 0; i < nbrNodes; i++) {
			System.out.println("Submitting Node "+ i);
			executorService.submit(nodes.get(i));
		}
	}

}
