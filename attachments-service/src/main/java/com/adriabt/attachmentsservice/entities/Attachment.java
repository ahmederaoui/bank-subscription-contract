package com.adriabt.attachmentsservice.entities;

import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.enums.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Language language;
    private Date creationDate;
    private Date signatureDate;
    private Date updateDate;
    private AttachmentStatus attachmentStatus;
    private String subscriptionId;
    private String subscriberId;
    private List<String> signatureProfiles;
    private String webCeilingId;
    private String mobileCeilingId;
    private List<String> accountsId;
    private List<String> cardsId;
}
