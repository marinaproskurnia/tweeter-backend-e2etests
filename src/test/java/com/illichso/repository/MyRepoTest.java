package com.illichso.repository;

import com.illichso.Application;
import com.illichso.model.entity.Post;
import com.illichso.model.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {Application.class})
public class MyRepoTest {

    @Value("${post-max-length}")
    private int postMaxLength;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldSaveNewUserAndFindHimByName() {
        String name = "John";
        User user = new User(name);
        userRepository.save(user);

        User userFoundByName = userRepository.findByName(name);
        Assertions.assertThat(userFoundByName.getName().equals(name));
    }

    @Test
    public void shouldSavePostForNewUser() {
        String name = "John";
        User user = new User(name);
        userRepository.save(user);

        String text = "some test text";
        Post post = new Post(text, user);
        postRepository.save(post);

        Post postFoundByUser = postRepository.findByUser(user);
        User userRetrieved = postFoundByUser.getUser();
        Assertions.assertThat(userRetrieved.equals(user));
    }

    @Test
    public void shouldUpdatePost() {
        String name = "John";
        User user = new User(name);
        userRepository.save(user);

        String text = "some test text";
        Post post = new Post(text, user);
        postRepository.save(post);

        String textUpdated = "bright new test text";
        post.setText(textUpdated);
        postRepository.save(post);

        Post postFoundByUser = postRepository.findByUser(user);
        Assertions.assertThat(postFoundByUser.getText().equals(textUpdated));
    }

    @Test
    public void shouldThrowConstraintViolationExceptionWhenPostTextSizeExceeded() {
        String name = "John";
        User user = new User(name);
        userRepository.save(user);

        int justAboveMaxTextLength = postMaxLength + 1;
        String textExceededMaxLength = StringUtils.leftPad("", justAboveMaxTextLength, "#");
        Post post = new Post(textExceededMaxLength, user);

        try {
            postRepository.save(post);
        } catch (ConstraintViolationException exception) {
            Assertions.assertThat(exception).isInstanceOf(ConstraintViolationException.class)
                    .hasMessageContaining("Post cannot be longer than 140 characters");
        }

    }
}
