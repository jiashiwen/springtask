package springtaskdemo;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import springtaskdemo.jobs.GetClusterNameJob;
import springtaskdemo.jobs.MatchAllJob;
import springtaskdemo.jobs.RestClientJob;

@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Resource
    private GetClusterNameJob job;
    
    @Resource
    private MatchAllJob match;

    @Resource
    private RestClientJob rest;
    
//    @Scheduled(fixedRate = 10000)
//    public void reportCurrentTime() {
//    	System.out.println("The time is now {}"+ dateFormat.format(new Date()));
//    }
//    
//    @Scheduled(cron="0 0/1 8-20 * * ?") 
//    public void executeUploadTask() {
//
//        // 间隔1分钟,执行工单上传任务              
//        Thread current = Thread.currentThread();  
//        System.out.println("定时任务2:"+current.getId());
//        log.info("ScheduledTest.executeUploadTask 定时任务2:"+current.getId() + ",name:"+current.getName());
//    }

    @Scheduled(cron="0 0/1 8-20 * * ?") 
    public void printclustername() throws UnknownHostException{
//    	 Thread current = Thread.currentThread();  
    	 
    	 log.info(job.getClass().getName()+":"+job.getClustername());
    }
    
    @Scheduled(cron="0 0/1 8-20 * * ?") 
    public void printmatchall() throws UnknownHostException, InterruptedException, ExecutionException{
  	 
    	 log.info(match.getClass().getName()+":"+match.getClustername());
    }
    
    @Scheduled(cron="0 0/1 8-20 * * ?") 
    public void printtoken() throws UnknownHostException, InterruptedException, ExecutionException{
  	 
    	 log.info(rest.getClass().getName()+":"+rest.getToken());
    }
    
    @Scheduled(cron="0 0/1 8-20 * * ?") 
    public void suggest() throws UnknownHostException, InterruptedException, ExecutionException{
  	 
    	String token=rest.getToken();
    	if(token != null){
    		 log.info(rest.getClass().getName()+":"+rest.suggest(token));
    	}
    	
    }
    
}
