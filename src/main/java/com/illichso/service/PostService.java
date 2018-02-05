package com.illichso.service;

import com.illichso.model.dto.Like;
import com.illichso.model.dto.UserPost;
import com.illichso.model.dto.UserRepost;
import com.illichso.model.entity.Post;

import java.util.List;

public interface PostService {

    void savePost(UserPost userPost);

    void saveRepost(UserRepost userRepost);

    void deletePost(long postId);

    List<Post> getWall(long userId);

    List<Post> getTimeline(long userId);

    void likePost(Like like);

    void unlikePost(Like like);
}
