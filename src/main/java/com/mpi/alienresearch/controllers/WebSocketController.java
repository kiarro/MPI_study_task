package com.mpi.alienresearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import com.mpi.alienresearch.model.WebSocketMessage;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate template;

	public void greeting(String message) throws Exception {
		Thread.sleep(1000); // simulated delay
		 this.template.convertAndSend("/topic/messages", new WebSocketMessage(message));
	}
}
