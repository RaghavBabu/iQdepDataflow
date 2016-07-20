package com.iqdep.dataflow.actors;

import java.util.ArrayList;

import com.iqdep.dataflow.scheduler.StageList;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

public class ResultActor extends UntypedActor{
	ActorSelection actor;
	StageList stageList;
	ActorRef ref;
	
	public ResultActor(StageList stageList){
		this.stageList = stageList;
	}
	@Override
	public void preStart() throws Exception {
		Config conf = ConfigFactory.load("localworker");
		actor = getContext().actorSelection(conf.getString("akka.actor.job-manager"));
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String){
			actor.tell(stageList, getSelf());
			ref = getSender();
		}
		else if(message instanceof ArrayList){
			ref.tell(message, getSelf());
			
		}
	}
	
}
