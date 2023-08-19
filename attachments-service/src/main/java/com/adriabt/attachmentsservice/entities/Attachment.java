package com.adriabt.attachmentsservice.entities;

import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.enums.Language;

import java.util.Date;
import java.util.List;

public class Attachment {
    private String id;
    private Language language;
    private Date creationDate;
    private AttachmentStatus attachmentStatus;
    private String subscriptionId;
    private String subscriberId;
    private List<String> signatureProfiles;
    private String webCeilingId;
    private String mobileCeilingId;
    private List<String> accountsId;
    private List<String> cardsId;
}
