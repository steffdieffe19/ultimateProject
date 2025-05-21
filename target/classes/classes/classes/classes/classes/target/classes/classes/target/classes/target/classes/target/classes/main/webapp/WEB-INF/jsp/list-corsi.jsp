<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Elenco Corsi</title>

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
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
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

<nav class="navbar navbar-expand-lg navbar-dark position-relative "
     style="background: linear-gradient(90deg, #1f1f1f, #2a2a2a); border-radius: 10px; margin-top: 1rem;">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/">
            <div style="background-color: white; padding: 4px; border-radius: 4px;">
                <img src="https://elitesoftwarehouse.com/wp-content/uploads/2023/11/Elite_logo-full-38.svg"
                     alt="Elite Software House Logo"
                     height="40"
                     class="me-2"/>
            </div>
            <span class="text-white fw-bold navbar-brand position-absolute top-50 start-50 translate-middle">Gestione</span>
        </a>
        <button class="navbar-toggler ms-auto" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav text-end">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/docenti/lista">Docenti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/discenti/lista">Discenti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/corso">Corsi</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container my-4 content-wrapper">
    <h1 class="text-center my-4">Elenco Corsi</h1>

    <a class="btn btn-primary mb-3" href="${pageContext.request.contextPath}/corso/nuovo">Nuovo Corso</a>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Anno Accademico</th>
                <th>Docente</th>
                <th>Discenti</th>
                <th class="text-center">Azioni</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${corsi}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.nome}</td>
                    <td>${c.anno_accademico}</td>
                    <td>${c.docente.nome}</td>
                    <td>
                        <c:forEach var="discente" items="${c.discenti}">
                            <div>${discente.nome} ${discente.cognome}</div>
                        </c:forEach>
                        <c:if test="${empty c.discenti}">
                            <span class="text-muted">Nessuno</span>
                        </c:if>
                    </td>
                    <td>
                        <div class="d-flex flex-column flex-sm-row justify-content-center align-items-center gap-2">
                            <a class="btn btn-sm btn-success"
                               href="${pageContext.request.contextPath}/corso/${c.id}/edit">Modifica</a>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/corso/delete"
                                  class="d-inline">
                                <input type="hidden" name="id" value="${c.id}"/>
                                <button type="submit" class="btn btn-sm btn-danger"
                                        onclick="return confirm('Sei sicuro di voler eliminare il corso?');">
                                    Elimina
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty corsi}">
                <tr>
                    <td colspan="6" class="text-center">Nessun corso disponibile</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>