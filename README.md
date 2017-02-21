## 基于Spring boot 开发的博客后端，使用token, refresh token机制

* #### 文件统一放在oss上,运行TestFileService时不用再做任何操作了
#### 要支持cors,请在<b>application.properties</b>中的cors项改成true

*  添加了cors支持，示例代码如下
```javascript
let url = "http://127.0.0.1:8080/blog/list?pages=1&limits=3";
fetch(url).then(function (json) {
	alert(json);
});
```

* 需要在com.blog.util.OssUtil填写accessKeyId与accessKeySecret