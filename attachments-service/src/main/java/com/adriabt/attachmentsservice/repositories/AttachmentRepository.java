package com.adriabt.attachmentsservice.repositories;

import com.adriabt.attachmentsservice.entities.Attachment;
import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,String> {
    Page<Attachment> findAllByIdIsStartingWithAndAndAttachmentStatus(String id, AttachmentStatus attachmentStatus, Pageable pageable);
}
