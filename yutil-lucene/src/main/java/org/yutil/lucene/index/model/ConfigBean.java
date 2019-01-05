package org.yutil.lucene.index.model;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
*
*@author Devonmusa
*@date   2017年8月17日
*/
public class ConfigBean {
	private long indexId;
	private String indexName = "default_index";
	private String indexSavePath = "/index/";
	private double indexReopenMaxStateSeconds = 10;
	private double indexReopenMinStateSeconds = 0.25;
	private int indexCommitSeconds = 60;
	private Analyzer analyzer = new StandardAnalyzer();
	
	public long getIndexId() {
		return indexId;
	}
	public String getIndexName() {
		return indexName;
	}
	public String getIndexSavePath() {
		return indexSavePath;
	}
	public double getIndexReopenMaxStateSeconds() {
		return indexReopenMaxStateSeconds;
	}
	public double getIndexReopenMinStateSeconds() {
		return indexReopenMinStateSeconds;
	}
	public int getIndexCommitSeconds() {
		return indexCommitSeconds;
	}
	public Analyzer getAnalyzer() {
		return analyzer;
	}
	public void setIndexId(long indexId) {
		this.indexId = indexId;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public void setIndexSavePath(String indexSavePath) {
		this.indexSavePath = indexSavePath;
	}
	public void setIndexReopenMaxStateSeconds(double indexReopenMaxStateSeconds) {
		this.indexReopenMaxStateSeconds = indexReopenMaxStateSeconds;
	}
	public void setIndexReopenMinStateSeconds(double indexReopenMinStateSeconds) {
		this.indexReopenMinStateSeconds = indexReopenMinStateSeconds;
	}
	public void setIndexCommitSeconds(int indexCommitSeconds) {
		this.indexCommitSeconds = indexCommitSeconds;
	}
	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	
}
