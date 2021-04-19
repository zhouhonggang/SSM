package com.javakc.ssm.user.service.impl;

import com.javakc.ssm.user.dao.UserDao;
import com.javakc.ssm.user.entity.User;
import com.javakc.ssm.user.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User entity) {
        Object salt = ByteSource.Util.bytes(entity.getName());
        SimpleHash simpleHash = new SimpleHash("MD5", entity.getPass(), salt, 1024);
        entity.setPass(simpleHash.toHex());
        return userDao.insert(entity);
    }

    @Override
    public int update(User entity) {
        return userDao.update(entity);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public User queryById(int id) {
        return userDao.queryById(id);
    }

    @Override
    public long queryByCount() {
        return userDao.queryByCount();
    }

    @Override
    public User queryByUser(String name) {
        return userDao.queryByUser(name);
    }

}
