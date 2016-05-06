<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
        function change(){
        	//1.获取<img>元素
        	//2.给src指向为/tools/VerifyCodeServlet
        	//3.需要给出一个参数去掉缓存
        	var img = document.getElementById("imgVerifyCode");
        	img.src = "/tools/VerifyCodeServlet?a=" + new Date();
        }
         

</script>
</head>
<body>
       <%--
         1.写表单，其中包含图片（验证码）
         2.显示图片：
           把<img>的src指向VerifyCodeServlet，需要在web.xml中部署VerfiyCodeServlet
         3.换一张
       --%>
       
       <form action="" method="post">
           用户名：<input type="text" name="name"/><br/><br/>
           密&nbsp;&nbsp;&nbsp;码：<input type="password" name="pwd"/><br/><br/>
           验证码：<input type="text" name="verifyCode"/><br/><br/>
           <img alt="" src="/tools/VerifyCodeServlet" id="imgVerifyCode"/>
           <a href="javascript:change()">换一张</a>
           <input type="submit" value="Submit"/>
       </form>
       

</body>
</html>