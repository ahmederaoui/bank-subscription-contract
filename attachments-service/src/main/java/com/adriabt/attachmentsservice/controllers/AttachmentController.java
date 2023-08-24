package com.adriabt.attachmentsservice.controllers;

import com.adriabt.attachmentsservice.entities.Attachment;
import com.adriabt.attachmentsservice.enums.AttachmentStatus;
import com.adriabt.attachmentsservice.models.SignRequest;
import com.adriabt.attachmentsservice.services.IAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class AttachmentController {
    private final IAttachmentService attachmentService;

    @PostMapping("/create")
    public ResponseEntity<?> registerAttachment(@RequestBody Attachment attachment){
        try {
            return ResponseEntity.ok(attachmentService.createAttachment(attachment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateAttachment(@RequestBody Attachment attachment){
        try {
            return ResponseEntity.ok(attachmentService.updateAttachment(attachment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAttachments(@RequestParam(value = "id",defaultValue = "") String id,
                                            @RequestParam(value = "attachmentStatus",defaultValue = "") AttachmentStatus attachmentStatus,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size){
        try {
            return ResponseEntity.ok(attachmentService.getAttachments( id,  attachmentStatus, page, size));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/sign")
    public ResponseEntity<?> signAttachment(@RequestBody SignRequest signRequest){
        try {
            return ResponseEntity.ok(attachmentService.signAttachment(signRequest.getAttachmentId(), signRequest.getOtpNumber()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/terminate")
    public ResponseEntity<?> terminateAttachment(@RequestBody SignRequest signRequest){
        try {
            return ResponseEntity.ok(attachmentService.terminateAttachment(signRequest.getAttachmentId(), signRequest.getOtpNumber()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cancel/{attachmentId}")
    public ResponseEntity<?> cancelAttachment(@PathVariable String attachmentId){
        try {
            return ResponseEntity.ok(attachmentService.cancelAttachment(attachmentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search/{attachmentId}")
    public ResponseEntity<?> getAttachmentById(@PathVariable String attachmentId){
        try {
            return ResponseEntity.ok(attachmentService.getAttachmentById(attachmentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
