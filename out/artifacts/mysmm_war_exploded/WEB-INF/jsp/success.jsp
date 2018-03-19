<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function countDown(secs,surl){
            //alert(surl);
            var jumpTo = document.getElementById('jumpTo');
            jumpTo.innerHTML=secs;
            if(--secs>0){
                setTimeout("countDown("+secs+",'"+surl+"')",1000);
            }
            else
            {
                location.href=surl;
            }
        }
    </script>
</head>
<body>
<p>操作成功!</p>
<span id="jumpTo">3</span>秒后自动跳转到查询页面
<script type="text/javascript">
    countDown(3,'${pageContext.request.contextPath }/items/queryItems.action');
</script>
</body>
</html>
