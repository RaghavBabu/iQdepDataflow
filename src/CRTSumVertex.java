import java.io.IOException;
import java.math.BigInteger;

import com.iqdep.dataflow.elements.BigIntegerElement;
import com.iqdep.dataflow.elements.TripleElement;
import com.iqdep.dataflow.io.Collector;
import com.iqdep.dataflow.io.OutputContext;
import com.iqdep.dataflow.utils.Triple;
import com.iqdep.dataflow.vertex.AbstractVertex;

public class CRTSumVertex extends AbstractVertex<TripleElement>{
	BigInteger CRT = BigInteger.ZERO;
	BigInteger B = BigInteger.ZERO;
	
	@Override
	public void execute(TripleElement element, OutputContext collector)
			throws IOException {
		Triple<BigInteger, BigInteger, BigInteger> triple = element.getElement();
		CRT = CRT.add(triple.getFirst());
		B = triple.getSecond();
	}

	@Override
	public void close(OutputContext collector) {
		System.out.println("Chinese remainder theorem value : "+CRT.remainder(B));
		try {
			collector.add(new BigIntegerElement(CRT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
