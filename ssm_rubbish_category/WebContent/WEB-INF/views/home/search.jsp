<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="google-site-verification" content="7bHGiWO9BqIdE29U8irujJwHqSV1g9z8dHpMDYmPtFA" />
<meta name="description" content="在线垃圾分类查询, 垃圾分类查询, 垃圾分类, 帮助你将垃圾放到正确到地方, 解决你因垃圾分类产生的烦恼">
<meta name="keywords" content="垃圾分类查询,垃圾分类,生活垃圾分类,垃圾分类">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2019, 版权所有, Ltd." />
<title>垃圾分类查询 - 垃圾分类</title>
<link rel="stylesheet" type="text/css" href="../resources/home/css/common.css" />
<link rel="stylesheet" type="text/css" href="../resources/home/css/bootstrap.min.css" />
</head>
<body style="background-color:#ecf0f1">
<script>
  function subsearch() {
	  var k = document.forms["form"]["inputv"].value;
	  if(k == '' || k == 'undefined'){
		  alert('请输入查询关键字!');
		  return false;
	  }
    window.location.href="search?k="+k;
    return false;
  }
</script>

<div class="container">


<div class="row">
  <div class="col-md-4 col-xs-2" style="padding-right: 0px">
  <img src="https://lajifenleiapp.com/favicon.png" class="img-responsive center-block" width="50px" style="margin-right: 0px;padding-top: 6px"></div>
  <div class="col-md-8 col-xs-8" style="padding-left: 0px"><h1>垃圾分类查询</h1><div style="font-size: 8px;color: red;"></div></div>
</div>
<br>

<form action="https://lajifenleiapp.com/sk" method="get" onsubmit="return subsearch()" id="form">
  <div class="col-md-1"></div>
  <div class="input-group col-md-9">
  <input name="URLz" value="${k }" class="form-control input-lg" placeholder="请输入关键字" type="text" id="inputv">
  <span class="input-group-btn"><button class="btn btn-primary input-lg" type="submit"><strong><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询</strong></button></span>
  </div>
  </form>
<br>
<c:if test="${not empty k}">
<c:if test="${empty rubbishCategory }">
<div class="row"><div class="col-md-1 col-xs-0"></div><div class="col-md-9 col-xs-12"><div class="row"><div class="col-md-12 col-xs-12" style="color:red;">未查到完全匹配字段。</div></div></div></div>
<br>
</c:if>
</c:if>
<c:if test="${not empty rubbishCategory }">	
	<div class="row">
		<div class="col-md-1 col-xs-0"></div>
		<div class="col-md-9 col-xs-12">
			<h1 style="text-align: left;">
				<span style="color:#D42121;">${k }</span>
				<span style="color:#FBbC28;">&nbsp;属于&nbsp;</span>
				<span style="#2e2a2b">${rubbishCategory.name }</span>
			</h1>
		</div>
	</div>
<br>
<div class="row">
		<div class="col-md-1 col-xs-0"></div>
		<div class="col-md-9 col-xs-12">
			<div class="row">
				<div class="col-md-12 col-xs-12"><h3 style="text-align: left;">${rubbishCategory.name }是指</h3></div>
				<div class="col-md-12 col-xs-12">${rubbishCategory.explain }</div>
			</div>
		</div>
</div>
<br>
<div class="row">
		<div class="col-md-1 col-xs-0"></div>
		<div class="col-md-9 col-xs-12">
			<div class="row">
				<div class="col-md-12 col-xs-12"><h3 style="text-align: left;">${rubbishCategory.name }主要包括</h3></div>
				<div class="col-md-12 col-xs-12">${rubbishCategory.common }</div>
			</div>
		</div>
</div>
<div class="row">
		<div class="col-md-1 col-xs-0"></div>
		<div class="col-md-9 col-xs-12">
			<div class="row">
				<div class="col-md-12 col-xs-12"><h3 style="text-align: left;">${rubbishCategory.name }投放要求</h3></div>
				<ul>
					<li>${rubbishCategory.require }</li>
				</ul>
			</div>
		</div>
	</div>
	<br>
</c:if>
<div class="row">
<div class="col-md-1 col-xs-0"></div> 
  <div class="col-md-9 col-xs-12">
    <div class="row"><span style="font-size: 20px;padding-left: 10px">上海市日常生活垃圾分为四类</span> </div>
    <br>
    <div class="row"> 
      <div class="col-md-6 col-xs-12"> 
        <div class="row"> 
          <div class="col-md-3 col-xs-3"> 
            <img style="max-width: 100px;" alt="可回收垃圾" src="https://lajifenleiapp.com/static/img/t5.png"> 
          </div> 
          <div class="col-md-9 col-xs-9" style="color: #653F34;padding-left: 22px;"> 
            <h3 style="color: #00457C;margin-top: 0px">可回收物是指</h3> 
            <p style="color: #00457C"> 废纸、废塑料、废玻璃制品、废金属、废织物等适宜回收，并且可以回收重复利用的生活废弃物。</p> 
          </div> 
        </div> 
      </div> 
      <div class="col-md-6 col-xs-12"> 
        <div class="row">
          <div class="col-md-3 col-xs-3" style="color: #653F34"> 
            <img style="max-width: 100px;" alt="有害垃圾" src="https://lajifenleiapp.com/static/img/t4.png"> 
          </div> 
          <div class="col-md-9 col-xs-9" style="padding-left: 22px;"> 
            <h3 style="margin-top: 0px;color:#FF5722">有害垃圾是指：</h3> 
            <p style="color:#FF5722">废电池、费灯泡灯管、废药品、废油漆，以及有损人身体健康或者对环境直接或者间接会造成污染，有潜在危害的生活用品。</p> 
          </div> 
        </div>
      </div> 
    </div> 


    <div class="row"> 
      <div class="col-md-6 col-xs-12"> 
        <div class="row"> 
          <div class="col-md-3 col-xs-3"> 
            <img style="max-width: 100px;" alt="湿垃圾" src="https://lajifenleiapp.com/static/img/t3.png"> 
          </div> 
          <div class="col-md-9 col-xs-9" style="color: #653F34;padding-left: 22px;"> 
            <h3 style="color: #653F34;margin-top: 0px">湿垃圾是指</h3> 
            <p>易腐垃圾，如食材废弃料、剩饭剩菜、过期食品、水果皮、干果垃圾，废弃植物、中药残渣等生活废弃物品。</p> 
            <p>如：剩菜剩饭、瓜皮果核、花卉绿植、过期食品等。</p> 
          </div> 
        </div> 
      </div> 
      <div class="col-md-6 col-xs-12"> 
        <div class="row">
          <div class="col-md-3 col-xs-3"> 
            <img style="max-width: 100px;" alt="干垃圾" src="https://lajifenleiapp.com/static/img/t2.png"> 
          </div> 
          <div class="col-md-9 col-xs-9" style="color: #2A2925;padding-left: 22px;"> 
            <h3 style="color: #2A2925;margin-top: 0px">干垃圾是指：</h3> 
            <p>就是生活中的其它垃圾，并且不属于“湿垃圾”、“有害垃圾”、“可回收垃圾”的垃圾分类，也叫其它垃圾。</p> 
          </div> 
        </div>
      </div> 
    </div> 
  </div>
</div>



<div class="row">
  <div class="col-md-1 col-cs-0"></div>
  <div class="col-md-9 col-xs-12" style="font-size: normal;color: #787878;"><p>鼓励将可回收物卖入废品回收系统，或交投至两融合服务点，或投放至可回收物收集容器；<br>严禁将有害垃圾投放到其他的生活垃圾收集容器；<br>日常家庭生活垃圾要做到干、湿垃圾两分开。</p></div>
</div>

</div>

<hr style="margin:5px">
<footer class="bs-docs-footer">

  <div class="container">
    <div class="row">
      <div class="col-md-1 col-xs-0"></div>
      <div class="col-md-9 col-xs-12" style="color:#787878">
      
      </div>
    </div>
    <div class="row">
      <div class="col-md-1 col-xs-0"></div>
      <div class="col-md-9 col-xs-12">
        <p style="color:#787878">Copyright 2018 - 2019 
        	
        	<a href="<%=request.getContextPath()%>/system/login" target="_blank">登录后台</a>
        </p>
      </div>
    </div>
  </div>
</footer>
<script>
</script>
</body>
</html>