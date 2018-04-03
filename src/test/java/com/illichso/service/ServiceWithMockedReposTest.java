package com.illichso.service;

import com.google.gson.Gson;
import com.illichso.Application;
import com.illichso.model.dto.NewUserPost;
import com.illichso.model.dto.UserPost;
import com.illichso.model.dto.UserRepost;
import com.illichso.repository.PostRepository;
import com.illichso.repository.UserRepository;
import com.illichso.rest.PostController;
import com.illichso.service.impl.PostServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PostController.class)
@ContextConfiguration(classes = {Application.class})
public class ServiceWithMockedReposTest {

    private String basePath = "/post";
    private String userName = "Adam";
    private String text = "Adam wrote some text";
    private Gson gson;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private PostServiceImpl postService;

    @Before
    public void setUp() {
        gson = new Gson();
    }

    @Test
    public void shouldSaveValidPostForNewUser() throws Exception {
        NewUserPost postContentForNewUser = new NewUserPost(userName, text);
        String postContentForNewUserAsJson = gson.toJson(postContentForNewUser);
        mockMvc.perform(post(basePath)
                .content(postContentForNewUserAsJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveValidPostForExistingUser() throws Exception {
        UserPost postContentForExistingUser = new UserPost(anyLong(), userName, text);
        String postContentForExistingUserAsJson = gson.toJson(postContentForExistingUser);
        mockMvc.perform(post(basePath)
                .content(postContentForExistingUserAsJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeletePost() throws Exception {
        long postId = ThreadLocalRandom.current().nextLong();
        String uri = UriComponentsBuilder.newInstance()
                .path(basePath)
                .path("/delete/{postId}")
                .build()
                .expand(postId)
                .encode()
                .toUriString();
        mockMvc.perform(delete(uri))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldSaveRepost() throws Exception {
        long userId = ThreadLocalRandom.current().nextLong();
        long postId = ThreadLocalRandom.current().nextLong();
        UserRepost repost = new UserRepost(userId, postId);
        String repostContentAsJson = gson.toJson(repost);

        String uri = UriComponentsBuilder.newInstance()
                .path(basePath)
                .path("/repost")
                .build()
                .toUriString();

        mockMvc.perform(post(uri)
                .content(repostContentAsJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetWall() throws Exception {
        long userId = ThreadLocalRandom.current().nextLong();
        String uri = UriComponentsBuilder.newInstance()
                .path(basePath)
                .path("/wall/{userId}")
                .build()
                .expand(userId)
                .encode()
                .toUriString();
        mockMvc.perform(get(uri))
                .andExpect(status().is2xxSuccessful());
    }
}
