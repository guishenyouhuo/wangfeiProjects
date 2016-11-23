package com.yashun.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.yashun.bean.MessageBean;

public class ReadExcel {

	//��315
    public static  List<MessageBean> readXls315(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell message = hssfRow.getCell(2);
                    HSSFCell address = hssfRow.getCell(3);
                    HSSFCell tel = hssfRow.getCell(4);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_name(getValue(name));
                    mb.setKh_ly(getValue(message));
                    mb.setKh_address(getValue(address));
                    mb.setKh_tel(getValue(tel).replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
    //959��  9592
    public static  List<MessageBean> readXls959(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(0);
                    HSSFCell message = hssfRow.getCell(6);
                    HSSFCell address = hssfRow.getCell(5);
                    HSSFCell tel = hssfRow.getCell(2);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_name(getValue(name));
                    mb.setKh_ly(getValue(message));
                    mb.setKh_address(getValue(address));
                    mb.setKh_tel(getValue(tel).replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
    //3158
    public static  List<MessageBean> readXls3158(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(3);
                    HSSFCell message = hssfRow.getCell(8);
                    HSSFCell address = hssfRow.getCell(6);
                    HSSFCell tel = hssfRow.getCell(5);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_name(getValue(name));
                    mb.setKh_ly(getValue(message));
                    mb.setKh_address(getValue(address));
                    mb.setKh_tel(getValue(tel).replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
    //23��  36  362  89   892  232
    public static  List<MessageBean> readXls23(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(2);
                    HSSFCell message = hssfRow.getCell(9);
                    HSSFCell address = hssfRow.getCell(5);
                    HSSFCell tel = hssfRow.getCell(4);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_name(getValue(name));
                    mb.setKh_ly(getValue(message));
                    mb.setKh_address(getValue(address));
                    String newtel = getValue(tel).replaceAll("`", "");//ȥ��
                    mb.setKh_tel(newtel.replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
    //78��
    public static  List<MessageBean> readXls78(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell message = hssfRow.getCell(8);
                    HSSFCell address = hssfRow.getCell(5);
                    HSSFCell tel = hssfRow.getCell(4);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_name(getValue(name));
                    mb.setKh_ly(getValue(message));
                    mb.setKh_address(getValue(address));
                    String newtel = getValue(tel);
                    mb.setKh_tel(newtel.replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
    
    //78��  78��2  78��  78��2
    public static  List<MessageBean> readXls78First(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell message = hssfRow.getCell(10);
                    HSSFCell address = hssfRow.getCell(7);
                    HSSFCell tel = hssfRow.getCell(4);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_name(getValue(name));
                    mb.setKh_ly(getValue(message));
                    mb.setKh_address(getValue(address));
                    String newtel = getValue(tel);
                    mb.setKh_tel(newtel.replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
    
    //2958��  29582
    public static  List<MessageBean> readXls2958(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(2);
                    HSSFCell message = hssfRow.getCell(10);
                    HSSFCell address = hssfRow.getCell(5);
                    HSSFCell tel = hssfRow.getCell(4);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_name(getValue(name));
                    mb.setKh_ly(getValue(message));
                    mb.setKh_address(getValue(address));
                    mb.setKh_tel(getValue(tel).replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
  
  //����  2958 23 78 78��  78��2  78��  78��2  36  362   89  892  29582 232
    public static  List<MessageBean> readXlsRe1(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell tel = hssfRow.getCell(4);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    String newtel = getValue(tel);
                    newtel = newtel.replaceAll("'", "");//ȥ����
                    if("0".equals(newtel.substring(0, 1)))//ȥ0
                    {
                    	newtel = newtel.substring(1);
                    }
                    mb.setKh_tel(newtel.replaceAll("[^0-9]", ""));
                    mb.setKh_name("");
                    mb.setKh_ly("");
                    mb.setKh_address("");
                    list.add(mb);
                }
            }
        }
        return list;
    }
    
  //����959  9592
    public static  List<MessageBean> readXlsRe959(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell tel = hssfRow.getCell(0);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    String newtel = getValue(tel);
                    newtel = newtel.replaceAll("'", "");//ȥ����
                    if("0".equals(newtel.substring(0, 1)))//ȥ0
                    {
                    	newtel = newtel.substring(1);
                    }
                    mb.setKh_tel(newtel.replaceAll("[^0-9]", ""));
                    mb.setKh_name("");
                    mb.setKh_ly("");
                    mb.setKh_address("");
                    list.add(mb);
                }
            }
        }
        return list;
    }
  //����3158
    public static  List<MessageBean> readXlsRe3158(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell tel = hssfRow.getCell(1);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mb.setKh_tel(getValue(tel).replaceAll("[^0-9]", ""));
                    mb.setKh_name("");
                    mb.setKh_ly("");
                    mb.setKh_address("");
                    list.add(mb);
                }
            }
        }
        return list;
    }
    
    
    //ٻ��
    public static  List<MessageBean> qianAdd(InputStream is) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell message = hssfRow.getCell(5);
                    HSSFCell address = hssfRow.getCell(3);
                    HSSFCell tel = hssfRow.getCell(2);
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    if(name!=null)
                    	mb.setKh_name(getValue(name));
                    else
                    	mb.setKh_name("");
                    if(message!=null)
                    	mb.setKh_ly(getValue(message));
                    else
                    	mb.setKh_ly("");
                    if(address!=null)
                    	mb.setKh_address(getValue(address));
                    else
                    	mb.setKh_address("");
                    mb.setKh_tel(getValue(tel).replaceAll("[^0-9]", ""));
                    list.add(mb);
                }
            }
        }
        return list;
    }
    
    //�ۺ��㷨
    public static  List<MessageBean> readXls(InputStream is,String[] params) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        MessageBean mb = null;
        List<MessageBean> list = new ArrayList<MessageBean>();
        int firstRow = 1;
        if("false".equals(params[0]))
        	firstRow = 2;
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = firstRow; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    mb = new MessageBean();
                    int telIndex = params[2].toLowerCase().charAt(0)-'a';
                    HSSFCell tel = hssfRow.getCell(telIndex);
                    
                    
                    tel.setCellType(HSSFCell.CELL_TYPE_STRING);
                    String newtel = getValue(tel);
                    newtel = newtel.replaceAll("'", "");//ȥ����
                    if("0".equals(newtel.substring(0, 1)))//ȥ0
                    {
                    	newtel = newtel.substring(1);
                    }
                    mb.setKh_tel(newtel.replaceAll("[^0-9]", ""));
                    
                    if(params[1]==null||"".equals(params[1].trim()))
                    	mb.setKh_name("");
                    else
                    {
                    	int nameIndex = params[1].toLowerCase().charAt(0)-'a';
                    	HSSFCell name = hssfRow.getCell(nameIndex);
                    	mb.setKh_name(getValue(name));
                    }
                    if(params[3]==null||"".equals(params[3].trim()))
                    	mb.setKh_address("");
                    else
                    {
                    	 int addrIndex = params[3].toLowerCase().charAt(0)-'a';
                    	 HSSFCell address = hssfRow.getCell(addrIndex);
                    	mb.setKh_address(getValue(address));
                    }
                    if(params[4]==null||"".equals(params[4].trim()))
                    	mb.setKh_ly("");
                    else
                    {
                    	 int msgIndex = params[4].toLowerCase().charAt(0)-'a';
                    	 HSSFCell message = hssfRow.getCell(msgIndex);
                    	mb.setKh_ly(getValue(message));
                    }
                    list.add(mb);
                }
            }
        }
        return list;
    }
     @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
            if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                // ���ز������͵�ֵ
                return String.valueOf(hssfCell.getBooleanCellValue());
            } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
                // ������ֵ���͵�ֵ
                return String.valueOf(hssfCell.getNumericCellValue());
            } else {
                // �����ַ������͵�ֵ
                return String.valueOf(hssfCell.getStringCellValue());
            }
        }
}