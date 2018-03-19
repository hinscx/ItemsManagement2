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

        <c:forEach items="${pb.itemsList }" var="item">
            <tr>
                <td><input type="checkbox" name="delete_id" value="${item.id}"> </td>
                <td>${item.name }</td>
                <td>${item.price }</td>
                <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.detail }</td>

                <td><a href="${pageContext.request.contextPath}/items/editItems.action?id=${item.id}">修改</a>
                    <a href="${pageContext.request.contextPath}/items/deleteItem.action?id=${item.id}">删除</a></td>

            </tr>
        </c:forEach>

        <center>
            第${pb.pc}页/共${pb.tp}页
            <a href="${pb.url}pc=1">首页</a>
            <c:if test="${pb.pc>1}">
                <a href="${pb.url}pc=${pb.pc-1}">上一页</a>
            </c:if>

            <c:choose>
                <c:when test="${pb.tp<=10}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${pb.tp}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${pb.pc-5}"/>
                    <c:set var="end" value="${pb.pc+4}"/>
                    <%--头溢出--%>
                    <c:if test="${begin<1}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="10"/>
                    </c:if>
                    <%--尾溢出--%>
                    <c:if test="${end>pb.tp}">
                        <c:set var="end" value="${pb.tp}"/>
                        <c:set var="begin" value="${pb.tp-9}"/>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <%--循环遍历页码列表--%>
            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:choose>
                    <c:when test="${i eq pb.pc}">
                        [${i}]
                    </c:when>
                    <c:otherwise>
                        <a href="${pb.url}pc=${i}">[${i}]</a>
                    </c:otherwise>
                </c:choose>

            </c:forEach>


            <c:if test="${pb.pc<pb.tp}">
                <a href="${pb.url}pc=${pb.pc+1}">下一页</a>
            </c:if>
            <a href="${pb.url}pc=${pb.tp}">尾页</a>

        </center>

    </table>
</form>
</body>

</html>