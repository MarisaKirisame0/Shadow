package com.project.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("type")
public class type {
    @TableId
    private int id;
    private String name;
    private String title;
    private String intro;
}
