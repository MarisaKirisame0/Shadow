package com.project.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("talk")
public class talk {
    @TableId
    private int id;
    private String time;
    private String mess;
    private String name;
    private String url;
}
