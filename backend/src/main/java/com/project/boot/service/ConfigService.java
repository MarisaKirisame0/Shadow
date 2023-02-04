package com.project.boot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.boot.bean.config;
import com.project.boot.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfigService {
    @Autowired
    ConfigMapper configMapper;

    /* 查询指定设置内容(按照设置名setup进行查询)
     * 该方法在部分页面初始化时会被使用
     * 返回值:相应设置文件的URL(String) */
    public String setupQuery(String setup){

        /* 查询设置文件的URL */
        QueryWrapper<config> wrapper = Wrappers.query();   //创建Wrapper条件构造器
        wrapper.like("setup", setup);   //设置Wrapper条件构造器
        return configMapper.selectOne(wrapper).getUrl();
    }
}
