package com.example.weibohuati.weiboservice.facade;

import com.example.weibohuati.model.Topic;
import com.example.weibohuati.service.result.ServiceResult;

public interface TopicService {
    ServiceResult<Topic> create(Topic topic);
    ServiceResult<Topic> get(Integer id);
}
