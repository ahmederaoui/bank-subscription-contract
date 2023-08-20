package com.adriabt.attachmentsservice.services.impl;

import com.adriabt.attachmentsservice.entities.Attachment;
import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.exceptions.AttachmentNotFound;
import com.adriabt.attachmentsservice.models.Subscription;
import com.adriabt.attachmentsservice.openFiegnServices.IContractService;
import com.adriabt.attachmentsservice.openFiegnServices.ISubscriberService;
import com.adriabt.attachmentsservice.repositories.AttachmentRepository;
import com.adriabt.attachmentsservice.services.IAttachmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@Transactional
@RequiredArgsConstructor
public class AttachmentServiceImpl implements IAttachmentService {
    private final IContractService contractService;
    private final ISubscriberService subscriberService;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Attachment createAttachment(Attachment attachment) {
        attachment.setAttachmentStatus(AttachmentStatus.REGISTERED);
        attachment.setCreationDate(new Date());
        Subscription subscription = contractService.getSubscriptionsById(attachment.getSubscriptionId());
        subscription.getSignatureProfiles().forEach(signatureProfile -> {
            attachment.getSignatureProfiles().add(signatureProfile.getId());
        });
        attachment.setMobileCeilingId(subscription.getMobileCeiling().getId());
        attachment.setWebCeilingId(subscription.getWebCeiling().getId());
        return attachmentRepository.save(attachment);
    }

    @Override
    public Page<Attachment> getAttachments(String id, AttachmentStatus attachmentStatus, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return attachmentRepository.findAllByIdIsStartingWithAndAndAttachmentStatus(id,attachmentStatus,pageable);
    }

    @Override
    public Attachment signAttachment(String attachmentId) {
        return null;
    }

    @Override
    public Attachment cancelAttachment(String attachmentId) throws AttachmentNotFound {
        Attachment attachment = getAttachmentById(attachmentId);
        attachment.setAttachmentStatus(AttachmentStatus.CANCELED);
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment terminateAttachment(String attachmentId) {
        return null;
    }

    @Override
    public Attachment getAttachmentById(String attachmentId) throws AttachmentNotFound {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(()-> new AttachmentNotFound(String.format("This attachment %s not found",attachmentId)));
    }
}
