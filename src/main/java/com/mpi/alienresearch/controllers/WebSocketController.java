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

	public void sendDirector(String message) {
		this.template.convertAndSend("/topic/director", new WebSocketMessage(message));
	}

	public void sendGroup(String message, String group) {
		this.template.convertAndSend("/topic/group/"+group, new WebSocketMessage(message));
	}
	
	public void sendUser(String message, String user) {
		this.template.convertAndSend("/topic/user"+user, new WebSocketMessage(message));
	}
}
