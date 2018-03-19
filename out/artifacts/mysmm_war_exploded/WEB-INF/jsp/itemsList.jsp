<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询商品列表</title>
    <script type="text/javascript">
        function deleteItems() {
            //将form的action指向删除商品的地址
            document.itemsForm.action="${pageContext.request.contextPath}/items/deleteItems.action";

            //进行form提交
            document.itemsForm.submit();

        }
    </script>
</head>
<body>
<a href="${pageContext.request.contextPath}/items/addItem.action">
    <button>添加商品</button>
</a>
<form  name = "itemsForm" action="${pageContext.request.contextPath}/items/queryItems.action" method="post">

    <td>
        <button onclick="location.href='/items/queryItems.action'"  class="btn">查询商品</button>
        <button onclick="deleteItems()"  class="btn">批量删除</button>

    </td>
    <br/>
    <br/>
    商品列表：
    <table width="100%" border=1>
        <tr>
            <td></td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>生产日期</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${itemsList }" var="item">
            <tr>
                <td><input type="checkbox" name="delete_id" value="${item.id}"> </td>
                <td>${item.name }</td>
                <td>${item.price }</td>
                <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.detail }</td>

                <td><a href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">修改</a>
                    <a href="${pageContext.request.contextPath }/items/deleteItem.action?id=${item.id}">删除</a></td>

            </tr>
        </c:forEach>

    </table>
</form>
</body>

</html>