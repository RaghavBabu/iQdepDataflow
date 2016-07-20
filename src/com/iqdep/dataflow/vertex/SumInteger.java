package com.iqdep.dataflow.vertex;

import java.io.IOException;

import com.iqdep.dataflow.elements.IntegerElement;
import com.iqdep.dataflow.io.OutputContext;

public class SumInteger extends AbstractVertex<Integer>{
	
	int total = 0;
	
	@Override
	public void execute(Integer line, OutputContext collector) throws IOException {
		total += line;
	}
	
	@Override
	public void close(OutputContext collector){
		try {
			collector.add(total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
