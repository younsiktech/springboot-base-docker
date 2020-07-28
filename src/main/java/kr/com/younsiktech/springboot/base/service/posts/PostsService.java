package kr.com.younsiktech.springboot.base.service.posts;

import kr.com.younsiktech.springboot.base.domain.posts.Posts;
import kr.com.younsiktech.springboot.base.domain.posts.PostsRepository;
import kr.com.younsiktech.springboot.base.web.dto.PostsResponseDto;
import kr.com.younsiktech.springboot.base.web.dto.PostsSaveRequestDto;
import kr.com.younsiktech.springboot.base.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
}
