package com.illichso.service.impl;

import com.illichso.exception.PostSizeExceedException;
import com.illichso.exception.UserDoesNotExistException;
import com.illichso.model.entity.Post;
import com.illichso.model.entity.User;
import com.illichso.model.dto.Like;
import com.illichso.model.dto.UserPost;
import com.illichso.model.dto.UserRepost;
import com.illichso.repository.PostRepository;
import com.illichso.repository.UserRepository;
import com.illichso.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.valueOf;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Value("${post-max-length}")
    private int postMaxLength;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void savePost(UserPost userPost) {
        if (isPostValid(userPost)) {
            User user = obtainUser(userPost);

            Post post = new Post(userPost.getText(), user);
            postRepository.save(post);

        } else {
            throw new PostSizeExceedException();
        }
    }

    private boolean isPostValid(UserPost userPost) {
        return valueOf(userPost.getText()).length() <= postMaxLength;
    }

    private User obtainUser(UserPost userPost) {
        User user = new User(userPost);
        if (user.getId() == 0) {
            user = userRepository.save(user);
        } else {
            user = userRepository.findOne(user.getId());
        }
        return user;
    }

    public void saveRepost(UserRepost userRepost) {
        Post post = postRepository.findOne(userRepost.getPostId());
        User user = userRepository.findOne(userRepost.getUserId());

        Post repost = new Post(user, post);

        postRepository.save(repost);
    }

    public void deletePost(long postId) {
        postRepository.delete(postId);
    }

    public List<Post> getWall(long userId) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            return postRepository.getWall(user);
        }
        throw new UserDoesNotExistException();
    }

    public List<Post> getTimeline(long userId) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            return postRepository.getTimeline(user.getFollowees());
        }
        throw new UserDoesNotExistException();
    }

    public void likePost(Like like) {
        User user = userRepository.findOne(like.getLikedUserId());
        Post post = postRepository.findOne(like.getPostId());
        post.likePost(user);
    }

    public void unlikePost(Like like) {
        User user = userRepository.findOne(like.getLikedUserId());
        Post post = postRepository.findOne(like.getPostId());
        post.unlikePost(user);
    }
}
