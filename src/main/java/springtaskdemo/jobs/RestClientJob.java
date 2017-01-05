package springtaskdemo.jobs;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientJob {

	@Resource
	private RestTemplate resttemplate;

	private final String loginuri = "http://10.204.12.34:9999/ch/login?username={username}&password={password}";

	private final String suggesturi = "http://10.204.12.34:9999/ch/suggest?k={k}&s={s}&b={b}";

	/**
	 * 系统登陆获取token
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public String getToken() throws UnknownHostException {
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		urlVariables.put("username", "dev");
		urlVariables.put("password", "123456");
		Map<String, ?> resultmap = resttemplate.postForObject(loginuri, null, Map.class, urlVariables);
		if ((boolean) resultmap.get("status")) {
			return (String) resultmap.get("token");
		}
		return null;
	}

	public String suggest(String token) {
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		urlVariables.put("k", "b");
		urlVariables.put("s", "10");
		urlVariables.put("b", "house");

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", token);
		HttpEntity<String> entity = new HttpEntity<String>(requestHeaders);
//		resttemplate.exchange(entity, String.class);
		return resttemplate.exchange(suggesturi, HttpMethod.GET, entity, String.class, urlVariables).toString();
	

	}

}
