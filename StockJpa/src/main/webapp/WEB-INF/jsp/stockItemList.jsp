<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KOSPI 200 주식 종목</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2 class="text-center">KOSPI 200 주식 종목</h2>
		<table class="table table-bordered table table-hover"> 
			<thead> 
				<tr> 
					<th>순위</th> 
					<th>종목명</th> 
					<th>종목순위</th>
				</tr> 
		</thead> 
		<tbody> 
			<c:forEach var="stockItem" items="${stockItemList}">
				<tr>
					<td>${stockItem.stockRank}</td>
					<td><a href="/${stockItem.stockNo}/detail">${stockItem.stockNm}</a></td>
					<td>${stockItem.stockNo}</td>
				</tr>
			</c:forEach>
		</tbody> 
	</table>
	</div>
</body>
</html>
<a href="/items/${item.id}/edit" class="btn btn-primary" role="button">수정</a>