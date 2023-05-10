package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourseNotFoundException;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.repo.CategoryRepo;
import com.blog.repo.PostRepo;
import com.blog.repo.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private CategoryRepo categoryrepo;
	@Autowired
	private PostRepo postrepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postdto, Integer userid, Integer categoryid) {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourseNotFoundException("User", "Id", userid.toString()));
		Category category = categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Id", categoryid.toString()));
		Post post = new Post();
		post = modelMapper.map(postdto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setAddedDate(new Date());
		post.setImage("demo.png");
		post = postrepo.save(post);
		postdto = modelMapper.map(post, PostDto.class);
		return postdto;
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postid) {
		Post post = postrepo.findById(postid)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Id", postid.toString()));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImage(postdto.getImage());
		post.setTitle(postdto.getTitle());
		postrepo.save(post);
		postdto = modelMapper.map(post, PostDto.class);
		return postdto;
	}

	@Override
	public void deletePost(Integer postid) {
		Post post = postrepo.findById(postid)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Id", postid.toString()));
		postrepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pagesize, Integer pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber, pagesize);
		Page<Post> postPage=postrepo.findAll(pageable);
		List<Post> findAllPost = postPage.getContent();
		List<PostDto> listPostDto =findAllPost.stream().map((post)->{
			return modelMapper.map(post, PostDto.class);
		}).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(listPostDto);
		postResponse.setLastPage(postPage.isLast());
		postResponse.setPageNumber(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalElement((int) postPage.getTotalElements());
		postResponse.setTotalPage(postPage.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postid) {
		Post post = postrepo.findById(postid)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Id", postid.toString()));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPostByUser(Integer userid,Integer pagesize, Integer pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber, pagesize);
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourseNotFoundException("User", "Id", userid.toString()));
		Page<Post> postPage = postrepo.findAllByUser(user,pageable);
		List<PostDto> listPostDto =postPage.getContent().stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(listPostDto);
		postResponse.setLastPage(postPage.isLast());
		postResponse.setPageNumber(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalElement((int) postPage.getTotalElements());
		postResponse.setTotalPage(postPage.getTotalPages());
		return postResponse;
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryid,Integer pagesize, Integer pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber, pagesize);
		Category category = categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Id", categoryid.toString()));
		Page<Post> postPage = postrepo.findAllByCategory(category,pageable);
		List<PostDto> listPostDto = postPage.getContent().stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(listPostDto);
		postResponse.setLastPage(postPage.isLast());
		postResponse.setPageNumber(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalElement((int) postPage.getTotalElements());
		postResponse.setTotalPage(postPage.getTotalPages());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		return postrepo.findAll().stream()
				.filter(post -> post.getContent().toLowerCase().contains(keyword.toLowerCase()) || post.getTitle().toLowerCase().contains(keyword.toLowerCase()))
				.map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
