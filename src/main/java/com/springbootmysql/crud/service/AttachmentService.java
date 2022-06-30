package com.springbootmysql.crud.service;

import org.springframework.web.multipart.MultipartFile;

import com.springbootmysql.crud.bean.ResponseBean;

public interface AttachmentService {
	public ResponseBean downloadAttachment(String id);
	public ResponseBean uploadAttachment(MultipartFile file);
	public ResponseBean getAllAttachment();
	public ResponseBean deleteAttachment(String id);
}
