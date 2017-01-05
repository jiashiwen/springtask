package springtaskdemo.jobs;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.springframework.stereotype.Service;

import springtaskdemo.configuration.ElasticsearchConfiguration;


@Service
public class GetClusterNameJob {
	
	@Resource
	private ElasticsearchConfiguration config;
	
    public String getClustername() throws UnknownHostException{
    	Client client=config.esclient();
    	ClusterHealthResponse healths = client.admin().cluster().prepareHealth().get();
    	
		return healths.getClusterName();
    }

}
