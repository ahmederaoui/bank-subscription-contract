package com.adriabt.attachmentsservice.entities;

import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @Enumerated(value = EnumType.STRING)
    private Language language;
    private Date creationDate;
    private Date signatureDate;
    private Date updateDate;
    @Enumerated(value = EnumType.STRING)
    private AttachmentStatus attachmentStatus;
    private String subscriptionId;
    private String subscriberId;
    private List<String> signatureProfiles = new ArrayList<>();
    private String webCeilingId;
    private String mobileCeilingId;
    private List<String> accountsId = new ArrayList<>();
    private List<String> cardsId = new ArrayList<>();
}
