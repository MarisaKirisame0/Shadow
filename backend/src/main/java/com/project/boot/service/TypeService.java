package com.project.boot.service;

import com.project.boot.bean.type;
import com.project.boot.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    TypeMapper typeMapper;

    /*查询所有的类型
    * 返回值:所有类型列表*/
    public List<type> allTypes(){
        return typeMapper.selectList(null);
    }
}
