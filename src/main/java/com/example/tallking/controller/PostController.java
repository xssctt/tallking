package com.example.tallking.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.tallking.common.JsonResult;
import com.example.tallking.entity.*;
import com.example.tallking.mapper.PostMapper;
import com.example.tallking.mapper.PostdtoMapper;
import com.example.tallking.mapper.ReplyMapper;
import com.example.tallking.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {


    @Resource
    PostService postService;
    @Resource
    TagService tagService;
    @Resource
    CategoriesService categoriesService;
    @Resource
    TokenService tokenService;
    @Resource
    UserService userService;
    @Resource
    FavoriteService favoriteService;
    @Resource
    UserLikeService userLikeService;
    @Resource
    PostDtoService postDtoService;
    @Resource
    ReplyService replyService;

    @Resource
    PostMapper postMapper;
    @Resource
    PostdtoMapper postdtoMapper;
    @Resource
    ReplyMapper replyMapper;



    //POST
    ///api/post/create
    //createPost
    @PostMapping("/create")
    public JsonResult createPost(Integer categoryId, String content, Integer status, String tag, String title, String token){

        //帖子 类型 标签


        //标签  》
        Tag tag1=tagService.findTagByname(tag);
        //类型   》
        Category category=categoriesService.findCategoryById(categoryId);

        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());

       // Integer favoriteCount=favoriteService.selectAllByPostId();

        //帖子  》
        Post post=Post.builder()
                .category(category.getName())
                .categoryid(categoryId)
                .content(content)
                .status(status)

                .title(title)
                .userid(user.getId())
                .build();
         postService.createPost(post);





        return new JsonResult(post);
    }

    //POST
    ///api/post/delete
    //deletePost
    @DeleteMapping("/delete")
    public JsonResult deletePost(Integer id){

        postService.deletePost(id);

        return new JsonResult();
    }


    //POST
    ///api/post/favorite
    //favorite

    @PostMapping("/favoritep")
    public JsonResult favorite(Integer id,String token){

        //点击 post 收藏加1  use记录用户点赞
        //post的id  --》更新post信息   user根据token进行获取信息

        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());
       // Post post=postService.findPostById(id);

        Integer faid=favoriteService.findfavoriteByid(user.getId(),id);

        if(faid == null){
            Favorite favorite1=Favorite.builder().postid(id).userid(user.getId()).status(0).build();
            favoriteService.insertfavorite(favorite1);
        }



        Integer status=favoriteService.findstatusByid(user.getId(),id);


        if(status == 1){

            favoriteService.updatafavorite(faid,0);
            Integer favoriteCount=favoriteService.selectAllByPostId(id);
            postService.updatafavoriteCount(favoriteCount,id);
            return new JsonResult();
        }else {
            favoriteService.updatafavorite(faid,1);
            //更新post的收藏
            Integer favoriteCount=favoriteService.selectAllByPostId(id);
            postService.updatafavoriteCount(favoriteCount,id);

            return new JsonResult();
        }

    }


    //GET
    ///api/post/getTagOrder
    //getTagOrder
    @GetMapping("/getTagOrder")
    public JsonResult<List<Tag>> getTagOrder(Integer hotTagNumber){

       // List<Tag> tagList=tagService.findTagHot(hotTagNumber);


        return new JsonResult<>(tagService.findTagHot(hotTagNumber));
    }

    //POST
    ///api/post/like
    //like
    @PostMapping("/likep")
    public JsonResult like(Integer id,String token){
        //点击 post 收藏加1  use记录用户点赞
        //post的id  --》更新post信息   user根据token进行获取信息

        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());
        // Post post=postService.findPostById(id);

        Integer faid=userLikeService.findfavoriteByid(user.getId(),id);

        if(faid == null){
            UserLike userLike=UserLike.builder().postid(id).userid(user.getId()).status(0).build();
            userLikeService.insertfavorite(userLike);
        }



        Integer status=userLikeService.findstatusByid(user.getId(),id);


        if(status == 1){

            userLikeService.updatafavorite(faid,0);
            Integer favoriteCount=userLikeService.selectAllByPostId(id);

            postService.updatalikeCount(favoriteCount,id);
            return new JsonResult();

        }else {
            userLikeService.updatafavorite(faid,1);
            //更新post的收藏
            Integer favoriteCount=userLikeService.selectAllByPostId(id);

            postService.updatalikeCount(favoriteCount,id);

            return new JsonResult();
        }

    }



    @PostMapping("/favorite")
    public JsonResult favoritepostdto(Integer id,String token){
        //点击 post 收藏加1  use记录用户点赞
        //post的id  --》更新post信息   user根据token进行获取信息

        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());
        if(id == null){
            throw new RuntimeException("id is null");
        }
        Post post=postService.findPostById(id);

        Integer faid=postDtoService.findByid(user.getId(),id);
        System.out.println(faid);

        if(faid == null){
            Postdto postdto=Postdto.builder().
                    userid(user.getId()).username(user.getUsername()).nickname(user.getNickname()).avatar(user.getAvatar())
                    .postid(id).favorite("false").build();
            postDtoService.insertFavoritepost(postdto);
        }



        Boolean status=postDtoService.selectFavoriteByUserId(user.getId(),id);


        if(!status){

            postDtoService.updatafavorite(faid,"true");
            Integer favoriteCount=postDtoService.selectAllfavoriteByPostId(id);

            postService.updatafavoriteCount(favoriteCount,id);


            Map<String,Object> map=new HashMap<>();
            map.put("likeBool",true);
            map.put("likeCount",favoriteCount);

            return new JsonResult(map);

        }else {
            postDtoService.updatafavorite(faid,"false");
            //更新post的收藏
            Integer favoriteCount=postDtoService.selectAllfavoriteByPostId(id);

            postService.updatafavoriteCount(favoriteCount,id);


            Map<String,Object> map=new HashMap<>();
            map.put("likeBool",false);
            map.put("likeCount",favoriteCount);

            return new JsonResult(map);
        }

    }



    @PostMapping("/like")
    public JsonResult likepostdto(Integer id,String token){
        //点击 post 收藏加1  use记录用户点赞
        //post的id  --》更新post信息   user根据token进行获取信息

        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());
        // Post post=postService.findPostById(id);
       if(id == null){
           throw new RuntimeException("id id null");
       }

        Integer faid=postDtoService.findByid(user.getId(),id);

        System.out.println(faid);


        if(faid == null){
            Postdto postdto=Postdto.builder().
                    userid(user.getId()).username(user.getUsername())
                    .nickname(user.getNickname()).avatar(user.getAvatar())
                    .postid(id).like("false").build();
            System.out.println(postdto);
            postDtoService.insertlikepost(postdto);
        }



        Boolean status=postDtoService.selectlikeByUserId(user.getId(),id);


        if(!status){

            postDtoService.updatalike(faid,"true");
            Integer likeCount=postDtoService.selectAlllikeByPostId(id);

            postService.updatalikeCount(likeCount,id);

            Map<String,Object> map=new HashMap<>();
            map.put("likeBool",true);
            map.put("likeCount",likeCount);


            return new JsonResult(map);

        }else {
            postDtoService.updatalike(faid,"false");
            //更新post的收藏
            Integer likeCount=postDtoService.selectAlllikeByPostId(id);

            postService.updatalikeCount(likeCount,id);

            Map<String,Object> map=new HashMap<>();
            map.put("likeBool",false);
            map.put("likeCount",likeCount);


            return new JsonResult(map);
        }

    }







    //GET
    ///api/post/list
    //listPost
    @GetMapping("/list")
    public JsonResult<PostListDto> listPost(Integer categoryId,String createdBegin,String createdEnd,Integer limit,Integer offset,Integer page,Integer recommend,Integer status,String Tag,String title,String token,Integer total,Integer userid,String username){

        //categoryid,
        //
        // limit, page  ,  total--
        // offset,total、   createdBegin, createdEnd,
        //  recommend, status,
        //  Tag
        //  title
        //  token
        //  userid, username

        //Category category=categoriesService.findCategoryById(categoryid);


        List<Post> postList=postService.findpostlist(categoryId,limit,page);
        List<Postdt> postdtoList=new ArrayList<>();

        for (Post post:postList) {
            UserDto userDto=userService.getUserByid(post.getUserid());

            System.out.println(userDto.toString());
            System.out.println(post.toString());

            Boolean fav=postDtoService.selectFavoriteByUserId(post.getUserid(),post.getId());
            Boolean lik=postDtoService.selectlikeByUserId(post.getUserid(),post.getId());

            //Postdt
            Postdt postdt=Postdt.builder().post(post)
                    .favorite(fav).level(0).like(lik)
                    .avatar(userDto.getAvatar()).nickname(userDto.getNickname()).username(userDto.getUsername()).userid(userDto.getId())
                    .build();
            postdtoList.add(postdt);
        }


        int totalCount=postMapper.findBycategoryidall(categoryId);
        //Post post=postService.findPostById(1);
//        Postdto postdto=Postdto.builder().post(post).build();
//        List<Postdto> postdtoList=new ArrayList<>();
//        postdtoList.add(postdto);
        postSearchDto postsearchdto= postSearchDto.builder()
                .categoryid(categoryId)
                .createdBegin(createdBegin).createdEnd(createdEnd)
                .ids(null).limit(limit)
                .page(page).recommend(categoryId)
                .status(1).tag(title).title(title).username(username).userid(userid).total(totalCount)
                .build();

        PostListDto postListDto=PostListDto.builder().postDtoList(postdtoList).postSearchDto(postsearchdto).build();



        return new JsonResult<>(postListDto);
    }


    @GetMapping("/ddp")
    public JsonResult<List<Post>> month(Integer categoryId,Integer limit,Integer page){
        System.out.println(categoryId+"--"+limit+"---"+page);
        return new JsonResult<>(postService.findpostlist(categoryId,limit,page));
    }




    //GET
    ///api/post/month
    //month
    @GetMapping("/month")
    public JsonResult<List<Postdt>>month(String token){

        List<Post> postList=postService.selectAllPost();

        List<Postdt> postdtoList=new ArrayList<>();

        for (Post post:postList) {
            UserDto userDto=userService.getUserByid(post.getUserid());


            Boolean fav=postDtoService.selectFavoriteByUserId(post.getUserid(),post.getId());
            Boolean lik=postDtoService.selectlikeByUserId(post.getUserid(),post.getId());

            //Postdt
            Postdt postdt=Postdt.builder().post(post)
                    .favorite(fav).level(0).like(lik)
                    .avatar(userDto.getAvatar()).nickname(userDto.getNickname()).username(userDto.getUsername()).userid(userDto.getId())
                    .build();
            postdtoList.add(postdt);
        }

        return new JsonResult<>(postdtoList);
    }

    //GET
    ///api/post/week
    //week
    @GetMapping("/week")
    public JsonResult<List<Postdt>> week(String token){

        List<Post> postList=postService.selectAllPost();
        System.out.println(postList.toString());

        List<Postdt> postdtoList=new ArrayList<>();

        for (Post post:postList) {
            UserDto userDto=userService.getUserByid(post.getUserid());


            Boolean fav=postDtoService.selectFavoriteByUserId(post.getUserid(),post.getId());
            Boolean lik=postDtoService.selectlikeByUserId(post.getUserid(),post.getId());

            //Postdt
            Postdt postdt=Postdt.builder().post(post)
                    .favorite(fav).level(0).like(lik)
                    .avatar(userDto.getAvatar()).nickname(userDto.getNickname()).username(userDto.getUsername()).userid(userDto.getId())
                    .build();
            postdtoList.add(postdt);
        }



        return new JsonResult<>(postdtoList);
    }


    //GET
    ///api/post/my
    //myPost
    @GetMapping("/my")
    public JsonResult<PostListDto> myPost(Integer id,String createdBegin,String createdEnd,Integer limit,Integer offset,Integer page,Integer recommend,Integer status,String Tag,String title,String token,Integer total,Integer userid,String username){

        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto userDto= userService.getUserByid(tokenuser.getUserid());


        List<Post> postList=postService.findUseridlist(userDto.getId(),limit,page);
        List<Postdt> postdtList=new ArrayList<>();

        for (Post post:postList) {

            System.out.println(userDto.toString());
            System.out.println(post.toString());

            Boolean fav=postDtoService.selectFavoriteByUserId(post.getUserid(),post.getId());
            Boolean lik=postDtoService.selectlikeByUserId(post.getUserid(),post.getId());

            //Postdt
            Postdt postdto=Postdt.builder().post(post)
                    .favorite(fav).level(0).like(lik)
                    .avatar(userDto.getAvatar()).nickname(userDto.getNickname()).username(userDto.getUsername()).userid(userDto.getId())
                    .build();
            postdtList.add(postdto);
        }

        //
        total=postMapper.finduserByidall(userDto.getId());

        //Post post=postService.findPostById(1);
//        Postdto postdto=Postdto.builder().post(post).build();
//        List<Postdto> postdtoList=new ArrayList<>();
//        postdtoList.add(postdto);
        postSearchDto postsearchdto= postSearchDto.builder()
                .createdBegin(createdBegin).createdEnd(createdEnd)
                .ids(null)
                .status(status).tag(title).title(title)
                .username(username).userid(userid)
                .total(total).page(page).limit(limit)
                .build();

        PostListDto postListDto=PostListDto.builder().postDtoList(postdtList).postSearchDto(postsearchdto).build();



        return new JsonResult<>(postListDto);
    }




    //GET
    ///api/post/myFavorite
    //myFavorite
    @GetMapping("/myFavorite")
    public JsonResult<PostListDto> myFavorite(Integer categoryId,String createdBegin,String createdEnd,Integer limit,Integer offset,Integer page,Integer recommend,Integer status,String Tag,String title,String token,Integer total,Integer userid,String username){
//categoryid, createdBegin, createdEnd,
        // limit, offset, page, total
        //  recommend, status,
        //  Tag
        //  title
        //  token
        //  userid, username
        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());




        List<Postdto> postdtoList=postDtoService.findUseridfavoritelist(user.getId(),limit,page);

        List<Postdt> postdtList=new ArrayList<>();

        for (Postdto postdto:postdtoList) {
            //tiezi
            Post post=postService.findPostById(postdto.getPostid());
            //tiezi 发帖人
            UserDto userDto=userService.getUserByid(post.getUserid());


            //
            Postdt postdt=Postdt.builder().post(post)
                    .favorite(Boolean.valueOf(postdto.getFavorite())).level(0).like(Boolean.valueOf(postdto.getLike()))
                    .avatar(userDto.getAvatar()).nickname(userDto.getNickname()).username(userDto.getUsername()).userid(userDto.getId())
                    .build();

            postdtList.add(postdt);

        }

        //
        total=postdtoMapper.findByuseridfavoriteall(user.getId());

        //Post post=postService.findPostById(1);
//        Postdto postdto=Postdto.builder().post(post).build();
//        List<Postdto> postdtoList=new ArrayList<>();
//        postdtoList.add(postdto);
        postSearchDto postsearchdto= postSearchDto.builder()
                .createdBegin(createdBegin).createdEnd(createdEnd)
                .ids(null)
                .status(status).tag(title).title(title)
                .username(username).userid(userid)
                .total(total).page(page).limit(limit)
                .build();

        PostListDto postListDto=PostListDto.builder().postDtoList(postdtList).postSearchDto(postsearchdto).build();



        return new JsonResult<>(postListDto);
    }






    //POST
    ///api/post/reply
    //createdReply
    @PostMapping("/reply")
    public JsonResult createdReply(Integer id,String content,String token){
        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());

        if(id == null){
            throw new RuntimeException("id is null");
        }
        //以父id 为  这个id进行发帖
        System.out.println(id);
        Post post=Post.builder()
                .content(content)
                .userid(user.getId())
                .parentid(id)
                .status(1)
                .build();
        System.out.println(post);
        postService.createPost(post);
        postService.updatereply(id);

//        Reply reply=Reply.builder().content(content).postid(id).userid(user.getId()).build();
//
//        replyService.inserreply(reply);

        return new JsonResult();
    }




    //GET
    ///api/post/replyList
    //viewReplies
    @GetMapping("/replyList")
    public JsonResult<Replydto> viewReplies(Integer id,Integer limit,Integer offset,Integer page,String token,Integer total){

        Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(tokenuser.getUserid());

        //找到父id  是这个id的post
        System.out.println(id);
        List<Post> postList=postService.findpostReplylist(id,limit,page);
        List<Postdt> postdtoList=new ArrayList<>();

        for (Post post:postList) {
            UserDto userDto=userService.getUserByid(post.getUserid());

            System.out.println(userDto.toString());
            System.out.println(post.toString());

            Boolean fav=postDtoService.selectFavoriteByUserId(post.getUserid(),post.getId());
            Boolean lik=postDtoService.selectlikeByUserId(post.getUserid(),post.getId());

            Postdt postdt=Postdt.builder().post(post)
                    .favorite(fav).level(0).like(lik)
                    .avatar(userDto.getAvatar()).nickname(userDto.getNickname()).username(userDto.getUsername()).userid(userDto.getId())
                    .build();
            postdtoList.add(postdt);
        }
        total=postMapper.findreplyByfuidall(id);

        Pagination pagination=Pagination.builder().page(page).limit(limit).total(total).build();

        Replydto replydto=Replydto.builder().replyList(postdtoList).pagination(pagination).build();

        //Post post=postService.findPostById(1);
//        Postdto postdto=Postdto.builder().post(post).build();
//        List<Postdto> postdtoList=new ArrayList<>();
//        postdtoList.add(postdto);



//        List<Reply> replyList=replyService.findReplyById(id,limit,page);
//        total=replyMapper.selectcountByPostId(id);
//
//        List<Postdto> postdtoList=new ArrayList<>();
//        //
//
//        for (Reply reply:replyList) {
//            Post post=postService.findPostById(id);
//            UserDto userDto=userService.getUserByid(reply.getUserid());
//
//            Postdto postdto=postDtoService.selectPostdtoByUserId(userDto.getId(),id);
//
//            postdto.setPost(post);
//            postdtoList.add(postdto);
//        }


        return new JsonResult(replydto);
    }





    //GET
    ///api/post/view
    //view
    @GetMapping("/view")
    public JsonResult<Postviewdto> view(Integer id,String token){
        Post post=postService.findPostById(id);
        Integer viewcount=postMapper.findviewcountById(id);
        postMapper.updataviewCount(viewcount+1,id);
        Integer viewcountnew=postMapper.findviewcountById(id);
        post.setViewcount(viewcountnew);



        //Tokendto tokenuser=tokenService.getUserIdByToken(token);
        UserDto user= userService.getUserByid(post.getUserid());

        System.out.println(user);
        System.out.println(id);
        Integer postdtoid=postDtoService.findByid(post.getUserid(),id);

        if(postdtoid == null){
            Postdto postdto=Postdto.builder().
                    userid(user.getId()).username(user.getUsername()).nickname(user.getNickname()).avatar(user.getAvatar())
                    .postid(id).favorite("false").like("false").build();
            postDtoService.insertFavoritepost(postdto);
        }

        Postdto postdto=postDtoService.selectPostdtoByUserId(post.getUserid(),id);
        postdto.setPost(post);

        Postdt postdt=Postdt.builder()
                .avatar(postdto.getAvatar()).level(postdto.getLevel()).nickname(postdto.getNickname())
                .userid(postdto.getUserid()).username(postdto.getUsername())
                .favorite(Boolean.valueOf(postdto.getFavorite())).like(Boolean.valueOf(postdto.getLike())).build();
        postdt.setPost(post);


        Tag tag=tagService.findTagById(post.getTagid());
        List<Tag> tagList=new ArrayList<>();

        tagList.add(tag);


        Postviewdto postviewdto=Postviewdto.builder().postDto(postdt).tagList(tagList).like(Boolean.valueOf(postdto.getLike())).favorite(Boolean.valueOf(postdto.getFavorite())).build();


        return new JsonResult<>(postviewdto);
    }




}
