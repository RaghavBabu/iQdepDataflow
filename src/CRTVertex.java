import java.io.IOException;
import java.math.BigInteger;

import com.iqdep.dataflow.elements.BigIntegerElement;
import com.iqdep.dataflow.elements.TripleElement;
import com.iqdep.dataflow.io.Collector;
import com.iqdep.dataflow.io.OutputContext;
import com.iqdep.dataflow.utils.Triple;
import com.iqdep.dataflow.vertex.AbstractVertex;

public class CRTVertex extends AbstractVertex<TripleElement>{

	@Override
	public void execute(TripleElement element, OutputContext collector) 
			throws IOException {
		Triple<BigInteger, BigInteger, BigInteger> triple = element.getElement();
		BigInteger B = triple.getThird().divide(triple.getSecond());
		BigInteger C = triple.getFirst();
		BigInteger X = ExtendedEuclidean.moduloInverse(B, triple.getSecond());
		
		BigInteger product = B.multiply(C);
		product = product.multiply(X);
		triple = new Triple(product, triple.getThird(), BigInteger.ONE);
		
		TripleElement tripleElement = new TripleElement(triple);
		collector.add(tripleElement);
	}

}
