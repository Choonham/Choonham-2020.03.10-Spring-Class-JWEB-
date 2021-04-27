<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 정보 수정</title>
</head>

<body>
	<h4>상품 정보를 수정한 후 수정 버튼을 누르십시오.</h4>
        <form action="GoodsInfoUpdate.jsp" method="get">
            코드: <input type="text" name="code" size="5" value="${CODE }" readonly="readonly" /> <br />
            제목: <input type="text" name=title size=50 value="${TITLE }" /> <br />
            저자: <input type="text" name=writer size=20 value="${WRITER }" /> <br />
            가격: <input type="text" name=price size=8 value="${PRICE }" />원 <br />
            <input type="submit" value="수정" />
        </form>
</body>
</html>