<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="card-columns">
    <c:forEach var="article" items="${articles}">
        <div class="card">
            <a href="${pageContext.request.contextPath}/articles/${article.slug}" class="card-link">
                <img class="card-img-top" src="${article.image}" alt="Card image cap">
            </a>
            <div class="card-body">
              <h5 class="card-title">${article.title}</h5>
              <p class="card-text">${article.description}</p>
            </div>
        </div>
    </c:forEach>
</div>