package org.infinispan.cache.query;

import java.util.List;

import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.filter.FullTextFilter;
import org.hibernate.search.stat.Statistics;
import org.infinispan.Cache;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;

/**
 *
 * @author Devonmusa
 * @date 2017年6月21日
 */
public class CluseterQuery<K, V> {

	private CacheQuery<Object> clusteredQuery;
	private org.infinispan.Cache<K, V> cache;
	private SearchManager searchManager;
	private MatchAllDocsQuery matchAllDocsQuery;
	private Statistics statistics;

	public CluseterQuery(Cache<K, V> cache) {
		this.cache = cache;
	}

	public void init() {
		searchManager = Search.getSearchManager(cache);
		matchAllDocsQuery = new MatchAllDocsQuery();
		statistics =  searchManager.getStatistics();

	}

	public List<Object> getQueryAll() {
	//	matchAllDocsQuery.createWeight(searcher, needsScores);

		clusteredQuery = searchManager.getClusteredQuery(matchAllDocsQuery, Object.class);
		List<Object> result = clusteredQuery.list();
		return result;

	}

//	public Object getQuery() {
//	//	Query luceneQuery = new Query();
//	//	clusteredQuery = searchManager.getQuery(luceneQuery, Object.class);
//	//	FullTextFilter fullTextFilter = clusteredQuery.enableFullTextFilter(name);
//	}
	
	public long  getQueryExecutionCount() {
		return statistics.getSearchQueryExecutionCount();
	}
	
	public long getObjectsLoadedCount() {
		return statistics.getObjectsLoadedCount();
	}
}
