package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.Preproccesingservice;
import com.example.demo.service.Summarymodelservice;
import com.example.demo.service.TextSummarizationService;

@Controller
public class Homecontroller {

	
	Preproccesingservice ps = new Preproccesingservice();
	TextSummarizationService tss=new TextSummarizationService(new RestTemplate());
	Summarymodelservice sms= new Summarymodelservice();
	 
	@GetMapping("/")
	public String openhomepage() {
		return "Home";
	}

	@PostMapping("/summary")
	public String makesummary(@RequestParam String words,  Model m) {
		
		
	    String generatedSummary = sms.giveSummary(words);
	    int periodIndex = generatedSummary.lastIndexOf('.');
        if (periodIndex != -1) {
            // Keep the substring up to and including the last '.'
            generatedSummary = generatedSummary.substring(0, periodIndex + 1);
        }
        
	    
	    if (generatedSummary.isEmpty()) {
	        // Handle empty response
	        m.addAttribute("summary", "Failed to generate summary. Please try again.");
	    } else {
	        m.addAttribute("summary", generatedSummary);
	    }
	    
	    System.out.println(words);
	    System.out.println(generatedSummary);
	    return "Summary";
	}


}
