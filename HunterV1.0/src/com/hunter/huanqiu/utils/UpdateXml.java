package com.hunter.huanqiu.utils;

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UpdateXml {
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/** 将document中的内容写入文件中 */
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	public static Document load(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	/**
	 * 演示修改文件的具体某个节点的值
	 */
	public static void updateNode(String realPath, String[] paras) {
		Document document = load(realPath);
		Node root = document.getDocumentElement();
		boolean b=false;
		/** 如果root有子元素 */
		if (root.hasChildNodes()) {
			NodeList users = root.getChildNodes();
			/** 循环取得users所有节点 */
			for (int i = 0; i < users.getLength(); i++) {
				NodeList user = users.item(i).getChildNodes();
				if (user.item(0).getTextContent().equals(paras[0])) {
					// 如果找到了相等的节点则更新
					user.item(1).setTextContent(paras[1]);
					user.item(2).setTextContent(paras[2]);
					b=true;
					break;
				}
				else{
					b=false;
				}
			}
		}
		if(!b){
			addNode(paras, realPath,document,root);
		}
		doc2XmlFile(document, realPath);
	}

	public static void addNode(String[] paras, String realPath,Document document,Node root) {
		
		Element e = document.createElement("user");
		Element e1 = document.createElement("ip");
		e1.setTextContent(paras[0]);
		Element e2 = document.createElement("publicKey");
		e2.setTextContent(paras[1]);
		Element e3 = document.createElement("privateKey");	
		e3.setTextContent(paras[2]);
		e.appendChild(e1);
		e.appendChild(e2);
		e.appendChild(e3);
		root.appendChild(e);
	}
	public static String[] getAttributes(String ip,String realPath){
		Document document = load(realPath);
		Node root = document.getDocumentElement();
		String[] keys=new String[2];
		if (root.hasChildNodes()) {
			NodeList users = root.getChildNodes();
			if(users.getLength()!=0){
				for(int i=0;i<users.getLength();i++){
					NodeList user=users.item(i).getChildNodes();
					if(user.item(0).getTextContent().equals(ip)){
						keys[0]=user.item(1).getTextContent();
						keys[1]=user.item(2).getTextContent();
					}
				}		
			}
		}
		return keys;
	}
}
