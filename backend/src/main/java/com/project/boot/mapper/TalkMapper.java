package com.project.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.boot.bean.talk;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TalkMapper extends BaseMapper<talk> {
}