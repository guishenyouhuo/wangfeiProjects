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
    <td height="50"><div align="center">维雅奇电动车留言管理菜单</div></td>
  </tr>
  <tr>
    <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
      <tr>
        <td height="30" bgcolor="#FF6600"><div align="center" class="STYLE1">招商用户管理</div></td>
      </tr>
      <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="adduser.jsp" target="mainFrame">添加招商用户</a></div></td>
      </tr>
	  <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="showusers.do" target="mainFrame">查看招商用户</a></div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
      <tr>
        <td height="30" bgcolor="#FF6600"><div align="center" class="STYLE1">自动分配留言管理</div></td>
      </tr>
      <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="auto.do?method=showView" target="mainFrame">自动留言分配设置</a></div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
      <tr>
        <td height="30" bgcolor="#FF6600"><div align="center" class="STYLE1">留言管理</div></td>
      </tr>
      <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="showmessage.do?flag=all" target="mainFrame">查看所有留言</a></div></td>
      </tr>
	  <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="lookbyuser.do" target="mainFrame">按分配人员查看</a></div></td>
      </tr>
      <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="complete.do?flag=all" target="mainFrame">查看已做客户</a></div></td>
      </tr>
	  <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="completebyuser.do" target="mainFrame">按分配人员查看已做客户</a></div></td>
      </tr>
	  <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="admin_add.do?method=getUsers" target="mainFrame">手动添加留言</a></div></td>
      </tr>
      <tr>
              <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="admin_add.do?method=showImpotView" target="mainFrame">导入留言</a></div></td>
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
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="sousoly.jsp" target="mainFrame">搜索留言</a></div></td>
      </tr>
	  <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="modifyadmin.do?method=show" target="mainFrame">后台用户管理</a></div></td>
      </tr>
    </table></td>
  </tr>
  <!--<tr>
    <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
      <tr>
        <td height="30" bgcolor="#FF6600"><div align="center" class="STYLE1">黑名单手机号管理</div></td>
      </tr>
      <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="addheimingdan.asp" target="mainFrame">添加黑名单</a></div></td>
      </tr>
	  <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="heilist.asp" target="mainFrame">查看黑名单</a></div></td>
      </tr>
    </table></td>
  </tr>-->
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFF00">
      <tr>
        <td height="30" bgcolor="#FFFFFF"><div align="center"><a href="logout.do" target="_top">安全退出</a></div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>