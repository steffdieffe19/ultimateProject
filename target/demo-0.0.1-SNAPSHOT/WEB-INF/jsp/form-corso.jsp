<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
  <title>Form Corso</title>
    <link rel="shortcut icon" href="https://elitesoftwarehouse.com/wp-content/uploads/2023/11/Elite_favicon.svg#159" type="image/x-icon">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            background: linear-gradient(330deg, #fca311, #fb5607, #ff006e);
            background-attachment: fixed;
            background-size: cover;
        }
        .content-wrapper {
            background-color: rgba(255, 255, 255, 0.92);
            border-radius: 10px;
            padding: 2rem;
            margin: 2rem auto;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            max-width: 960px;
        }
        @media (max-width: 576px) {
            .content-wrapper {
                padding: 1rem;
            }
            .navbar-brand span {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body class="container mt-4">

<h2>${corso.id == null ? 'Nuovo Corso' : 'Modifica Corso'}</h2>

<c:choose>
    <c:when test="${corso.id == null}">
        <c:set var="formAction" value="/corso"/>
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="/corso/${corso.id}"/>
    </c:otherwise>
</c:choose>

<form:form method="post" modelAttribute="corso" action="${formAction}" class="needs-validation">

    <div class="mb-3">
        <form:label path="nome" class="form-label">Nome</form:label>
        <form:input path="nome" class="form-control"/>
    </div>

    <div class="mb-3">
        <form:label path="anno_accademico" class="form-label">Anno_accademico</form:label>
        <form:input path="anno_accademico" class="form-control"/>
    </div>

    <div class="mb-3">
       <form:label path="docente" class="form-label">Docente</form:label>
        <form:select path="docente" class="form-select">
           <c:forEach var="d" items="${docenti}">
             <option value="${d.id}" ${corso.docente != null && corso.docente.id == d.id ? 'selected' : ''}>
                  ${d.nome}
             </option>
           </c:forEach>
       </form:select>
    </div>

    <h5 class="mt-4">Discenti assegnati</h5>
    <div class="row">
        <c:forEach var="discente" items="${tuttiDiscenti}">
            <div class="col-md-6 col-lg-4 mb-2">
                <div class="form-check">
                    <input class="form-check-input"
                           type="checkbox"
                           name="discenteIds"
                           value="${discente.id}"
                           id="discente-${discente.id}"
                           <c:if test="${corso.discenti != null && corso.discenti.contains(discente)}">checked</c:if> />
                    <label class="form-check-label" for="discente-${discente.id}">
                            ${discente.nome} ${discente.cognome}
                    </label>
                </div>
            </div>
        </c:forEach>
    </div>
<button type="submit" class="btn btn-success">Salva</button>
<a href="<c:url value='/corso'/>" class="btn btn-secondary">Annulla</a>
</form:form>

</body>
</html>


