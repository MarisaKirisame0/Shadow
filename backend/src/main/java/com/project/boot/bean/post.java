package com.project.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("post")
public class post {
    @TableId
    private int id;
    private String title;
    private String time;
    private String url;
    private String type;
    private String intro;
    private int hits;
}
