<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form Discente</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<h2>${discente.id == null ? 'Nuovo Discente' : 'Modifica Discente'}</h2>

<c:choose>
    <c:when test="${discnte.id == null}">
        <c:set var="formAction" value="/discenti"/>
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="/discenti/${discente.id}"/>
    </c:otherwise>
</c:choose>

<form:form method="post" modelAttribute="discente" action="${formAction}" class="needs-validation">



    <div class="mb-3">
        <form:label path="nome" class="form-label">Nome</form:label>
        <form:input path="nome" class="form-control"/>
    </div>

    <div class="mb-3">
        <form:label path="cognome" class="form-label">Cognome</form:label>
        <form:input path="cognome" class="form-control"/>
    </div>

    <div class="mb-3">
        <form:label path="citta_di_residenza" class="form-label">Citta_di_residenza</form:label>
        <form:input path="citta_di_residenza"  class="form-control"/>
    </div>

    <div class="mb-3">
        <form:label path="matricola" class="form-label">Matricola</form:label>
        <form:input path="matricola" class="form-control"/>
    </div>

    <div class="mb-3">
        <form:label path="eta" class="form-label">Eta</form:label>
        <form:input path="eta" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-success">Salva</button>
    <a href="<c:url value='/discenti/lista'/>" class="btn btn-secondary">Annulla</a>

</form:form>

</body>
</html>
