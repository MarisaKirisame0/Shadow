package com.project.boot.service;

import com.project.boot.bean.link;
import com.project.boot.mapper.LinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    @Autowired
    LinkMapper linkMapper;

    /* 用途：获取友链数据库中所有的友链数据
    *  返回值：以各个friendlink的JavaBean风格类为元素的List，即List中是JSON数据元素，每个元素是一个友链
    *  */
    public List<link> allLinks(){
        return linkMapper.selectList(null);
    }
}
