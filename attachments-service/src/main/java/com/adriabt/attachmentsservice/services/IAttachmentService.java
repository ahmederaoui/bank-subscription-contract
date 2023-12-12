package com.adriabt.attachmentsservice.services;

import com.adriabt.attachmentsservice.entities.Attachment;
import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.exceptions.AttachmentNotFound;
import org.springframework.data.domain.Page;

public interface IAttachmentService {
    Attachment createAttachment(Attachment attachment,String token);
    Attachment updateAttachment(Attachment attachment,String token) throws AttachmentNotFound;
    Page<Attachment> getAttachments(String id, AttachmentStatus attachmentStatus,int page,int size);
    Attachment signAttachment(String attachmentId,String otpNumber,String token)throws AttachmentNotFound;
    Attachment cancelAttachment(String attachmentId) throws AttachmentNotFound;
    Attachment terminateAttachment(String attachmentId,String otpNumber, String token) throws AttachmentNotFound;
    Attachment getAttachmentById(String attachmentId) throws AttachmentNotFound;
}
