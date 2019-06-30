package com.example.weibohuati.controller;

import com.example.weibohuati.model.Topic;
import com.example.weibohuati.service.result.ServiceResult;
import com.example.weibohuati.weiboservice.facade.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/topic")
public class TopicController extends BaseController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/create")
    public Map createTopic(@RequestParam(name="title") String title,
                           @RequestParam(name="description") String description) {
        Topic topic =  new Topic();
        topic.setTitle(title);
        topic.setDescription(description);
        ServiceResult<Topic> serviceResult = topicService.create(topic);
        if (serviceResult == null) {
            return getErrorResponse("Topic service communication exception");
        } else if (!serviceResult.isSuccess()) {
            return getErrorResponse(serviceResult.getErrorMessage());
        }
        return getResponse(serviceResult.getData());
    }
}
