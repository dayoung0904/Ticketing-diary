package com.ticketingdiary.show;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/show")
@RestController
public class showRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
}
