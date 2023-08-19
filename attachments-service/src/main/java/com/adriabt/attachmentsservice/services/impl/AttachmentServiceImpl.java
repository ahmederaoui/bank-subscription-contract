package com.adriabt.attachmentsservice.services.impl;

import com.adriabt.attachmentsservice.entities.Attachment;
import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.services.IAttachmentService;
import org.springframework.data.domain.Page;

public class AttachmentServiceImpl implements IAttachmentService {
    @Override
    public Attachment createAttachment(Attachment attachment) {
        return null;
    }

    @Override
    public Page<Attachment> getAttachments(String id, AttachmentStatus attachmentStatus, int page, int size) {
        return null;
    }

    @Override
    public Attachment signAttachment(String attachmentId) {
        return null;
    }

    @Override
    public Attachment cancelAttachment(String attachmentId) {
        return null;
    }

    @Override
    public Attachment terminateAttachment(String attachmentId) {
        return null;
    }

    @Override
    public Attachment getAttachmentById(String attachmentId) {
        return null;
    }
}
