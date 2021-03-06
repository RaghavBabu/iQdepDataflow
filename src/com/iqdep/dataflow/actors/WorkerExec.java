package com.iqdep.dataflow.actors;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.iqdep.dataflow.messages.RegisterWorker;
import com.iqdep.dataflow.messages.WorkComplete;
import com.iqdep.dataflow.messages.WorkIsReady;
import com.iqdep.dataflow.messages.WorkRequest;
import com.iqdep.dataflow.messages.WorkToBeDone;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Function;
import akka.japi.Procedure;

public class WorkerExec extends UntypedActor {

	private final ActorRef worker;
	private final ActorRef jobManager;
	
	public WorkerExec(final Function<ActorRefFactory, ActorRef> f , ActorRef jobManager) throws Exception {
		this.worker = f.apply(context());
		this.jobManager = jobManager;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		unhandled(message);
	}

	private final Procedure<Object> idle = new Procedure<Object>() {
		public void apply(Object message) throws Exception {
			MethodUtils.invokeMethod(this, "handle", message);
		}
		
		public void handle(RegisterWorker workReady) {
			sendToMaster(new RegisterWorker(getSelf()));
		}
		
		public void handle(WorkToBeDone worktoBeDone) {
			worker.tell(worktoBeDone.getStage(), getSelf());
			getContext().become(working);
		}

		public void handle(WorkIsReady workReady) {
			sendToMaster(new WorkRequest(getSelf()));
		}
	};
	
	private final Procedure<Object> working = new Procedure<Object>() {
		public void apply(Object message) {
			if (message instanceof WorkComplete) {
				Map<Integer,String> path = ((WorkComplete) message).getPaths();
				sendToMaster(new WorkComplete(getSelf(), path, ""));
				getContext().become(idle);
			} else if (message instanceof WorkToBeDone) {
				System.out.println("Working...");
			} else {
				unhandled(message);
			}
		}
	};

	private void sendToMaster(Object message) {
		if(message instanceof RegisterWorker){
			jobManager.tell(message, getSelf());
		}else if(message instanceof WorkRequest){
			jobManager.tell(message, getSelf());
		}else{
			getSender().tell(message, getSelf());
		}
	}

	{
		getContext().become(idle);
	}
}
