package com.project.boot.controller;

import com.project.boot.bean.*;
import com.project.boot.service.*;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    PostService postService;
    @Autowired
    TypeService typeService;
    @Autowired
    LinkService linkService;
    @Autowired
    TalkService talkService;
    @Autowired
    MessageService messageService;
    @Autowired
    ConfigService configService;
    @Autowired
    BasicService basicService;
    @Autowired
    MarkdownreaderService markdownreaderService;

    /* 功能:APP组件进行最初始化的查询
    *  输入值:null
    *  返回值:long, 文章总数 */
    @GetMapping("/web/init")
    @CrossOrigin(origins = "*")
    public long webInit(){
        postService.init();
        return postService.getPostTotal();
    }

    /* 功能: 前端页面加载时 获取 第一页文章、总页数、总文章数
     * 输入值: null
     * 返回值：Map<String,Object>，第一个元素是文章列表:JSON数组, 第二个元素是总页数, 第三个元素是总文章数 */
    @GetMapping("/web/load")
    @CrossOrigin(origins = "*")
    public Map<String,Object> webLoad(){
        List<post> postList = postService.pageQuery(1);  //分页查询获取文章列表。注意该方法必须执行，以确定总页数和总文章数。
        Map<String,Object> res = new HashMap<>();
        try{
            res.put("PostList",postList);
            res.put("MaxPageIndex",postService.getPageTotal());
            res.put("TotalPostCount",postService.getPostTotal());
            return res;
        }
        catch(Exception e){
           log.info("(ERROR:1)后端获取首页文章、类型、标签列表信息出现错误");
        }
        return null;
    }

    /* 功能：按照指定的页码获取分页查询文章列表
     * 输入值：long 页码值
     * 返回值：List<article> ,前端得到JSON数组，每个元素代表一个文章 */
    @GetMapping("/web/getPage")
    @CrossOrigin(origins = "*")
    public Map<String,Object> webGetPage(@RequestParam("pageIndex") long pageindex){
        List<post> postList = postService.pageQuery(pageindex);  //分页查询获取文章列表。注意该方法必须执行，以确定总页数和总文章数。
        try{
            Map<String,Object> res = new HashMap<>();
            res.put("PostList",postList);
            res.put("MaxPageIndex",postService.getPageTotal());
            res.put("TotalPostCount",postService.getPostTotal());
            return res;
        }
        catch(Exception e){
            log.info("(ERROR:1)后端获取分页查询文章、类型、标签列表信息出现错误");
        }
        return null;
    }

    /* 功能：按照指定ID查询文章，返回文章的信息与文章具体内容转化后的HTML片段
    *  输入值：long变量数字，是文章的ID
    *  返回值：Map<String,Object>，第一个元素是文章信息:JSON，第二个元素是文章内容转化的HTML片段:String */
    @GetMapping("/web/getPost")
    @CrossOrigin(origins = "*")
    public Map<String,Object> webGetPost(@RequestParam("ID") int id){
        Map<String,Object> res = new HashMap<>();
        post info = postService.idQuery(id);
        String content = markdownreaderService.MarkdownConvertHtml(info.getUrl());
        try{
            res.put("Info",info);
            res.put("Content",content);
            return res;
        }
        catch (Exception e){
            log.info("(ERROR:2)后端获取指定ID的文章信息与内容时出现错误");
        }
        return null;
    }

    /* 功能：按照指定Type查询文章，返回全部文章列表
     * 输入值：String:article_type 文章的Type
     * 返回值：List<post>JSON数组 */
    @GetMapping("/web/type")
    @CrossOrigin(origins = "*")
    public List<post> webType(@RequestParam("Type") String postType){
        return postService.typeQuery(postType);
    }

    /* 功能：按照指定关键词查询文章，返回全部文章列表
     * 输入值：String:keyWord 关键词
     * 返回值：List<post>JSON数组 */
    @GetMapping("/web/keyWord")
    @CrossOrigin(origins = "*")
    public List<post> webKeyWord(@RequestParam("KeyWord") String keyWord){
        return postService.keyWordQuery(keyWord);
    }

    /* 功能：按照指定Type查询文章，返回文章列表与最大查询页数
     * 输入值：String:article_type 文章的Type, int:PageIndex 分页查询的页码
     * 返回值：Map<String,Object>，第一个元素是文章列表:JSON数组，第二个元素是查询最大页码:long */
    @GetMapping("/web/type2")
    @CrossOrigin(origins = "*")
    public Map<String, Object> webType2(@RequestParam("Type") String postType, @RequestParam("Index") int pageIndex){
        return postService.typeQuery2(postType,pageIndex);
    }

    /* 功能：获取分类信息
     * 输入值：void
     * 返回值：List，返回前端JSON数组，所有友链信息，每个JSON是一个文章类型 */
    @GetMapping("/web/types")
    @CrossOrigin(origins = "*")
    public List<type> webTypes(){
        return typeService.allTypes();
    }

    /* 功能：获取友链信息
     * 输入值：void
     * 返回值：List，返回前端JSON数组，所有友链信息，每个JSON是一个友链 */
    @GetMapping("/web/links")
    @CrossOrigin(origins = "*")
    public List<link> webLinks(){
        return linkService.allLinks();
    }

    /* 功能：获取说说信息
     * 输入值：void
     * 返回值：List，返回前端JSON数组，所有说说信息 */
    @GetMapping("/web/talks")
    @CrossOrigin(origins = "*")
    public List<talk> webTalks(){
        return talkService.allTalks();
    }

    /* 功能：获取留言信息
     * 输入值：int PageIndex
     * 返回值：Map<String, Object>，返回前端JSON数组，所有留言信息，内含最大页码和留言数据 */
    @GetMapping("/web/messages")
    @CrossOrigin(origins = "*")
    public Map<String, Object> webMessages(@RequestParam("pageIndex") int pageIndex){
        return messageService.pageQuery(pageIndex);
    }

    /* 功能：插入一条留言
     * 输入值：message 留言数据
     * 返回值：void */
    @PostMapping("/web/addM")
    @CrossOrigin(origins = "*")
    public void webAddMessage(@RequestBody message newMes){
        newMes.setId(messageService.getMessageTotal());
        messageService.insert(newMes);
    }

    /* 功能：获取设置文件的内容
     * 输入值：String setup 设置的名称
     * 返回值：String，返回前端String，设置内容的HTML格式(设置文件将直接显示在网页上) */
    @GetMapping("/web/getSetup")
    @CrossOrigin(origins = "*")
    public String webGetSetup(@RequestParam("Setup") String setup){
        try{
            String url = configService.setupQuery(setup);
            return markdownreaderService.MarkdownConvertHtml(url);
        }
        catch (Exception e){
            log.info("(ERROR:3)后端获取指定设置文件内容时出现错误");
        }
        return null;
    }

    /* 功能：插入一条新的文章记录
     * 输入值：article 文章实例
     * 返回值：当前文章的ID */
    @PostMapping("/crud/AddPost")
    @CrossOrigin(origins = "*")
    public long crudAddPost(@RequestBody @NotNull post newPost){
        newPost.setTime(newPost.getTime().split("T")[0]);
        return postService.insert(newPost);
    }

    /* 功能：获取基础设置
     * 输入值：void
     * 返回值：列表 */
    @GetMapping("/web/getBasic")
    @CrossOrigin(origins = "*")
    public List<basic> webGetBasic(){
        return basicService.basicSetupQuery();
    }
}
