package com.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/2/22.
 */
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender mailSender;
    private final static String from = "stcode98@foxmail.com";
    private String to;
    private String subject;
    private String text;
    private List<File> files;

    public MailUtil() {
    }

    public void buildMail(String to, String subject, String text) throws MessagingException {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public void addFiles(List<File> files){
        this.files = files;
    }

    public void senMail(boolean is_html) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, is_html);

        if (files != null){
            for (File f: files){
                helper.addAttachment(f.getName(), f);
            }
        }
        mailSender.send(mimeMessage);
    }
}
