<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form ACTION="#" METHOD="POST" id="form1" name="form1">
  <p>&nbsp;</p>
  <p align="center">添加留言</p>
  <table width="400" border="0" align="center" cellpadding="1" cellspacing="0">
    <tr>
      <td width="62" height="25"><div align="right">客户姓名</div></td>
      <td width="334"><label>
        <input name="kh_name" type="text" id="kh_name" />
      </label></td>
    </tr>
    <tr>
      <td height="25"><div align="right">客户电话</div></td>
      <td><label>
        <input name="kh_tel" type="text" id="kh_tel"/>
      </label></td>
    </tr>
    <tr>
      <td height="25"><div align="right">客户地址</div></td>
      <td><label>
        <input name="kh_address" type="text" id="kh_address" size="40" />
      </label></td>
    </tr>
    <tr>
      <td height="25"><div align="right">客户留言</div></td>
      <td><label>
        <textarea name="kh_ly" cols="40" rows="3" id="kh_ly"></textarea>
        <input name="fp_user" type="hidden" id="fp_user" value="11" />
        <input name="intime" type="hidden" id="intime" value="11" />
      </label></td>
    </tr>
    <tr>
      <td height="54" colspan="2"><label>
        <div align="center">
          <input type="submit" name="Submit" value="提交" />
        </div>
      </label></td>
    </tr>
  </table>

    

  <input type="hidden" name="MM_insert" value="form1">
</form>
<script>
</script>
</body>
</html>