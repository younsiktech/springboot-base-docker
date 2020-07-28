package kr.com.younsiktech.springboot.base.web.controller;

import kr.com.younsiktech.springboot.base.domain.posts.Posts;
import kr.com.younsiktech.springboot.base.domain.posts.PostsRepository;
import kr.com.younsiktech.springboot.base.web.dto.PostsSaveRequestDto;
import kr.com.younsiktech.springboot.base.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void insertPosts() throws Exception {
        String title = "test title";
        String content = "test content";
        String author = "test@gmail.com";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author(author).build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
        Assertions.assertThat(all.get(0).getAuthor()).isEqualTo(author);
    }

    @Test
    public void updatePosts() throws Exception {

        String title = "test udpate";
        String content = "test content";
        String author = "test@gmail.com";

        Posts savedPosts = postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

        Long updateId = savedPosts.getId();
        String updateTitle = "title2";
        String updateContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(updateTitle).content(updateContent).build();

        String url = "http://localhost:" + port + "/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(updateContent);
        Assertions.assertThat(all.get(0).getAuthor()).isEqualTo(author);

    }

    @AfterEach
    public void clearPosts() throws Exception {
        postsRepository.deleteAll();
    }
}