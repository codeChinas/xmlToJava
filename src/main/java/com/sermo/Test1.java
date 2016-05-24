package com.sermo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.sermo.model.Head;
import com.sermo.model.Product;
import com.sermo.model.Root;
import com.sermo.scanner.ScannerFile;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Test1 {
	public static void readXmlToJava() {
		String path = "E:/liran";
		File[] files = ScannerFile.getFileName(path);
		List<Root> lists = new ArrayList<Root>();
		for (File file : files) {
			lists.add(readXml(file));
		}
		System.out.println("MessageID = " + lists.get(0).getHead().getMessageID());
		System.out.println("CreationTime = " + lists.get(0).getHead().getCreationTime());
	}

	public static Root readXml(File file) {
		Root root = new Root();
		XStream stream = new XStream(new DomDriver());
		stream.alias("root", Root.class);
		stream.aliasAttribute(Head.class, "messageID", "MessageID");
		stream.alias("Product", Product.class);
		stream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", null, TimeZone.getTimeZone("GMT+8")));
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			stream.fromXML(fis, root);
			return root;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return root;
	}

	public static void main(String[] args) {
		readXmlToJava();
		/*
		 * File file = new
		 * File("E:/liran/statelliteproductorderorderneworder65493_WX_ORDER.xml"
		 * ); Root root = readXml(file);
		 * System.out.println(root.getHead().getMessageID()); Date date =
		 * root.getHead().getCreationTime(); SimpleDateFormat dateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * System.out.println(dateFormat.format(date));
		 */
	}
}
