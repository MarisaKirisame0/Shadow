package com.project.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.boot.bean.message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<message> {
}
