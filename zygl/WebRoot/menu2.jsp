<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>维雅奇电动车留言管理系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	background-color: #CCCCCC;
}
.STYLE1 {
	color: #FFFFFF;
	font-weight: bold;
}
-->
</style></head>

<body>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="50"><div align="center">留言管理菜单</div></td>
      </tr>
      <tr>
        <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
            <tr>
              <td height="30" bgcolor="#FF6600"><div align="center" class="STYLE1">留言管理</div></td>
            </tr>
<!--             <tr> -->
<!--               <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="userlyaction.do?method=showMessages&flag=all" target="mainFrame">查看所有留言</a></div></td> -->
<!--             </tr> -->
            <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="userlyaction.do?method=showMessages&flag=mine" target="mainFrame">查看我的留言</a></div></td>
            </tr>
            <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="userlyaction.do?method=showMessages&flag=intent" target="mainFrame">查看意向组</a></div></td>
            </tr>
            <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="addly.jsp" target="mainFrame">手动添加留言</a></div></td>
            </tr>
            
			<tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="task.do?flag=today" target="mainFrame">今日任务</a></div></td>
            </tr>
			<tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="task.do?flag=tomorrow" target="mainFrame">明日任务</a></div></td>
            </tr>
            <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="task.do?flag=aftertomorrow" target="mainFrame">后天任务</a></div></td>
            </tr>
            <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="usercomplete.do?method=LookUserComplete" target="mainFrame">已做客户</a></div></td>
            </tr>
            <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="userlyaction.do?method=showMessages&flag=unvisited" target="mainFrame">未回访留言</a></div></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
            <tr>
              <td height="30" bgcolor="#FF6600"><div align="center" class="STYLE1">功能区</div></td>
            </tr>
            <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="userloginout.do?method=modifyView" target="mainFrame">修理密码</a></div></td>
            </tr>
			<tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="sousoly2.jsp" target="mainFrame">搜索留言</a></div></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
  <tr>
    <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
      <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="userloginout.do?method=logout" target="_top">安全退出</a></div></td>
      </tr>
    </table></td>
  </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
</body>
</html>