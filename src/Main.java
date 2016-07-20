import java.io.IOException;

import com.iqdep.dataflow.builder.BuilderException;
import com.iqdep.dataflow.scheduler.DataFlowJob;
import com.iqdep.dataflow.vertex.VertexList;

public class Main {

	public static void main(String[] args) throws BuilderException, IOException {

		DataFlowJob job = new DataFlowJob();
		job.writeTextFile("CRT_out");
		
		VertexList root = 
				job.readTextFile("CRT")
		.map((line) -> line.length(), 1)
		.<Integer>filter((nbr) -> nbr > 2, 1)
		.sumInt();
		
		job.setRoot(root);
		job.run();
	}
}
