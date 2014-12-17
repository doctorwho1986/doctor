package com.doctor.localhost;


import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.doctor.localhost.util.HBaseHelper;

public class HBasePut {

	public static void main(String[] args) throws Throwable{
		String tableName = "hbase_test";
		String[] columnFamilyName = {"cf1","cf2"};
		String[] columnQualifier={"q1","q2"};
		Configuration configuration = HBaseConfiguration.create();
		HBaseHelper helper = HBaseHelper.getInstance(configuration );
		helper.create(tableName, columnFamilyName);
		
		String rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello doctor");
		
		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello doctor 000");
		Result result = helper.get(rowKey);
		System.out.println(result);
		System.out.println("rowKey:" + Bytes.toString(result.getRow()));
		System.out.println("cf1:q1  : " + Bytes.toString(result.getValue(Bytes.toBytes(columnFamilyName[0]), Bytes.toBytes(columnQualifier[0]))) );
		
		rowKey = UUID.randomUUID().toString();
		helper.put(rowKey, columnFamilyName[0], columnQualifier[0], "hello doctor 100");
		helper.delete(rowKey);
		
		helper.close();
	}

}
