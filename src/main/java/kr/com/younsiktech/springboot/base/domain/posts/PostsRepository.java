package kr.com.younsiktech.springboot.base.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // DAO : db layer 접근자

}
