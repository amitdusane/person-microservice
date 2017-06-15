package com.amitdusane.api.rest;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.amitdusane.domain.RestAPIErrorInfo;
import com.amitdusane.exception.Http400Exception;
import com.amitdusane.exception.Http404Exception;

public abstract class AbstractRestController implements ApplicationEventPublisherAware {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public ApplicationEventPublisher applicationEventPublisher;

	public static final String DEFAULT_PAGE_SIZE = "100";
	public static final String DEFAULT_PAGE_NO = "0";

	@Autowired
	CounterService cntService;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Http404Exception.class)
	public @ResponseBody RestAPIErrorInfo handleResourceNotFound(Http404Exception ex, WebRequest request,
			HttpServletResponse resp) {

		log.info("Converting Data store exceptions to rest response " + ex.getMessage());
		cntService.increment("com.amitdusane.http.404.count");
		return new RestAPIErrorInfo(ex, "Sorry we couldn't find the resource!");

	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Http400Exception.class)
	public @ResponseBody RestAPIErrorInfo handleResourceNotFound(Http400Exception ex, WebRequest request,
			HttpServletResponse resp) {

		log.info("Converting Data store exceptions to rest response " + ex.getMessage());
		cntService.increment("com.amitdusane.http.400.count");
		return new RestAPIErrorInfo(ex, "You messed up!");

	}

	public static <T> T checkResourceFound(T resource) {

		if (resource == null)
			throw new Http404Exception("Resource not found");

		return resource;
	}

}
