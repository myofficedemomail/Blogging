package com.blog.service;

import com.blog.payload.CommentDto;
import com.blog.payload.PostDto;

public interface CommentService {
	PostDto comment(CommentDto coomentdto,Integer postid);
	void deleteComment(Integer commentid);
}
