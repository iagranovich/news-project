<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sf:form modelAttribute="article" method="POST">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <button class="btn btn-outline-secondary" type="submit">Добавить</button>
        </div>
        <sf:input path="title" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1"/>
        <sf:input path="content" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1"/>
        <sf:input path="slug" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1"/>
    </div>
</sf:form>
