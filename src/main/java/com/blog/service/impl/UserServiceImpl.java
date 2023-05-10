package com.blog.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exception.ResourseNotFoundException;
import com.blog.payload.UserDto;
import com.blog.repo.UserRepo;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto createUser(UserDto userdto){
		User user = new User();
		try {
			BeanUtils.copyProperties(user, userdto);
			user=userrepo.save(user);
			BeanUtils.copyProperties(userdto, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userdto;
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userid) {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourseNotFoundException("User", "Id", userid.toString()));
		user.setAbout(userdto.getAbout());
		user.setEmail(userdto.getEmail());
		user.setName(userdto.getName());
		user.setPassword(userdto.getPassword());
		userrepo.save(user);
		userdto = modelmapper.map(user, UserDto.class);
		/*try {
			BeanUtils.copyProperties(user, userdto);
			user.setUserid(userid);
			User updatedUser = userrepo.save(user);
			System.out.println(updatedUser);
			BeanUtils.copyProperties(userdto, updatedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return userdto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> listuser = userrepo.findAll();
		List<UserDto> listuserdto = new ArrayList<>();
		// UserDto userdto=null;
		listuser.stream().forEach((user) -> {
			UserDto userdto = new UserDto();
			userdto.setUserid(user.getUserid());
			userdto.setName(user.getName());
			userdto.setEmail(user.getEmail());
			userdto.setAbout(user.getAbout());
			userdto.setPassword(user.getPassword());
			listuserdto.add(userdto);
		});
		return listuserdto;
	}

	@Override
	public UserDto getUserById(Integer userid) {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourseNotFoundException("User", "Id", userid.toString()));
		UserDto userdto = new UserDto();
		try {
			BeanUtils.copyProperties(userdto, user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return userdto;
	}

	@Override
	public void deleteUser(Integer userid) {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourseNotFoundException("User", "Id", userid.toString()));
		userrepo.delete(user);
	}

}
