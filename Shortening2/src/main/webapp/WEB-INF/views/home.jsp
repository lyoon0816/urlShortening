<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="./resources/js/jquery-1.12.1.js"></script>
<script type="text/javascript" src="./resources/js/jqCommon.js"></script>
</head>
<body>
<div>
	TargetUrl : <input type="text" id ="targetUrl" style="width:500px" value="https://en.wikipedia.org/wiki/URL_shortening ="/>
	<button id="btnChange">변환</button>
</div>
<div>
</div>
<!-- <div>
	ChangeUrl : <input type="text" id ="changeUrl" style="width:500px"/>
	<button id="btnRedirect">이동</button>
</div> -->
<div id="resultDiv">
</div>
</body>
<script type="text/javascript">
$("#btnChange").click( function() { fnChangeUrl();});
$("#btnRedirect").click( function() { fnRedirect();});

</script>
<script type="text/javascript">
function fnChangeUrl()
{
	var targetUrl = $("#targetUrl").val();
	if(!_isNullValueCheck(targetUrl))
	{
		alert("TargetUrl값을 입력하세요");
		$("#targetUrl").focus();
		return;
	}
	if(targetUrl.indexOf('http://')<0&&targetUrl.indexOf('https://')<0)
	{
		alert("TargetUrl은 http:// 혹은 https:// 로 시작해야 합니다.");
		$("#targetUrl").focus();
		return;
	}
	
	_Ajax("/changeUrl","GET","Target="+targetUrl,"text",true,'fnAddUrl');
}
function fnAddUrl(result)
{
	$("#resultDiv").children().remove();
	$("#resultDiv").append("<a id='resultUrl' href='" + result + "'>" + result + "</a>");
}
/* 
function fnAddUrl(result)
{
	$("#changeUrl").val(result);
}
function fnRedirect(url)
{
	var changeUrl = $("#changeUrl").val();
	if(!_isNullValueCheck(changeUrl))
	{
		alert("변환 후 이동 가능합니다.");
		$("#changeUrl").focus();
		return;
	}
	_Ajax("/url.do","GET","Target="+changeUrl,"text",true);
} 
*/

</script>
</html>
