package springtaskdemo.jobs;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import springtaskdemo.configuration.ElasticsearchConfiguration;

@Service
public class MatchAllJob {

	@Resource
	private ElasticsearchConfiguration config;

	public String getClustername() throws UnknownHostException, InterruptedException, ExecutionException {
		Client client = config.esclient();
		StringBuilder sb= new StringBuilder("");
		SearchResponse response = new SearchResponse();
		response = client.prepareSearch("suggest.house").setQuery(QueryBuilders.matchAllQuery()).setSize(5).execute()
				.get();
		for(SearchHit hit:response.getHits().getHits()){
			sb.append(hit.getSourceAsString());
			sb.append("\n\r");
		}
		return sb.toString();
	}

}
