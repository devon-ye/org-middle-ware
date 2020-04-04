package org.mw.pool.thread;

/**
 * @author devon.ye
 * @datetime 2020/4/4 7:43 下午
 * @since
 */
public interface ThreadPool {

	int rejectSize();

	int incrementThreadTimes();

	int decrementThreadTimes();

}
