package com.project.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.boot.bean.post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends BaseMapper<post> {
}
