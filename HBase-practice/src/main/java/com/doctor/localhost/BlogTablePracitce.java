package com.doctor.localhost;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.doctor.localhost.util.HBaseHelper;

public class BlogTablePracitce {
	private static final String blogTableName = "blog";
	private static final String[] columnFamilyName = { "info", "content" };
	private static final String[] columnQualifierName = { "title", "author", "category", "content" };

	public static void main(String[] args) throws Throwable {
		Configuration configuration = HBaseConfiguration.create();
		HBaseHelper helper = HBaseHelper.getInstance(configuration);

		helper.create(blogTableName, columnFamilyName);
		 helper.put("30131020162535", columnFamilyName[0], columnQualifierName[0], "Why use HBase?");
		 helper.put("30131020162535", columnFamilyName[0], columnQualifierName[1], "Jane Doe");
		 helper.put("30131020162535", columnFamilyName[0], columnQualifierName[2], "Persistence");
		 helper.put("30131020162535", columnFamilyName[1], columnQualifierName[3], "HBase is a column-oriented...");
		
		 helper.put("31131020162535", columnFamilyName[0], columnQualifierName[0], "Why use HBase?");
		 helper.put("32131020162535", columnFamilyName[0], columnQualifierName[1], "Jane Doe");
		 helper.put("33131020162535", columnFamilyName[0], columnQualifierName[2], "Persistence");
		 helper.put("34131020162535", columnFamilyName[1], columnQualifierName[3], "HBase is a column-oriented...");
		 
		 helper.put("30131020162545", columnFamilyName[0], columnQualifierName[0], "Intro to Bigtable");
		 helper.put("30131020162545", columnFamilyName[0], columnQualifierName[1], "John");
		 helper.put("30131020162545", columnFamilyName[0], columnQualifierName[2], "Persistence");
		 helper.put("30131020162545", columnFamilyName[1], columnQualifierName[3], "Google Bigtable is...");
		
		 helper.put("30161020162545", columnFamilyName[0], columnQualifierName[0], "Intro to Bigtable");
		 helper.put("30171020162545", columnFamilyName[0], columnQualifierName[1], "John");
		 helper.put("30181020162545", columnFamilyName[0], columnQualifierName[2], "Persistence");
		 helper.put("30191020162545", columnFamilyName[1], columnQualifierName[3], "Google Bigtable is...");
		 
		helper.toString(helper.scan()).forEach(System.out::println);

		Scan scan = new Scan(Bytes.toBytes("20131020162535"), Bytes.toBytes("32831020162565"));
		System.out.println("-------------");
		helper.toString(helper.scan(scan)).forEach(System.out::println);
		
		System.out.println("-------------");
		scan.setFilter(new PageFilter(2));
		helper.toString(helper.scan(scan)).forEach(System.out::println);

	}
}
