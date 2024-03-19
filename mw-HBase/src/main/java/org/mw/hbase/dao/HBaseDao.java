package org.mw.hbase.dao;

import org.mw.hbase.dto.KeyRangeTuple;
import java.util.List;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2019/10/28 00:44
 * @description
 */
public interface HBaseDao<T> {

    T getRequest(String key);

    List<T> getBatchRequest(List<String> keys);

    List<T> scanRequest(KeyRangeTuple keyRangeTuple);

    List<T> scanBatchRequest(List<KeyRangeTuple> keyRangeTuples);

    List<T> asyncScanBatchRequest(List<KeyRangeTuple> keyRangeTuples);

    List<T>  parallelScanBatchRequest(List<KeyRangeTuple> keyRangeTuples);
}
