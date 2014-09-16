
/*
 * Copyright (C) 2014 The doctor Authors
 * https://github.com/doctorwho1986
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.hbase.Hbase权威指南ebook;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

/**
 * @author doctor
 *
 * @date 2014年9月14日 下午8:15:44
 */
public class PutExample3 {
	protected static final Logger log = Logger.getLogger(PutExample3.class.getName());
	private static Configuration hbaseConfiguration;
	static {
		hbaseConfiguration = HBaseConfiguration.create();
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws ZooKeeperConnectionException
	 * @throws MasterNotRunningException
	 */
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {

		String tableName = "testtable";
		String[] familys = { "family-1", "family-2", "family-3" };

		// HBaseAdmin hBaseAdmin = new HBaseAdmin(hbaseConfiguration);
		//
		// if (hBaseAdmin.tableExists(talbeName)) {
		// log.warning(String.format("{ %s is  existsed}", talbeName));
		// hBaseAdmin.disableTable(talbeName);
		// hBaseAdmin.deleteTable(talbeName);
		// log.info(String.format("{info:  table %s is delete }", talbeName));
		// }
		//
		// HTableDescriptor desc = new
		// HTableDescriptor(TableName.valueOf(talbeName));
		// desc.addFamily(new HColumnDescriptor("family1"));
		// desc.addFamily(new HColumnDescriptor("family2"));
		// desc.addFamily(new HColumnDescriptor("family3"));
		// hBaseAdmin.createTable(desc);
		// log.info(String.format("{ table %s is created }", talbeName));
		//
		// HTable hTable = new HTable(hbaseConfiguration, talbeName);
		//
		// Put put = new Put(UUID.randomUUID().toString().getBytes());
		// put.add(Bytes.toBytes("family1"), "qual1".getBytes(),
		// Bytes.toBytes("value1"));
		//
		// hTable.put(put);
		deleteTable(tableName);
		createTable(tableName, familys);
		String row = "row-1";
		deleteRow(tableName, row);

		String columnFamily = familys[0];
		String[] values ={"value-1","value-2","value-3"};
		String[] columns = {"col-1","col-2","col-3"};
		insertRecord(tableName, row, columnFamily, columns, values);
		deleteRow(tableName, row);
		insertRecord(tableName, row, columnFamily, columns, values);
		Cell[] selectRow = selectRow(tableName, row);
		Arrays.asList(selectRow).stream().forEach(r -> {
			System.out.println();
			System.out.print("Row Name: " + new String(CellUtil.cloneRow(r)) + "  ");
			System.out.print("column Name: " + new String(CellUtil.cloneQualifier(r)) + "  ");
			System.out.print("column Family: " + new String(CellUtil.cloneFamily(r)) + "  ");
			System.out.print("Timetamp: " + r.getTimestamp() + "  ");
			System.out.print("value: " + new String(CellUtil.cloneValue(r)) + "  ");
		});
	}

	public static void createTable(String tableName, String[] familys) {
		try (HBaseAdmin hBaseAdmin = new HBaseAdmin(hbaseConfiguration)) {

			if (hBaseAdmin.tableExists(tableName)) {
				log.info(String.join("", "{table is existed :'", tableName + "'"));
				deleteTable(tableName);

				log.info(String.join("", "{table is deleted: ", tableName, "}"));

			}

			HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
			for (String family : familys) {
				desc.addFamily(new HColumnDescriptor(family));
			}

			hBaseAdmin.createTable(desc);
			log.info(String.join("", "{table is created: ", tableName, "}"));

		} catch (IOException e) {
			log.info(String.join("", "{error:'" + e.getMessage(), "'"));
		}

	}

	public static boolean insertRecord(String tablename, String row, String columnFamily, String[] columns, String[] values) {

		try (HTable hTable = new HTable(hbaseConfiguration, tablename)) {

			Put put = new Put(row.getBytes());

			for (int i = 0, length = values.length; i < length; i++) {
				put.add(columnFamily.getBytes(), columns[i].getBytes(), values[i].getBytes());
			}
			hTable.put(put);

		} catch (IOException e) {
			log.severe(String.join(" ", "{ insertRecord error: ", "tablename", tablename, "}"));
			return false;
		}

		return true;
	}

	public static boolean deleteRow(String tablename, String rowkey) {

		try (HTable hTable = new HTable(hbaseConfiguration, tablename)) {
			Delete delete = new Delete(rowkey.getBytes());
			hTable.delete(delete);

		} catch (Exception e) {
			log.severe(String.join(" ", "{deleteRow error: row ", rowkey, "}"));
			return false;
		}
		return true;
	}

	public static Cell[] selectRow(String tablename, String rowKey) {

		try (HTable hTable = new HTable(hbaseConfiguration, tablename)) {
			Get get = new Get(rowKey.getBytes());
			Result result = hTable.get(get);
			return result.rawCells();
		} catch (Exception e) {
			log.severe(String.format("{selectRow error: tablename %s, rowKey %s }", tablename, rowKey));
		}

		return null;
	}

	public static boolean deleteTable(String tableName) {

		try (HBaseAdmin hBaseAdmin = new HBaseAdmin(hbaseConfiguration)) {
			if (hBaseAdmin.tableExists(tableName)) {
				hBaseAdmin.disableTable(tableName);
				hBaseAdmin.deleteTable(tableName);
				log.info(String.format("{deleteTable tableName : %s is success}", tableName));
				return true;
			}

		} catch (Exception e) {
			log.severe(String.format("deleteTable tableName : %s error : %s", tableName, e.getMessage()));
		}

		return false;
	}
}