package com.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourseNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.payload.PostDto;
import com.blog.repo.CommentRepo;
import com.blog.repo.PostRepo;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public PostDto comment(CommentDto commentdto, Integer postid) {
		Post post = postRepo.findById(postid)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "postid", postid.toString()));
		Comment comment = modelmapper.map(commentdto, Comment.class);
		comment.setPost(post);
		commentRepo.save(comment);
		PostDto postdto = modelmapper.map(postRepo.findById(postid).get(), PostDto.class);
		return postdto;
	}

	@Override
	public void deleteComment(Integer commentid) {
		System.out.println("******************************"+commentid);
		Comment comment = commentRepo.findById(commentid)
				.orElseThrow(() -> new ResourseNotFoundException("Comment", "commentid", commentid.toString()));
		System.out.println(comment);
		commentRepo.delete(comment);
	}
}
