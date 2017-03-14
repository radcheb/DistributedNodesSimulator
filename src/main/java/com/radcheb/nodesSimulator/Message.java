package com.radcheb.nodesSimulator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Message {

	String sendeAdr;
	int senderPort;
	byte[] content;
}
