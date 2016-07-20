import java.io.IOException;
import java.math.BigInteger;

import com.iqdep.dataflow.elements.BigIntegerElement;
import com.iqdep.dataflow.io.Collector;
import com.iqdep.dataflow.io.OutputContext;
import com.iqdep.dataflow.vertex.AbstractVertex;

public class Sum extends AbstractVertex<BigIntegerElement>{
	private BigInteger total = BigInteger.ZERO;
	
	@Override
	public void execute(BigIntegerElement line, OutputContext collector) throws IOException {
		total = total.add(line.getElement());
		System.out.println(total);
	}
	
	@Override
	public void close(OutputContext collector){
		try {
			collector.add(new BigIntegerElement(total));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
