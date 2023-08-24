package com.adriabt.attachmentsservice.services;

import com.adriabt.attachmentsservice.entities.Attachment;
import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.exceptions.AttachmentNotFound;
import org.springframework.data.domain.Page;

public interface IAttachmentService {
    Attachment createAttachment(Attachment attachment);
    Attachment updateAttachment(Attachment attachment) throws AttachmentNotFound;
    Page<Attachment> getAttachments(String id, AttachmentStatus attachmentStatus,int page,int size);
    Attachment signAttachment(String attachmentId,String otpNumber)throws AttachmentNotFound;
    Attachment cancelAttachment(String attachmentId) throws AttachmentNotFound;
    Attachment terminateAttachment(String attachmentId,String otpNumber) throws AttachmentNotFound;
    Attachment getAttachmentById(String attachmentId) throws AttachmentNotFound;
}
