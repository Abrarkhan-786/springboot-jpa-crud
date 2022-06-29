package com.springbootmysql.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootmysql.crud.model.Attachment;

@Repository
public interface AttachmentDao extends JpaRepository<Attachment, String> {

	
	
}
