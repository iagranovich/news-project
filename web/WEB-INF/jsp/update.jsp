<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="entryTableOne" method="POST" action="/project1/admin/articles/add.htm" class="input-group">

  <div class="input-group-prepend">
      <c:if test="${entryTableOne.id != 0}">
          <button class="btn btn-outline-secondary" type="submit">Сохранить</button>    
      </c:if>
      <c:if test="${entryTableOne.id == 0}">
          <button class="btn btn-outline-secondary" type="submit">Добавить</button>    
      </c:if>
  </div>
    
    <input name="id" value="${entryTableOne.id}" readonly="true"  type="hidden" class="form-control">
    <input name="title" value="${entryTableOne.title}" type="text" class="form-control">
    <input name="content" value="${entryTableOne.content}" type="text" class="form-control">
    <input name="slug" value="${entryTableOne.slug}" type="text" class="form-control">

</form>