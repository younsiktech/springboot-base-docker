package kr.com.younsiktech.springboot.base.web.dto;

import kr.com.younsiktech.springboot.base.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
