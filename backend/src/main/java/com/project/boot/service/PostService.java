package com.project.boot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.boot.bean.post;
import com.project.boot.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*服务层
* 查询文章数据库的服务*/

@Service
public class PostService {
    @Autowired
    PostMapper postMapper;

    private final int pageSize = 5;
    private long postTotal;
    private long pageTotal;

    /* 文章查询服务初始化,用于确定文章总数和文章查询总页数
       输入值:null
       返回值:void */
    public void init(){
        Page<post> page = new Page<>(1, pageSize);
        Page<post> pageParam = postMapper.selectPage(page, null);
        postTotal = pageParam.getTotal();
        pageTotal = pageParam.getPages();
    }

//    /* 查询首页
//       该方法在页面加载时就会执行，由此设置页面的数量、记录的数量
//       返回值:文章分页查询记录列表 */
//    public List<post>  InquireFirstPage(){
//        Page<post> page = new Page<>(1, pageSize);
//        QueryWrapper<post> wrapper = Wrappers.query();
//        wrapper.orderByDesc("id");
//        Page<post> pageParam = postMapper.selectPage(page, wrapper);
//        return pageParam.getRecords();
//    }

    /* 查询指定页码
    *  该方法在页面点击相应的页码选择的时候执行
    *  返回值:指定页码的分页查询记录列表 */
    public List<post>  pageQuery(long pageIndex){
        if(pageIndex > pageTotal)pageIndex=1;
        if(pageIndex < 0)pageIndex=1;
        QueryWrapper<post> wrapper = Wrappers.query();
        wrapper.orderByDesc("id");
        Page<post> page = new Page<>(pageIndex, pageSize);
        Page<post> pageParam = postMapper.selectPage(page, wrapper);
        return pageParam.getRecords();
    }

    /* 查询指定文章ID
    * 该方法在页面中被点击文章时执行
    * 返回值:相应ID的文章的记录信息 */
    public post  idQuery(int id){
        if(id > postTotal)id=1;
        if(id < 0)id=1;
        /* 获取之前的点击量，并更新点击量加一 */
        int hits = postMapper.selectById(id).getHits();
        post p = new post();
        p.setId(id);
        p.setHits(hits+1);
        postMapper.updateById(p);
        /* 返回指定文章查询 */
        return postMapper.selectById(id);
    }

    /* 按照关键词进行查询, 返回全部符合条件的数据项
     * 该方法在页面中搜索框确定按键被点击时触发
     * 返回值:相应带有关键词的全部文章的记录信息列表 */
    public List<post> keyWordQuery(String keyWord){
        QueryWrapper<post> wrapper = Wrappers.query();   //创建Wrapper条件构造器
        wrapper.like("title", keyWord).or().like("intro", keyWord);
        wrapper.orderByDesc("id");
        return postMapper.selectList(wrapper);
    }


    /* 按照Type进行查询, 返回全部符合条件的数据项
     * 该方法在页面中被点击分类中指定Type时执行
     * 返回值:相应符合Type的全部文章的记录信息列表 */
    public List<post> typeQuery(String postType){
        QueryWrapper<post> wrapper = Wrappers.query();   //创建Wrapper条件构造器
        wrapper.like("type", postType);   //设置Wrapper条件构造器
        wrapper.orderByDesc("id");
        return postMapper.selectList(wrapper);
    }


    /* 按照Type进行分页查询
     * 该方法在页面中被点击分类中指定Type时执行
     * 返回值:相应符合Type的文章的记录信息列表 */
    public Map<String, Object> typeQuery2(String postType, int PageIndex){
        int query_type_page_size = 4;//设定每次对Type查询分页尺寸

        Page<post> page = new Page<>(PageIndex, query_type_page_size);

        QueryWrapper<post> wrapper = Wrappers.query();   //创建Wrapper条件构造器
        wrapper.like("type", postType);   //设置Wrapper条件构造器
        wrapper.orderByDesc("id");

        List<post>typePostList = postMapper.selectList(wrapper);

        Page<post> pageParam = postMapper.selectPage(page, wrapper);
        Map<String,Object> PostQueryTypeRes = new HashMap<>();
        try{
            PostQueryTypeRes.put("PostList",pageParam.getRecords());
            PostQueryTypeRes.put("MaxPageIndex",pageParam.getPages());
        }
        catch(Exception e){
            System.out.println("按照Type查询文章执行数据转化出现错误");
        }
        return PostQueryTypeRes;
    }

    /* 增加一条新的记录
    * 该方法在后台管理系统中选择部署时执行
    * 返回值增加的文章记录的ID*/
    public int insert(post newPost){
        postMapper.insert(newPost);
        QueryWrapper<post> wrapper = Wrappers.query();
        wrapper.like("name", newPost.getTitle());
        return postMapper.selectOne(wrapper).getId();
    }

    /*获取总页数*/
    public long getPageTotal(){
        return this.pageTotal;
    }

    /*获取总文章数*/
    public long getPostTotal(){
        return this.postTotal;
    }
}
