package com.blog.test;

import com.blog.Application;
import com.blog.util.MailUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/2/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestMail {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration configuration;
    @Autowired
    private MailUtil mailUtil;

    @Test
    public void test(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("stcode98@foxmail.com");
        simpleMailMessage.setTo("345657357@qq.com");
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("一封测试邮件");
        mailSender.send(simpleMailMessage);
    }

    @Test
    public void testTemplateMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("账号激活邮件");
        helper.setFrom("stcode98@foxmail.com");
        helper.setTo("2963103258@qq.com");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("link", "https://www.baidu.com/");
        Template template = null;
        String text = null;
        try {
            template = configuration.getTemplate("mail.ftl");
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        helper.setText(text, true);
        mailSender.send(mimeMessage);
        System.out.println(text);
    }

    @Test
    public void testMailUtil() throws IOException, MessagingException, TemplateException {
        Template template = null;
        String text = null;
        template = configuration.getTemplate("mail.ftl");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("link", "https://www.baidu.com/");
        text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailUtil.buildMail("2963103258@qq.com", "测试邮件", text);
        mailUtil.senMail(true);

    }
}
