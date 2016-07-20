package com.iqdep.dataflow.utils;

import java.io.Serializable;
import java.util.function.Function;

public interface SerializableFunction<T,U> extends Function<T,U>,Serializable{

}
