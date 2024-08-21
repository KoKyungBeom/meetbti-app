package com.springboot.post.mapper;

import com.springboot.comment.mapper.CommentMapper;
import com.springboot.post.dto.PostDto;
import com.springboot.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface PostMapper {
    Post postCreateDtoToPost (PostDto.Create create);
//    @Mapping(source = "memberId", target = "member.memberId")
    Post postUpdateDtoToPost (PostDto.Update update);
    PostDto.PatchResponse postToPostPatchResponseDto (Post post);
    default PostDto.GetResponse postToPostGetResponseDto (Post post,CommentMapper commentMapper) {
        PostDto.GetResponse.GetResponseBuilder response = PostDto.GetResponse.builder();
            response.postId(post.getPostId());
            response.title(post.getTitle());
            response.content(post.getContent());
            response.createdAt(post.getCreatedAt());
            response.modifiedAt(post.getModifiedAt());
            response.image(post.getMember().getImage());
            response.nickName(post.getMember().getNickname());
            response.mbti(post.getMember().getTestResults().get(post.getMember().getTestResults().size()-1).getMbti());
            response.heartCount(post.getHearts().size());
            response.viewCount(post.getViews().size());
            response.commentCount(post.getComments().size());
            response.comments(commentMapper.commentsToCommentResponseDtos(post.getComments()));

            return response.build();
    }
    List<PostDto.GetResponse> postsToPostResponseDtos (List<Post> posts);
}
