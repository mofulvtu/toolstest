package com.tools.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 校验验证码 1.获取表单中验证码 2.获取图片上的文字
		 */
		String verifyCode = req.getParameter("verifyCode");
		String vcode = (String) req.getSession().getAttribute("vCode");
		System.out.println(verifyCode.equalsIgnoreCase(vcode));

		return null;
	}

}
