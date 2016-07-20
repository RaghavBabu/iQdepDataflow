package com.iqdep.dataflow.vertex;

import java.io.IOException;

import com.iqdep.dataflow.io.OutputContext;
import com.iqdep.dataflow.utils.SerializablePredicate;

public class Filter<T extends Comparable> extends AbstractVertex<T> {

	SerializablePredicate<T> applyFunc;

	public Filter(SerializablePredicate<T> func) {
		this.applyFunc = func;
	}

	@Override
	public void execute(T line, OutputContext collector) throws IOException {
		if(applyFunc.test(line)){
			collector.add(line);
		}
	}
}