<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Vaga">
  <jsp:body>
    <div class="Table">
      <h2 class="Table__title">CANDIDATOS</h2>
      <table>
        <tr class="Table__header">
          <td>Nome Completo</td>
          <td>Linguagem</td>
          <td>E-mail</td>
          <td>Telefone/Celular</td>
          <td>Ações</td>
        </tr>
        <c:forEach var="c" items="${job.candidates}">
          <tr class="Table__cell">
            <td>${c.name}</td>
            <td>${c.language}</td>
            <td>${c.email}</td>
            <td>${c.cellphone}</td>
            <td>
	            <a href="g/usuarios/visualizar?id=${c.email}" class="Table__action transition">
	              <i class="fas fa-trash-alt"></i>
	            </a>
            </td>
           </tr>
        </c:forEach>
      </table>
    </div>
  </jsp:body>
</t:template>
