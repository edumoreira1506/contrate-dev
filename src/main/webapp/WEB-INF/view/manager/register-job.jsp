<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Nova vaga">
  <jsp:body>
    <div class="Form">
      <h2 class="Form__title">Nova vaga</h2>    
      <form action="g/vagas/cadastrar" method="POST">
        <input type="text" class="Form__input" name="name" placeholder="Nome do cargo" required>
        <textarea name="description" class="Form__textarea" placeholder="Descrição da vaga" required></textarea>
        <input type="text" class="Form__input" name="salary" placeholder="Salário (no formato brasileiro. Exemplo 2000,00)" required>
        <button class="Form__button transition" type="submit" class="transition">
          Cadastrar
        </button>
      </form>
    </div>
  </jsp:body>
</t:template>
