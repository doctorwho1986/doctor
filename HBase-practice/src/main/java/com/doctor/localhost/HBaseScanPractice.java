package com.doctor.localhost;

import java.util.List;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;

import com.doctor.localhost.util.HBaseHelper;


public class HBaseScanPractice {

	public static void main(String[] args) throws Throwable {
		String tableName = "hbase_test";
		String[] columnFamilyName = { "cf1", "cf2" };
		String[] columnQualifier = { "q1", "q2" };
		Configuration configuration = HBaseConfiguration.create();
		HBaseHelper helper = HBaseHelper.getInstance(configuration);
		helper.create(tableName, columnFamilyName);

		String rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello doctor");

		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello doctor 000");

		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello doctor 100");
		
		List<Result> scan = helper.scan();
		helper.toString(scan).forEach(System.out::println);

	}

}
