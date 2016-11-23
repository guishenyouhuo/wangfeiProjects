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

<p align="center">查看 明天 所有需要回访留言</p>

 <form id="form1" name="form1" method="post" action="jumppage3.asp">
   <p align="center"><A HREF="#">第一页</A> <A HREF="#">前一页</A> <A HREF="#">下一页</A> <A HREF="#">最后一页</A> 【共  0 条留言·当前在 0 页·共 0 页】 
     <label>
    &nbsp;&nbsp;跳转至
          <input name="offset" type="text" id="offset" size="3" maxlength="3" />
		  <input name="fp_user" type="hidden" id="offset" value="11" size="3" maxlength="3" />
          页
    </label>
    <label>
    <input type="submit" name="Submit" value="提交" />
    </label></p></form>

    <table width="800" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FF9900">
      <tr>
        <td width="40" bgcolor="#FFFF99"><div align="center">编号</div></td>
        <td width="53" height="25" bgcolor="#FFFF99"><div align="center">客户姓名</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">11</div></td>
        <td width="140" bgcolor="#FFFFFF"><div align="center">11</div></td>
        <td width="61" bgcolor="#FFFF99"><div align="center">分配人员</div></td>
        <td width="239" bgcolor="#FFFF99"><div align="center">回访情况</div></td>
        <td width="165" rowspan="3" bgcolor="#FFFFFF"><iframe src="modifyrenzs.jsp?num=11&id=11" width="165" height="80" frameborder="0" scrolling="no"></iframe></td>
      </tr>
      <tr>
        <td rowspan="2" bgcolor="#FFFFFF" width="40"><div align="center">11</div></td>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户电话</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">11</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">11</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center"><iframe src="fpren.jsp?id=11" width="50" height="14" frameborder="0" scrolling="no"></iframe></div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>11</td>
  </tr>
  <tr>
    <td><div align="right"><a href="modify_ly.jsp?id=11&amp;fp_user=11&amp;offset=11">修改回访</a></div></td>
  </tr>
</table>
</td>
      </tr>
      <tr>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户地址</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">11</div></td>
      </tr>
    </table>

	<br />

 <form id="form1" name="form1" method="post" action="jumppage3.asp">
   <p align="center"><A HREF="#">第一页</A> <A HREF="#">前一页</A> <A HREF="#">下一页</A> <A HREF="#">最后一页</A> 【共 0 条留言·当前在 0 页·共 0 页】 
     <label>
    &nbsp;&nbsp;跳转至
          <input name="offset" type="text" id="offset" size="3" maxlength="3" />
		  <input name="fp_user" type="hidden" id="offset" value="11" size="3" maxlength="3" />
		  
          页
    </label>
    <label>
    <input type="submit" name="Submit" value="提交" />
    </label></p></form>
<br />
</body>
</html>