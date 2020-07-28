package kr.com.younsiktech.springboot.base.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @BeforeEach
    public void initialize() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void postSaveAndLoad() {
        String title = "테스트 제목";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("test@gmail.com").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts testPost = postsList.get(0);

        Assertions.assertThat(testPost.getTitle()).isEqualTo(title);
        Assertions.assertThat(testPost.getContent()).isEqualTo(content);

        postsRepository.deleteAll();
    }

    @Test
    public void ussBaseTimeEntity() {
        LocalDateTime now = LocalDateTime.of(2020,6,1,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>> createdDate : "+posts.getCreatedDateTime() + "/ modifiedDate : " + posts.getModifiedDateTime());

        Assertions.assertThat(posts.getCreatedDateTime()).isAfter(now);
        Assertions.assertThat(posts.getModifiedDateTime()).isAfter(now);

    }
}