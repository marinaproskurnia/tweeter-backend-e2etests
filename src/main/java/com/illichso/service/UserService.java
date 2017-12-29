package com.illichso.service;

import com.illichso.model.entity.User;
import com.illichso.model.dto.Subscription;

import java.util.Set;

public interface UserService {

    void followUser(Subscription subscription);

    void unfollowUser(Subscription subscription);

    Set<User> getFollowers(long userId);

    Set<User> getFollowees(long userId);
}
