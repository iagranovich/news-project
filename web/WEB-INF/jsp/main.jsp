<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach var="article" items="${articles}">
    <h2>
        <a href="/news-project/articles/${article.title}.htm"><c:out value="${article.title}"/></a><br>
    </h2>    
</c:forEach>