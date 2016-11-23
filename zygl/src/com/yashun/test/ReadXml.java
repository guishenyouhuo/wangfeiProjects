package com.yashun.test;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            
	            File f = new File("src/template.xml");
	            
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//步骤1
	            
	            DocumentBuilder builder = factory.newDocumentBuilder();//步骤2
	            
	            Document doc = builder.parse(f);//步骤3
	            
	            NodeList list = doc.getElementsByTagName("template");
	            for(int i = 0;i<list.getLength();i++)
	            {
	            	Node node = list.item(i);
	            	Node key = node.getAttributes().item(0);
	            	System.out.println(key.getNodeValue());
	            	
	            }
	            
	            Node root = doc.getDocumentElement();
	            Element template = doc.createElement("template");
	            template.setAttribute("key", "测试");
	            Element firstline = doc.createElement("firstline");
	            firstline.setTextContent("false");
	            Element name = doc.createElement("name"); 
	            name.setTextContent("false");
	            Element telphone = doc.createElement("telphone");
	            telphone.setTextContent("false");
	            Element address = doc.createElement("address");
	            address.setTextContent("false");
	            Element message = doc.createElement("message");
	            message.setTextContent("false");
	            template.appendChild(firstline);
	            template.appendChild(name);
	            template.appendChild(telphone);
	            template.appendChild(address);
	            template.appendChild(message);
	            root.appendChild(template); 
	          //保存xml文件
	            TransformerFactory transformerFactory=TransformerFactory.newInstance();
	            Transformer transformer=transformerFactory.newTransformer();
	            DOMSource domSource=new DOMSource(doc);

	           //设置编码类型
	            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
	            StreamResult result=new StreamResult(new FileOutputStream("src/template.xml"));

	           //把DOM树转换为xml文件
	            transformer.transform(domSource, result);  
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	        }
	}

}
