package com.blog.controller;

import com.blog.error.UserDefinedException;
import com.blog.service.FileService;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/17.
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private FileService fileService;

    private static ArrayList<String> acceptFileType = new ArrayList<String>();

    /**
     *
     * @param file 上传的文件
     * @param token token cookie的token
     * @return {
     *     "url": 文件的相对于根目录的url
     * }
     * @throws UserDefinedException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map upload(@RequestParam("file") MultipartFile file, @CookieValue("token")String token) throws UserDefinedException, IOException, NoSuchAlgorithmException {
        if (!file.isEmpty()){
            throw new UserDefinedException("文件不能为空", 400);
        }
        String filename = file.getName();
        String type = filename.split(".")[1];
        if (!acceptFileType.contains(type)){
            throw new UserDefinedException("不允许的文件类型", 403);
        }
        Claims claims = JwtUtil.parseJWT(token);
        Map m = (Map)JsonUtil.json2object(claims.getSubject(), Map.class);
        Integer user_id = (Integer) m.get("user_id");
        String url = fileService.upload(user_id, file.getBytes(), type, filename);
        Map result = new HashMap();
        result.put("url", url);
        return result;
    }
}
