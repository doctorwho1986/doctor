package com.doctor.localhost.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseHelper {
	private Configuration configuration;
	private HBaseAdmin hBaseAdmin;
	private String tableName;

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	private HBaseHelper(Configuration configuration) throws IOException {
		this.configuration = configuration;
		hBaseAdmin = new HBaseAdmin(configuration);
	}

	public static HBaseHelper getInstance(Configuration configuration) throws IOException {
		return new HBaseHelper(configuration);
	}

	public boolean isExist(String tableName) throws IOException {
		return hBaseAdmin.isTableAvailable(tableName);
	}

	public void drop(String tableName) throws IOException {
		if (isExist(tableName)) {
			hBaseAdmin.disableTable(tableName);
			hBaseAdmin.deleteTable(tableName);
		}

	}

	public void create(String tableName, String... columnFamilyName) throws IOException {
		this.tableName = tableName;
		if (isExist(tableName)) {
			return;
		}
		drop(tableName);
		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
		for (String familyName : columnFamilyName) {
			tableDescriptor.addFamily(new HColumnDescriptor(familyName));
		}
		hBaseAdmin.createTable(tableDescriptor);

	}

	public void put(String rowKey, String family, String qualifier, String value) throws IOException {
		HTable hTable = new HTable(configuration, tableName);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		hTable.put(put);
		hTable.close();
	}

	public Result get(String rowKey) throws IOException {
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

	public void delete(String rowKey, HashMap<String, String> familyQualifier) throws IOException {
		HTable hTable = new HTable(configuration, tableName);
		Delete delete = new Delete(Bytes.toBytes(rowKey));

		for (Entry<String, String> item : familyQualifier.entrySet()) {
			if (item.getKey() != null && item.getValue() != null) {
				delete.deleteColumns(Bytes.toBytes(item.getKey()), Bytes.toBytes(item.getValue()));
				continue;
			}

			if (item.getKey() != null) {
				delete.deleteFamily(Bytes.toBytes(item.getKey()));
			}
		}

		hTable.delete(delete);
		hTable.close();
	}

	public List<Result> scan() throws IOException {
		Scan scan = new Scan();
		return scan(scan);
	}

	public List<Result> scan(Scan scan) throws IOException {
		List<Result> list = new ArrayList<>();

		try (HTable hTable = new HTable(configuration, tableName);
				ResultScanner resultScanner = hTable.getScanner(scan);) {

			for (Result result : resultScanner) {
				list.add(result);
			}
		}
		return list;
	}

	public List<String> toString(final List<Result> result) {

		List<String> list = new ArrayList<>(result.size());

		StringBuilder stringBuilder = new StringBuilder(256);
		for (Result r : result) {
			stringBuilder.delete(0, stringBuilder.length());

			stringBuilder.append("{rowKey=" + Bytes.toString(r.getRow())).append(",");
			List<Cell> rawCells = r.listCells();
			if (rawCells == null) {
				return list;
			}

			for (Cell cell : rawCells) {
				timestamp.setTime(cell.getTimestamp());
				stringBuilder.append("column=")
						.append(Bytes.toString(CellUtil.cloneFamily(cell)))
						.append(":")
						.append(Bytes.toString(CellUtil.cloneQualifier(cell)))
						.append(",")
						.append("timestamp=")
						.append(timestamp.toLocalDateTime().format(dateTimeFormatter))
						.append(",value=")
						.append(Bytes.toString(CellUtil.cloneValue(cell)))
						.append(",");
			}
			stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
			stringBuilder.append("}");
			list.add(stringBuilder.toString());
		}

		return list;
	}

	public void close() {
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
