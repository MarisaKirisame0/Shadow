package com.project.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("message")
public class message {
    @TableId
    private long id;
    private String name;
    private String url;
    private String m;
    private String time;
    private String mail;
}
