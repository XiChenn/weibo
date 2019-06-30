package com.example.weibohuati.service.impl;

import com.example.weibohuati.mapper.UserWeiboLikeMapper;
import com.example.weibohuati.mapper.WeiboMapper;
import com.example.weibohuati.model.UserWeiboLike;
import com.example.weibohuati.model.Weibo;
import com.example.weibohuati.service.result.ServiceResult;
import com.example.weibohuati.weiboservice.facade.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.thymeleaf.util.StringUtils;

import java.util.List;

public class WeiboServiceImpl implements WeiboService {

    @Autowired
    private WeiboMapper weiboMapper;

    @Autowired
    private UserWeiboLikeMapper userWeiboLikeMapper;

    @Override
    public ServiceResult<Weibo> get(Integer id) {
        ServiceResult<Weibo> serviceResult = new ServiceResult<>();
        if (id == null) {
            serviceResult.setSuccess(false);
            serviceResult.setErrorMessage("The input ID is illegal");
            return serviceResult;
        }
        Weibo weibo = weiboMapper.selectByPrimaryKey(id);
        if (weibo == null) {
            serviceResult.setSuccess(false);
            serviceResult.setErrorMessage("The requested Weibo do not exist");
            return serviceResult;
        }
        serviceResult.setData(weibo);
        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<Weibo> create(Weibo weibo) {
        ServiceResult<Weibo> weiboServiceResult = new ServiceResult<Weibo>();
        if (StringUtils.isEmpty(weibo.getContent()) || StringUtils.isEmpty(weibo.getImg_url()) || weibo.getUser_id() == null) {
            weiboServiceResult.setSuccess(false);
            weiboServiceResult.setErrorMessage("Invalid Parameters");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return weiboServiceResult;
        }
        weiboMapper.insertSelective(weibo);
        weiboServiceResult.setData(weibo);
        return weiboServiceResult;
    }

    @Override
    public ServiceResult<List<Weibo>> list() {
        List<Weibo> weiboList = weiboMapper.listWeibo();
        ServiceResult<List<Weibo>> list = new ServiceResult<>();
        list.setData(weiboList);
        return list;
    }

    @Override
    @Transactional
    public ServiceResult like(Integer userId, Integer weiboId) {
        ServiceResult serviceResult = new ServiceResult();
        if (userId == null || weiboId == null) {
            serviceResult.setSuccess(false);
            serviceResult.setErrorMessage("Invalid parameters");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return serviceResult;
        }

        Weibo weibo = weiboMapper.selectByPrimaryKey(weiboId);
        if (weibo == null) {
            serviceResult.setSuccess(false);
            serviceResult.setErrorMessage("The weibo doesn't exist");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return serviceResult;
        }

        UserWeiboLike userWeiboLike = new UserWeiboLike();
        userWeiboLike.setUser_id(userId);
        userWeiboLike.setWeibo_id(weiboId);

        try {
            userWeiboLikeMapper.insertSelective(userWeiboLike);
        } catch (DuplicateKeyException ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return serviceResult;
        }

        weiboMapper.increaseLikeCount(weiboId);

        return serviceResult;
    }


}
