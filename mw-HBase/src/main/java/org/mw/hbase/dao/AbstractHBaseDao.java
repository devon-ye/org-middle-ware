package org.mw.hbase.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mw.hbase.dto.ScanQueryParam;
import org.mw.hbase.storage.HBaseSearch;
import io.prometheus.client.Histogram;
import org.hbase.async.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author dewen.ye
 * @date 2019/8/14 16:49
 * @since
 */
public abstract class AbstractHBaseDao<T> extends RecursiveTask implements HBaseDao<T> {

	@Autowired
	private HBaseSearch search;

	protected static final ObjectMapper MAPPER = new ObjectMapper();

	protected static final String SPLIT_SYMBOL = "#";

	private static final Histogram transDuration = Histogram.build()
			.name("hbase_trans_time")
			.help("Duration of service requests in milliseconds.")
			.labelNames("method")
			.buckets(0.1, 1, 10, 100) // Specifies the bucket range, in milliseconds
			.register();

	public List<KeyValue> getRequest(String table, String rowKey, String family) {
		return search.getRequest(table, rowKey, family);
	}

	public List<KeyValue> getRequest(String table, String rowKey, String family, String qualifier) {
		return search.getRequest(table, rowKey, family, qualifier);
	}

	public ArrayList<ArrayList<KeyValue>> getBatchRequest(String table, List<String> rowKeys, String family) {
		return search.getBatchRequest(table, rowKeys, family);
	}

	public ArrayList<ArrayList<KeyValue>> getBatchRequest(String table, List<String> rowKeys, String family, String qualifier) {
		return search.getBatchRequest(table, rowKeys, family, qualifier);
	}

	public ArrayList<ArrayList<KeyValue>> scanRequest(ScanQueryParam scanQueryParam) {
		return search.scanRequest(scanQueryParam);
	}

	public ArrayList<ArrayList<ArrayList<KeyValue>>> scanBatchRequest(ScanQueryParam scanQueryParam) {
		return search.scanBatchRequest(scanQueryParam);
	}

	public ArrayList<ArrayList<ArrayList<KeyValue>>> asyncScanBatchRequest(ScanQueryParam scanQueryParam) {
		return search.asyncScanBatchRequest(scanQueryParam);
	}



	public ArrayList<ArrayList<ArrayList<KeyValue>>> parallelScanBatchRequest(ScanQueryParam scanQueryParam) {
		return search.asyncParallelScanRequest(scanQueryParam);
	}

	public abstract T transListKeyValueToObject(List<KeyValue> keyValueList);

	protected List<T> transListListKeyValueToObject(ArrayList<ArrayList<KeyValue>> listListList) {
		List<T> result = null;
		if (listListList == null) {
			return new ArrayList<>(0);
		} else {
			result = new ArrayList<>(listListList.size() * 2);
		}
		long startTime = System.currentTimeMillis();
		try {
			for (ArrayList<KeyValue> keyValueArrayList : listListList) {
				if (keyValueArrayList == null) continue;
				T row = transListKeyValueToObject(keyValueArrayList);
				result.add(row);
			}
		} finally {
			transDuration.labels("toObject").observe(System.currentTimeMillis()-startTime);

		}
		return result;
	}

	protected List<T> transListListListKeyValueToObject(ArrayList<ArrayList<ArrayList<KeyValue>>> keyValueListListList) {
		List<T> result = null;
		if (keyValueListListList == null) {
			return new ArrayList<>(0);
		} else {
			result = new ArrayList<>(keyValueListListList.size() * 2);
		}
		long startTime = System.currentTimeMillis();
		try {
			for (ArrayList<ArrayList<KeyValue>> keyValueListList : keyValueListListList) {
				if (keyValueListList == null) continue;
				for (ArrayList<KeyValue> keyValueArrayList : keyValueListList) {
					T row = transListKeyValueToObject(keyValueArrayList);
					result.add(row);
				}
			}
		} finally {
			transDuration.labels("toObject").observe(System.currentTimeMillis()-startTime);
		}
		return result;
	}


}
