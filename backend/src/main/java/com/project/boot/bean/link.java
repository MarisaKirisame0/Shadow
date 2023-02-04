package com.project.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("link")
public class link {
    @TableId
    private int id;
    private String name;
    private String url;
    private String info;
    private String avatar;
}
