package com.suyaodong.servlet;

import com.google.gson.Gson;
import com.suyaodong.entity.Book;
import com.suyaodong.mongo.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HelloServlet() {
		super();
	}

	// 处理GET请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8"); // 解决中文乱码问题

		Controller controller = new Controller();
		response.getWriter().append(controller.download().toString());

	}

	// 处理POST请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 防止中文传过来变乱码

		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		Book book = gson.fromJson(reader, Book.class);
		long time = System.currentTimeMillis();
		book.setTime(time);
		Controller controller = new Controller();
		controller.upload(book);
		System.out.println("上传成功");
		response.getWriter().append("success");
	}

}
