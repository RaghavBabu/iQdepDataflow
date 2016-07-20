package com.iqdep.dataflow.vertex;

import java.io.IOException;

import com.iqdep.dataflow.io.OutputContext;

public class CountInteger extends AbstractVertex<Integer>{

	int nbrElement = Integer.MAX_VALUE;
	
	@Override
	public void execute(Integer line, OutputContext collector) throws IOException {
		nbrElement++;
	}
	
	@Override
	public void close(OutputContext collector){
		try {
			collector.add(nbrElement);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
