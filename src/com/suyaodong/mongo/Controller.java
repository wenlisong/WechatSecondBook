package com.suyaodong.mongo;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.suyaodong.entity.Book;
import net.sf.json.JSONArray;
import org.bson.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Controller {

	static private MongoClient mongoClient = new MongoClient("localhost", 27017);
	static private MongoDatabase database = mongoClient.getDatabase("mydb");
	static private MongoCollection<Document> collection = database.getCollection("user");
	static private MongoCollection CollectionImage = database.getCollection("images");

	/**
	 * 接受小程序POST请求，存储内容到数据库
	 */
	@SuppressWarnings("unchecked")
	public int upload(Book book) {
		try {
			Gson gson = new Gson();
			BasicDBObject dbObject = (BasicDBObject) JSON.parse(gson.toJson(book));
			CollectionImage.insertOne(new Document(dbObject));
			return 1;
		} catch (ClassCastException e1) {
			return 0;
		}

	}

	/**
	 * 将数据库的数据传到小程序
	 */
	@SuppressWarnings("unchecked")
	public JSONArray download() {
		FindIterable<Document> iter = CollectionImage.find();
		JSONArray JsonArray = new JSONArray();
		iter.forEach(new Block<Document>() {
			public void apply(Document doc) {
				JsonArray.add(doc.toJson());
			}
		});
		return JsonArray;
	}

	// 查找
	public Document find() {
		BasicDBObject query = new BasicDBObject("bookID", 1238910);
		Document doc = (Document) CollectionImage.find(query).first();
		return doc;
	}

	// 数据库存储对象
	static public void SaveImgBJSON(byte[] byteImg) {
		Book book = new Book();
		book.setAcademic("软件学院");
		book.setTime(System.currentTimeMillis());
		Gson gson = new Gson();
		// 转换成json字符串，再转换成DBObject对象
		BasicDBObject dbObject = (BasicDBObject) JSON.parse(gson.toJson(book));
		System.out.println("成功");
		// 插入数据库
		System.out.println(gson.toJson(book));
		CollectionImage.insertOne(new Document(dbObject));

	}

	// 图片转二进制
	static private void uploadImages() throws IOException {
		File file = new File("C:/Users/Yaodong Su/Desktop/海报/1-160516094105.jpg");
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(fis.toString().length());
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = fis.read(b)) != -1) {
			bos.write(b, 0, len);
		}
		byte[] fileByte = bos.toByteArray();

		SaveImgBJSON(fileByte);
	}

	public static void main(String[] args) throws IOException {

		// uploadImages();
		Controller controller = new Controller();

		controller.find();
		controller.download();
	}
}
