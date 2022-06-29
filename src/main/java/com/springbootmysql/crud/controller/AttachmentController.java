package com.springbootmysql.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.model.Attachment;
import com.springbootmysql.crud.service.AttachmentService;

@CrossOrigin(allowCredentials="false")
@RestController
@RequestMapping(value="/attachment")
public class AttachmentController {
	
	@Autowired
	@Qualifier("attachmentServiceImpl")
	AttachmentService attachmentService;
	
	@RequestMapping(value="uploadAttachment" , method= RequestMethod.POST)
	@ResponseBody
	public ResponseBean uploadAttachment(@RequestParam("file") MultipartFile file) {
		return attachmentService.uploadAttachment(file);
	}
	
	@RequestMapping(value="/downloadAttachment/{ID}" ,method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> downloadAttachment(@PathVariable("ID") String id) {
		Attachment attachment= (Attachment)attachmentService.downloadAttachment(id).getResponse();
		
				return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
	}

}
