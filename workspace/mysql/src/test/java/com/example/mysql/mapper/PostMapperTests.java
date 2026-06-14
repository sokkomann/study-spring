package com.example.mysql.mapper;

import com.example.mysql.domain.PostVO;
import com.example.mysql.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostMapperTests {
    @Autowired
    private PostMapper postMapper;

    @Test
    public void testInsert() {
        PostDTO postDTO = new PostDTO();

        postDTO.setPostTitle("테스트 제목2");
        postDTO.setPostContent("테스트 내용2");
        postDTO.setMemberId(2L);

        postMapper.insert(postDTO);

        log.info("{}", postDTO.getId());
    }
    @Test
    public void testUpdate() {

        PostVO postVO = PostVO.builder()
                .id(1L)
                .postTitle("수정된 제목1")
                .postContent("수정된 내용1")
                .build();

        postMapper.update(postVO);

        log.info("게시글 수정댐: {}", postVO.getId());
    }
    @Test
    public void testUpdatePostReadCount() {

        Long postId = 1L;

        postMapper.updatePostReadCount(postId);

        log.info("{}", postId);
    }
}


















