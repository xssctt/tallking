package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Category;
import com.example.tallking.entity.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostMapper extends BaseMapper<Post> {



    @Insert("insert into post(categoryid,content,status,title) values (#{post.categoryid},#{post.content},#{post.status},#{post.title})")
    void createPost(@Param("post") Post post);


    int insertSelective(Post post);

    @Insert("insert into where id=#{id}")
    void addfavorite(@Param("id") Integer id);

    @Update("update post set favoritecount=#{favoritecount} where id=#{id}")
    void updatafavoriteCount(@Param("favoritecount") Integer favoritecount,@Param("id") Integer id);



    @Update("update post set likecount=#{likecount} where id=#{id}")
    void updatalikeCount(@Param("likecount") Integer likecount,@Param("id") Integer id);

    @Update("update post set viewcount=#{viewcount} where id=#{id}")
    void updataviewCount(@Param("viewcount") Integer viewcount,@Param("id") Integer id);

    @Select(" select viewcount from post where id=#{id}")
    Integer findviewcountById(@Param("id") Integer id);


    @Select(" select * from post where categoryid=#{categoryid} limit #{offset},#{pageSize} ")
    List<Post> findpostlist(@Param("pageSize") Integer pageSize,@Param("offset") Integer offset,@Param("categoryid") Integer categoryid);


    @Select(" select count(*) from post where categoryid=#{categoryid}")
    Integer findBycategoryidall(@Param("categoryid") Integer categoryid);




    @Select(" select * from post where parentid=#{parentid} limit #{offset},#{pageSize} ")
    List<Post> findreplypostlist(@Param("pageSize") Integer pageSize,@Param("offset") Integer offset,@Param("parentid") Integer parentid);


    @Select(" select count(*) from post where parentid=#{parentid}")
    Integer findreplyByfuidall(@Param("parentid") Integer parentid);




         //查询 用户发的帖子
    @Select(" select * from post where userid=#{userid} limit #{offset},#{pageSize} ")
    List<Post> findByiddall(@Param("pageSize") Integer pageSize,@Param("offset") Integer offset,@Param("userid") Integer userid);



    @Select(" select count(*) from post where userid=#{userid}")
    Integer finduserByidall(@Param("userid") Integer userid);


    @Select(" select * from post ")
    List<Post> selectAllPost();

    @Update("update post set replycount=#{replycount}")
    void updatereply(@Param("replycount") Integer replycount);
}