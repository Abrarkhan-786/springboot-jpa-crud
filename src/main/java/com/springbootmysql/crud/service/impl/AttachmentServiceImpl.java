package com.springbootmysql.crud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.constant.Status;
import com.springbootmysql.crud.model.Attachment;
import com.springbootmysql.crud.repository.AttachmentDao;
import com.springbootmysql.crud.service.AttachmentService;


@Service
@Qualifier("attachmentServiceImpl")
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;
	

	public AttachmentServiceImpl(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	@Override
	public ResponseBean uploadAttachment(MultipartFile file) {
		try {
			if (file == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Data Is Null").build();
			}
			
			long fileSize=file.getSize()/1024/1024;
			if(fileSize>10) {
				return ResponseBean.builder().status(Status.FAIL).message("File Size Too large should be less than 10 mb").build();
			}
			
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
			if (fileName.contains("..")) {
				return ResponseBean.builder().status(Status.FAIL).message("Sorry! your file contails invalid characters...").build();

			}
			Attachment attachment=new Attachment();
			attachment.setFileName(fileName);
			attachment.setFileType(file.getContentType());
			attachment.setData(file.getBytes());
					
			Attachment attachmentBean = attachmentDao.save(attachment);
			
			String downloadURL=ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/attachment")
					.path("/downloadAttachment/")
					.path(attachmentBean.getId())
					.toUriString();
			attachmentBean.setDownloadURL(downloadURL);
			attachmentBean.setData(null);
			return ResponseBean.builder().status(Status.SUCCESS).message("file uploaded Succesfully")
					.response(attachmentBean).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean downloadAttachment(String id) {
		try {
			if (id.trim()== null) {
				return ResponseBean.builder().status(Status.FAIL).message("please Provide id").build();
			}
			Optional<Attachment> findById = attachmentDao.findById(id);
			if (findById == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			return ResponseBean.builder().status(Status.SUCCESS).response(findById.get()).build();

		} catch (Exception e) {
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

   @Override
	public ResponseBean getAllAttachment() {
		try {
			
			 List<Attachment> findAll = attachmentDao.findAll();
			if (findAll == null || findAll.size()<1) {
				return ResponseBean.builder().status(Status.FAIL).message("Records Not found").build();
			}

			return ResponseBean.builder().status(Status.SUCCESS).response(findAll).build();

		} catch (Exception e) {
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

@Override
public ResponseBean deleteAttachment(String id) {
	try {
		if (id.trim()== null) {
			return ResponseBean.builder().status(Status.FAIL).message("please Provide id").build();
		}
		Optional<Attachment> findById = attachmentDao.findById(id);
		if (findById == null) {
			return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
		}
		 attachmentDao.deleteById(id);
		return ResponseBean.builder().status(Status.SUCCESS).message("Deleted Successfully").build();

	} catch (Exception e) {
		return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
	}

}


	

	
	
}
