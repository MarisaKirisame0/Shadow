package com.project.boot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.boot.bean.basic;
import com.project.boot.mapper.BasicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {
    @Autowired
    BasicMapper basicMapper;

    /* 查询基础设置(网站标题、网站简介、网站初始运行时间、网站头像)
     * 返回值:基础设置内容 */
    public List<basic> basicSetupQuery(){
        QueryWrapper<basic> wrapper = Wrappers.query();   //创建Wrapper条件构造器
        return basicMapper.selectList(wrapper);
    }
}
