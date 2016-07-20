package com.iqdep.dataflow.elements;

import java.io.Serializable;

public interface Element<T> extends Serializable,Comparable<Element<T>>{
	public T getElement();
}
