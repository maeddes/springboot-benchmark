package com.example.load;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoadApplication {

	@Value("${KBV_ID:not_set}")
	private String id;
	
	@Value("${KBV_INIT:false}")
	private String loadOnStartup = "false";
	
	private boolean isLoaded = false;
	private byte[] payload = null;
	
	private static int MAX_LOAD = 300; //MB
	private static int TIME_SIZE_RATIO = 100; //MB processed per second

	@PostConstruct
	private void postConstruct(){

		System.out.println("In postConstruct");
		System.out.println("Xmx: "+Runtime.getRuntime().maxMemory());
		if(loadOnStartup.equals("true"))
			this.initializePayload(15, 150); 

	}

	@GetMapping("/ready")
	@ResponseBody
	public ResponseEntity<Object> isReady(){

		if (isLoaded)
        	return new ResponseEntity<Object>(HttpStatus.OK);
    	else
        	return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/status")
	@ResponseBody
	public String getStatus(){

		return "Service "+id+" with payload "+isLoaded;
	
	}

	@PostMapping("/loadService")
	public String loadService(){

		int size = (int) (Math.random() * MAX_LOAD);
		int time = (int) (size/TIME_SIZE_RATIO);

		initializePayload(time, size);
		return "Initialized service "+id+" with "+time+"sec delay and "+size+"MB load";

	}

	@PostMapping("/loadService/{size}")
	public String loadService(@PathVariable int size){

		int time = (int) (size/TIME_SIZE_RATIO);

		initializePayload(time, size);
		return "Initialized service "+id+" with "+time+"sec delay and "+size+"MB load";

	}

	@PostMapping("/loadService/{size}/{time}")
	public String loadService(@PathVariable int size, @PathVariable int time){

		initializePayload(time, size);
		return "Initialized service "+id+" with "+time+"sec delay and "+size+"MB load";

	}

	@PostMapping("/unloadService")
	public String unloadService() {

		clearPayload();
		return "Cleared service "+id;

	}

	private void initializePayload(int timeInSeconds, int sizeInMB){

		int size = sizeInMB*1000000;
		int time = timeInSeconds*1000;
		
		System.out.println("Initializing service "+id+" with "+time+"ms delay and "+size+"b load");
		payload = new byte[size];
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isLoaded = true;
		
	}

	private void clearPayload() {

		payload = null;
		System.gc();
		isLoaded = false;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(LoadApplication.class, args);
	}
}
