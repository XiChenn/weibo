package com.example.weibohuati.weiboservice.facade;

import com.example.weibohuati.model.Weibo;
import com.example.weibohuati.service.result.ServiceResult;

import java.util.List;

public interface WeiboService {
    ServiceResult<Weibo> get(Integer id);

    ServiceResult<Weibo> create(Weibo weibo);

    ServiceResult<List<Weibo>> list();

    ServiceResult like(Integer userId, Integer weiboId);
}

