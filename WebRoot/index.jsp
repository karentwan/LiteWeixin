<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  
    <script >
    	/*var xml = '<xml>'+
		    		'<ToUserName><![CDATA[toUser]]></ToUserName>'+
		    		'<FromUserName><![CDATA[fromUser]]></FromUserName>'+
		    		'<CreateTime>1357290913</CreateTime>'+
		    		'<MsgType><![CDATA[video]]></MsgType>'+
		    		'<MediaId><![CDATA[media_id]]></MediaId>'+
		    		'<Format><![CDATA[Format]]></Format>'+
		    		'<MsgId>1234567890123456</MsgId>'+
		    		'</xml>';*/
		    		var xml =  '<xml>'+
								' <ToUserName><![CDATA[toUser]]></ToUserName>'+
					    		' <FromUserName><![CDATA[fromUser]]></FromUserName> '+
					    		' <CreateTime>1348831860</CreateTime>'+
					    		' <MsgType><![CDATA[text]]></MsgType>'+
					    		' <Content><![CDATA[I\'m content]]></Content>'+
					    		' <MsgId>1234567890123456</MsgId>'+
					    		' </xml>';
		    		$.ajax({
		    			url:"entry",
		    			type:'post',
		    			data:xml,
		    			dataType:'xml',
		    			success:function(xmlDoc) {
		    				console.log("success!" + xmlDoc);
		    			}
		    		});
    </script>
  </body>
</html>
