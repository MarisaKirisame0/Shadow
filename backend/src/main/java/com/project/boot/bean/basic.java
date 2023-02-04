package com.project.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("basic")
public class basic {
    @TableId
    private String title;
    private String motto;
    private String begin;
    private String avatar;
    private String code;
}
