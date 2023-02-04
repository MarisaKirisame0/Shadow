package com.project.boot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.boot.bean.talk;
import com.project.boot.mapper.TalkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalkService {
    @Autowired
    TalkMapper talkMapper;

    /* 用途：获取友链数据库中所有的说说数据
     *  返回值：以各个talk类型的JavaBean风格类为元素的List，即List中是JSON数据元素，每个元素是一条说说
     *  */
    public List<talk> allTalks(){
        QueryWrapper<talk> wrapper = Wrappers.query();
        wrapper.orderByDesc("id");
        return talkMapper.selectList(wrapper);
    }
}
