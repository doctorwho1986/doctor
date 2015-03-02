package com.doctor.spring.expression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;

@Component("customer")
public class Customer {

	@Autowired
	private Item item;

	@Value("#{item.name}")//In Spring EL, you can reference a bean, and nested properties using a ‘dot (.)‘ symbol.
	private String itemName;
	
	@Value("#{item}")
	private Item itemValue;
	
	@Value("#{item.getQty()}")
	private int qty;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("item", item)
				.add("itemName", itemName)
				.add("itemValue", itemValue)
				.add("qty", qty)
				.toString();
	}

}
