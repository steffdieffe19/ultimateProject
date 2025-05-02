<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form Docente</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<h2>${docente.id == null ? 'Nuovo Docente' : 'Modifica Docente'}</h2>

<c:choose>
    <c:when test="${docente.id == null}">
        <c:set var="formAction" value="/docenti"/>
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="/docenti/${docente.id}"/>
    </c:otherwise>
</c:choose>

<form:form method="post" modelAttribute="docente" action="${formAction}" class="needs-validation">



    <div class="mb-3">
        <form:label path="nome" class="form-label">Nome</form:label>
        <form:input path="nome" class="form-control"/>
    </div>

    <div class="mb-3">
        <form:label path="cognome" class="form-label">Cognome</form:label>
        <form:input path="cognome" class="form-control"/>
    </div>

    <div class="mb-3">
        <form:label path="data_di_nascita" class="form-label">Data di nascita</form:label>
        <form:input path="data_di_nascita" type="date" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-success">Salva</button>
    <a href="<c:url value='/docenti/lista'/>" class="btn btn-secondary">Annulla</a>

</form:form>

</body>
</html>
