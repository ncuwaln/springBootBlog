package com.blog.test;

import com.blog.Application;
import com.blog.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;

/**
 * Created by Administrator on 2017/2/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestFileService {
    @Autowired
    private FileService fileService;

    @Test
    public void test() throws IOException {
        File f = new File("target/classes/test.jpg");
        FileInputStream fis = null;
        fis = new FileInputStream("target/classes/test.jpg");
        byte[] bytes = new byte[(int)f.length()];

//        DataInputStream dis = null;
//        dis = new DataInputStream(fis);
        fis.read(bytes);
        System.out.println(fileService.upload(1, bytes, "jpg", "test.jpg"));
    }
}
