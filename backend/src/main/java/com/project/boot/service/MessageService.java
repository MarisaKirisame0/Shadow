package com.project.boot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.boot.bean.message;
import com.project.boot.bean.post;
import com.project.boot.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    private final int pageSize = 5;

    /* 查询指定页码
     *  该方法在页面点击相应的页码选择的时候执行
     *  返回值:指定页码的分页查询记录列表 */
    public Map<String, Object> pageQuery(long pageIndex){
        // 设置每页数据条目

        // 初始查询总页数和总记录数
        Page<message> pageParamInit = messageMapper.selectPage(new Page<>(1, pageSize), null);
        long messageTotal = pageParamInit.getTotal();
        long pageTotal = pageParamInit.getPages();
        // 检查页数是否超出限制
        if(pageIndex > pageTotal)pageIndex=1;
        if(pageIndex <= 0)pageIndex=1;
        // 设置query
        QueryWrapper<message> wrapper = Wrappers.query();
        wrapper.orderByDesc("id");
        // 指定页码查询
        Page<message> page = new Page<>(pageIndex, pageSize);
        Page<message> pageParam = messageMapper.selectPage(page, wrapper);
        // 封装数据
        Map<String,Object> messageQueryRes = new HashMap<>();
        try{
            messageQueryRes.put("messages",pageParam.getRecords());
            messageQueryRes.put("maxPageIndex",pageTotal);
        }
        catch(Exception e){
            System.out.println("留言数据查询时数据封装出现错误");
        }
        // 返回数据
        return messageQueryRes;
    }

    /* 增加一条新的留言
     * 输入值: message newMes
     * 返回值: void */
    public void insert(message newMes){
        // 插入数据
        messageMapper.insert(newMes);
    }

    public long getMessageTotal(){
        return messageMapper.selectPage(new Page<>(1, pageSize), null).getTotal();
    }
}
