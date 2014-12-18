package com.doctor.localhost;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;

import com.doctor.localhost.util.HBaseHelper;
import com.google.common.collect.Maps;

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

		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello 100");

		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello hbase 0.98");
		
		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hbase 0.98");
		
		helper.delete(rowKey);
		
		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hbase 0.98 ---");
		HashMap<String, String> familyQualifier = Maps.newHashMap();
		familyQualifier.put(columnFamilyName[1], columnQualifier[1]);
		helper.delete(rowKey, familyQualifier );
		
		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hbase 0.98 ---111");
		familyQualifier .clear();
		familyQualifier.put(columnFamilyName[0], null);
		helper.delete(rowKey, familyQualifier );
		
		List<Result> scan = helper.scan();
		helper.toString(scan).forEach(System.out::println);

	}

}
