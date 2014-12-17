package com.doctor.localhost.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;



public class HBaseHelper {
	private Configuration configuration;
	private HBaseAdmin hBaseAdmin;
	private String tableName;
	
	private HBaseHelper(Configuration configuration) throws IOException{
		this.configuration = configuration;
		hBaseAdmin = new HBaseAdmin(configuration);
	}
	
	public static HBaseHelper getInstance(Configuration configuration) throws IOException{
		return new HBaseHelper(configuration);
	}
	
	public boolean isExist(String tableName) throws IOException{
		return hBaseAdmin.isTableAvailable(tableName);
	}
	
	public void drop(String tableName) throws IOException{
		if (isExist(tableName)) {
			hBaseAdmin.disableTable(tableName);
			hBaseAdmin.deleteTable(tableName);
		}
		
	}
	
	public void create(String tableName,String... columnFamilyName) throws IOException{
		drop(tableName);
		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
		for (String familyName : columnFamilyName) {
			tableDescriptor.addFamily(new HColumnDescriptor(familyName));
		}
		hBaseAdmin.createTable(tableDescriptor);
		this.tableName = tableName;
	}
	
	public void put(String rowKey,String family,String qualifier,String value) throws IOException{
		HTable hTable = new HTable(configuration, tableName);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		hTable.put(put);
		hTable.close();
	}
	
	public Result get(String rowKey) throws IOException{
		HTable hTable = new HTable(configuration, tableName);
		Get get = new Get(Bytes.toBytes(rowKey));
		Result result = hTable.get(get);
		hTable.close();
		return result;
	}
	
	public void delete(String rowKey) throws IOException {
		HTable hTable = new HTable(configuration, tableName);
		Delete delete = new Delete(Bytes.toBytes(rowKey));
		hTable.delete(delete);
		hTable.close();
	}
	public void close(){
		if (hBaseAdmin == null) {
			return;
		}
		
		try {
			hBaseAdmin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
