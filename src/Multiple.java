import java.io.IOException;
import java.math.BigInteger;

import com.iqdep.dataflow.elements.BigIntegerElement;
import com.iqdep.dataflow.io.Collector;
import com.iqdep.dataflow.io.OutputContext;
import com.iqdep.dataflow.vertex.AbstractVertex;

public class Multiple extends AbstractVertex<BigIntegerElement>{

	private static final long serialVersionUID = 8983135386205518370L;

	@Override
	public void execute(BigIntegerElement line, OutputContext collector) throws IOException {
		collector.add(new BigIntegerElement(line.getElement().multiply(BigInteger.valueOf(2))));
	}
	
}
