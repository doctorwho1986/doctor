package com.doctor.localhost;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class HBaseConnector {
	private static final Logger log = LoggerFactory.getLogger(HBaseConnector.class);
	
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		final String tableName = "doctor_hbase";
		Configuration configuration = HBaseConfiguration.create();
		configuration.addResource("hbasePracticeConfigDefault/hbase-site.xml");
		HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
		if (hBaseAdmin.isTableAvailable(tableName)) {
			log.info("'table exist': {}",tableName);
			hBaseAdmin.disableTable(tableName);
			hBaseAdmin.deleteTable(tableName);
		}
		HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
		desc.addFamily(new HColumnDescriptor("cf1"));
		desc.addFamily(new HColumnDescriptor("cf2"));
		hBaseAdmin.createTable(desc );
		boolean tableAvailable = hBaseAdmin.isTableAvailable(tableName);
		Preconditions.checkArgument(tableAvailable);
		log.info("{}",tableAvailable);
		hBaseAdmin.close();
	}
}
