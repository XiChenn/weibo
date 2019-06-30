package com.example.weibohuati.service;

import com.alibaba.druid.util.StringUtils;
import com.example.weibohuati.mapper.UserMapper;
import com.example.weibohuati.model.User;
import com.example.weibohuati.service.result.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public ServiceResult register(String loginName, String password, String nickName, Integer age, Integer gender, String avatarUrl) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ServiceResult serviceResult = new ServiceResult();

        User user = new User();
        user.setLogin_name(loginName);
        user.setPassword(this.encodeByHash(password));
        user.setNick_name(nickName);
        user.setAge(age);
        user.setGender(gender.byteValue());
        user.setAvatar_url(avatarUrl);

        try {
            userMapper.insertSelective(user);
        } catch(DuplicateKeyException dupEx) {
            serviceResult.setSuccess(false);
            serviceResult.setData("User Login Duplicate");
        }

        return serviceResult;
    }

    private String encodeByHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String encodedStr = Base64.getEncoder().encodeToString(messageDigest.digest(str.getBytes("utf-8")));
        return encodedStr;
    }

    public ServiceResult login(String loginName, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ServiceResult serviceResult = new ServiceResult();

        User user = userMapper.selectByLoginName(loginName);
        if (user == null) {
            serviceResult.setSuccess(false);
            serviceResult.setData("Username or password error");
            return serviceResult;
        }

        String encrptPassword = this.encodeByHash(password);
        if (!StringUtils.equals(encrptPassword, user.getPassword())) {
            serviceResult.setSuccess(false);
            serviceResult.setData("Username or password error");
            return serviceResult;
        }

        serviceResult.setData(user);
        return serviceResult;
    }
}
