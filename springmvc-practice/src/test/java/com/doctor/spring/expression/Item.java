package com.doctor.spring.expression;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;

@Component("item")
public class Item {

	@Value("itemA")//inject String directly
	private String name;

	@Value("10")
	private int qty;

	public String getName() {
		return name;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("name",name)
				.add("qty", qty)
				.toString();
	}

}
