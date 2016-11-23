package com.yashun.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ManXmls {
	public static Document loadDoc(String realPath)
	{
		 Document doc = null;
		  try {
			File file = new File(realPath);
		   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//步骤1
		   DocumentBuilder builder = factory.newDocumentBuilder();//步骤2
		  doc = builder.parse(file);//步骤3
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return doc;
	}
	public static List<String> getkeys(String realPath)
	{
		List<String> keys = new ArrayList<String>();
		Document doc = loadDoc(realPath);
        NodeList list = doc.getElementsByTagName("template");
        for(int i = 0;i<list.getLength();i++)
        {
        	Node node = list.item(i);
        	Node key = node.getAttributes().item(0);
        	keys.add(key.getNodeValue());
        }
		return keys;
	}
	public static String[] getParams(String realPath,String key)
	{
		Document doc = loadDoc(realPath);
        NodeList list = doc.getElementsByTagName("template");
        Node target = null;
        String[] params = new String[5];
        for(int i = 0 ;i<list.getLength();i++)
        {
        	Node node = list.item(i);
        	Node k = node.getAttributes().item(0);
        	if(k.getNodeValue().equals(key))
        	{
        		target = node;
        		break;
        	}
        }
        if(target!=null)
        {
        	if(target.hasChildNodes())
        	{
        		NodeList children = target.getChildNodes();
        		int k = 0;
        		for(int i = 0 ;i<children.getLength();i++)
        		{
        			Node n = children.item(i);
        			if(n.getNodeType()!=Node.ELEMENT_NODE)
        				continue;
        			params[k] = n.getTextContent();
        			k++;
        		}
        	}
        }
        return params;
	}
	public static void addElement(String[] params ,String realPath)
	{
		Document doc = loadDoc(realPath);
		Node root = doc.getDocumentElement();
        Element template = doc.createElement("template");
        template.setAttribute("key", params[0]);
        Element firstline = doc.createElement("firstline");
        firstline.setTextContent(params[1]);
        Element name = doc.createElement("name"); 
        name.setTextContent(params[2]);
        Element telphone = doc.createElement("telphone");
        telphone.setTextContent(params[3]);
        Element address = doc.createElement("address");
        address.setTextContent(params[4]);
        Element message = doc.createElement("message");
        message.setTextContent(params[5]);
        template.appendChild(firstline);
        template.appendChild(name);
        template.appendChild(telphone);
        template.appendChild(address);
        template.appendChild(message);
        root.appendChild(template); 
      //保存xml文件
        try {
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			Transformer transformer=transformerFactory.newTransformer();
			DOMSource domSource=new DOMSource(doc);
      //设置编码类型
			transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
			StreamResult result=new StreamResult(new FileOutputStream(realPath));
      //把DOM树转换为xml文件
			transformer.transform(domSource, result);  
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
