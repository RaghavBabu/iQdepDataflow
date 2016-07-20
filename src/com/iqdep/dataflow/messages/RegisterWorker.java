package com.iqdep.dataflow.messages;

import akka.actor.ActorRef;

public class RegisterWorker extends WorkMessage{
	
	public RegisterWorker(ActorRef workerRef) {
		super(workerRef);
	}
}
