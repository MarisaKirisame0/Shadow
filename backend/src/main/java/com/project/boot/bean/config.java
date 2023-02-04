package com.project.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("config")
public class config {
    @TableId
    private int id;
    private String setup;
    private String url;
}
