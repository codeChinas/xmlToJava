package com.sermo.Demo2;

import java.io.FileInputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Xpp3Parse {
	public static void main(String[] args) {
		XmlPullParserFactory factory;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			long start = System.currentTimeMillis();
			xpp.setInput(new FileInputStream("E:/liran/statelliteproductorderorderneworder65493_WX_ORDER.xml"), "utf-8");
			int eventType = xpp.getEventType();
			while (true) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					System.out.println("Start of Document");
					break;
				case XmlPullParser.START_TAG:
					System.out.println("Start of Tag: " + xpp.getName());
					if (xpp.getAttributeCount() > 0) {
						for (int i = 0; i < xpp.getAttributeCount(); i++) {
							System.out.println(xpp.getAttributeName(i) + " = " + xpp.getAttributeValue(i));
						}
					}
					break;
				case XmlPullParser.END_TAG:
					System.out.println("END_TAG: " + xpp.getName());
					break;
				case XmlPullParser.TEXT:
					System.out.println("Text: " + xpp.getText());
					break;
				}
				if (eventType == XmlPullParser.END_DOCUMENT)
					break;
				eventType = xpp.next();
			}
			System.out.println("XPP3耗时：" + (System.currentTimeMillis() - start) + " ms");
		} catch(XmlPullParserException e){
			e.printStackTrace();
		} catch(IOException e1){
			e1.printStackTrace();
		}
	}
}