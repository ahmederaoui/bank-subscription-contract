package com.adriabt.mailsenderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    MultipartFile[] file;
    String to;
    String[] cc;
    String subject;
    String body;
}
