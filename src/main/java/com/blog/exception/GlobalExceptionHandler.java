package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ApiResponse> resourseNotFoundException(ResourseNotFoundException ex){
		ApiResponse apiResponse=new ApiResponse(ex.getMessage(),false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
