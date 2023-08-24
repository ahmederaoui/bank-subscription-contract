package com.adriabt.attachmentsservice.services.impl;

import com.adriabt.attachmentsservice.entities.Attachment;
import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.exceptions.AttachmentNotFound;
import com.adriabt.attachmentsservice.models.ContractAttachmentDTO;
import com.adriabt.attachmentsservice.models.OtpValidationRequest;
import com.adriabt.attachmentsservice.models.Subscription;
import com.adriabt.attachmentsservice.openFiegnServices.IContractService;
import com.adriabt.attachmentsservice.openFiegnServices.ISmsService;
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
    private  final ISmsService smsService;

    @Override
    public Attachment createAttachment(Attachment attachment) {
        attachment.setAttachmentStatus(AttachmentStatus.REGISTERED);
        attachment.setCreationDate(new Date());
        Subscription subscription = contractService.getSubscriptionsById(attachment.getSubscriptionId());
        subscription.getSignatureProfiles()
                .forEach(signatureProfile -> attachment.getSignatureProfiles().add(signatureProfile.getId()));
        attachment.setMobileCeilingId(subscription.getMobileCeiling().getId());
        attachment.setWebCeilingId(subscription.getWebCeiling().getId());
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment updateAttachment(Attachment attachment) throws AttachmentNotFound {
        Attachment updateAttachment = getAttachmentById(attachment.getId());
        if(attachment.getLanguage()!=null)updateAttachment.setLanguage(attachment.getLanguage());
        if(attachment.getAccountsId()!=null)updateAttachment.setAccountsId(attachment.getAccountsId());
        if(attachment.getCardsId()!=null)updateAttachment.setCardsId(attachment.getCardsId());
        if(attachment.getSubscriberId()!=null)updateAttachment.setSubscriberId(attachment.getSubscriberId());
        if(attachment.getSubscriptionId()!=null) {
            Subscription subscription = contractService.getSubscriptionsById(attachment.getSubscriptionId());
            subscription.getSignatureProfiles()
                    .forEach(signatureProfile -> attachment.getSignatureProfiles().add(signatureProfile.getId()));
            updateAttachment.setMobileCeilingId(subscription.getMobileCeiling().getId());
            updateAttachment.setWebCeilingId(subscription.getWebCeiling().getId());
        }
        updateAttachment.setUpdateDate(new Date());
        return attachmentRepository.save(attachment);
    }

    @Override
    public Page<Attachment> getAttachments(String id, AttachmentStatus attachmentStatus, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return attachmentRepository.findAllByIdIsStartingWithAndAndAttachmentStatus(id,attachmentStatus,pageable);
    }

    @Override
    public Attachment signAttachment(String attachmentId,String otpNumber) throws AttachmentNotFound {
        Attachment attachment = getAttachmentById(attachmentId);
        OtpValidationRequest otpValidationRequest = new OtpValidationRequest(attachment.getSubscriberId(),otpNumber);
        boolean isOtpValid = smsService.validateOtp(otpValidationRequest);
        System.out.println(isOtpValid);
        if (isOtpValid){
            ContractAttachmentDTO contractAttachmentDTO = new ContractAttachmentDTO(attachment.getSubscriptionId(),
                    attachment.getSubscriberId(),attachment.getAccountsId(),attachment.getCardsId());
            contractService.attachContract(contractAttachmentDTO);
            subscriberService.attachSubscriber(attachmentId,attachment.getSubscriberId());
            attachment.setAttachmentStatus(AttachmentStatus.SIGNED);
            attachment.setSignatureDate(new Date());
            return attachmentRepository.save(attachment);
        }
        return attachment;
    }

    @Override
    public Attachment cancelAttachment(String attachmentId) throws AttachmentNotFound {
        Attachment attachment = getAttachmentById(attachmentId);
        attachment.setAttachmentStatus(AttachmentStatus.CANCELED);
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment terminateAttachment(String attachmentId,String otpNumber) throws AttachmentNotFound {
        Attachment attachment = getAttachmentById(attachmentId);
        OtpValidationRequest otpValidationRequest = new OtpValidationRequest(attachmentId,otpNumber);
        boolean isOtpValid = smsService.validateOtp(otpValidationRequest);
        if (isOtpValid){
            ContractAttachmentDTO contractAttachmentDTO = new ContractAttachmentDTO(attachment.getSubscriptionId(),
                    attachment.getSubscriberId(),attachment.getAccountsId(),attachment.getCardsId());
            contractService.attachContract(contractAttachmentDTO);
            subscriberService.attachSubscriber(attachmentId,attachment.getSubscriberId());
            attachment.setAttachmentStatus(AttachmentStatus.TERMINATED);
            attachment.setUpdateDate(new Date());
            return attachmentRepository.save(attachment);
        }
        return attachment;
    }

    @Override
    public Attachment getAttachmentById(String attachmentId) throws AttachmentNotFound {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(()-> new AttachmentNotFound(String.format("This attachment %s not found",attachmentId)));
    }
}
