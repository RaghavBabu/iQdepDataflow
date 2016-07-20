package com.iqdep.dataflow.vertex;

import java.io.IOException;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;

import com.iqdep.dataflow.io.OutputContext;

public class SparkSql extends AbstractVertex<String>{
	
	String sql;
	
	public SparkSql(String sql){
		this.sql = sql;
	}
	
	@Override
	public void execute(String Line, OutputContext collector) throws IOException {
		SparkContext context = new SparkContext("ec2-50-112-192-125.us-west-2.compute.amazonaws.com", "IOT");
		SQLContext sqlContext = new SQLContext(context);
		sqlContext.sql(sql);
		
	}

}
