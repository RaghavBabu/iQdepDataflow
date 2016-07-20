package com.iqdep.dataflow.vertex;

import java.io.IOException;

import com.iqdep.dataflow.io.OutputContext;
import com.iqdep.dataflow.utils.SerializableFunction;

public class Map<T, U extends Comparable> extends AbstractVertex<T> {

	SerializableFunction<T, U> applyFunc;

	public Map(SerializableFunction<T, U> func) {
		this.applyFunc = func;
	}

	@Override
	public void execute(T line, OutputContext collector) throws IOException {
		collector.add(applyFunc.apply(line));
	}
}