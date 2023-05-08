package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourseNotFoundException;
import com.blog.payload.PostDto;
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

	@Override
	public PostDto createPost(PostDto postdto, Integer userid, Integer categoryid) {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourseNotFoundException("User", "Id", userid.toString()));
		Category category = categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Id", categoryid.toString()));
		Post post = new Post();
		try {
			BeanUtils.copyProperties(post, postdto);
			post.setUser(user);
			post.setCategory(category);
			postrepo.save(post);
			BeanUtils.copyProperties(postdto, post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postdto;
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postid) {
		Post post = postrepo.findById(postid)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Id", postid.toString()));
		try {
			BeanUtils.copyProperties(post, postdto);
			post.setPostid(postid);
			postrepo.save(post);
			BeanUtils.copyProperties(postdto, post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postdto;
	}

	@Override
	public void deletePost(Integer postid) {
		Post post = postrepo.findById(postid)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Id", postid.toString()));
		postrepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> findAllPost = postrepo.findAll();
		List<PostDto> listPostDto = new ArrayList<>();
		PostDto postdto = null;
		for (Post post : findAllPost) {
			postdto = new PostDto();
			try {
				BeanUtils.copyProperties(postdto, post);
				listPostDto.add(postdto);
			} catch (Exception e) {
				continue;
			}
		}
		return listPostDto;
	}

	@Override
	public PostDto getPostById(Integer postid) {
		Post post = postrepo.findById(postid)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Id", postid.toString()));
		PostDto postdto = new PostDto();
		try {
			BeanUtils.copyProperties(postdto, post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postdto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userid) {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourseNotFoundException("User", "Id", userid.toString()));
		List<PostDto> listPostDto = new ArrayList<>();
		List<Post> listPost = user.getPost();
		PostDto postdto = null;
		for (Post post : listPost) {
			postdto = new PostDto();
			try {
				BeanUtils.copyProperties(postdto, post);
				listPostDto.add(postdto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listPostDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryid) {
		Category category = categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Id", categoryid.toString()));
		List<PostDto> listPostDto = new ArrayList<>();
		List<Post> listPost = category.getPost();
		PostDto postdto = null;
		for (Post post : listPost) {
			postdto = new PostDto();
			try {
				BeanUtils.copyProperties(postdto, post);
				listPostDto.add(postdto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listPostDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> allPost = postrepo.findAll().stream().filter((post) -> post.getContent().contains(keyword) || post.getTitle().contains(keyword)).collect(Collectors.toList());
		List<PostDto> listPostDto = new ArrayList<>();
		PostDto postdto = null;
		for (Post post : allPost) {
			postdto = new PostDto();
			try {
				BeanUtils.copyProperties(postdto, post);
				listPostDto.add(postdto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listPostDto;
	}

}
