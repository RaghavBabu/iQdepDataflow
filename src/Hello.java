import java.io.IOException;
import java.math.BigInteger;

import com.iqdep.dataflow.elements.BigIntegerElement;
import com.iqdep.dataflow.io.Collector;
import com.iqdep.dataflow.io.OutputContext;
import com.iqdep.dataflow.vertex.AbstractVertex;

public class Hello extends AbstractVertex<String>{
	
	private static final long serialVersionUID = -1749891852729638953L;

	@Override
	public void execute(String line, OutputContext collector) throws IOException {
		collector.add(new BigIntegerElement(BigInteger.valueOf(line.length())));
	}
}
