package com.example.weibohuati.service.impl;

import com.example.weibohuati.mapper.TopicMapper;
import com.example.weibohuati.model.Topic;
import com.example.weibohuati.service.result.ServiceResult;
import com.example.weibohuati.weiboservice.facade.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.thymeleaf.util.StringUtils;

public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    @Transactional
    public ServiceResult<Topic> create(Topic topic) {
        ServiceResult<Topic> result = new ServiceResult<>();
        if (StringUtils.isEmpty(topic.getTitle()) || StringUtils.isEmpty(topic.getDescription())) {
            result.setSuccess(false);
            result.setErrorMessage("Invalid parameters");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        try {
            topicMapper.insertSelective(topic);
        } catch (DuplicateKeyException ex) {
            result.setSuccess(false);
            result.setErrorMessage("Topic exists!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        result.setData(topic);

        return result;
    }

    @Override
    public ServiceResult<Topic> get(Integer id) {
        return null;
    }
}
