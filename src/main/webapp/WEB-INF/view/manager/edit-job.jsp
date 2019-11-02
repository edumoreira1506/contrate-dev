<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Editar vaga">
  <jsp:body>
    <div class="Form">
      <h2 class="Form__title">Editar vaga</h2>    
      <form action="g/vagas/editar" method="POST">
        <input type="text" value="${job.name}" class="Form__input" name="name" placeholder="Nome do cargo" required>
        <textarea name="description" class="Form__textarea" placeholder="Descrição da vaga" required>${job.description}</textarea>
        <input type="text" class="Form__input" name="salary" value="${job.salary}" placeholder="Salário (no formato brasileiro. Exemplo 2000,00)" required>
        <input type="hidden" name="id" value="${job.id}">
        <button class="Form__button transition" type="submit" class="transition">
          Editar
        </button>
      </form>
    </div>
  </jsp:body>
</t:template>
