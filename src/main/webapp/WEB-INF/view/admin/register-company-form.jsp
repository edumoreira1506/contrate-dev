<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Nova empresa">
  <jsp:body>
    <div class="Form">
	  <h2 class="Form__title">Nova empresa</h2>    
   	  <form action="a/empresas/cadastrar" method="POST">
   	    <h3 class="Form__subtitle">Dados da empresa</h3>
				<input type="text" class="Form__input" name="company_name" placeholder="Nome da empresa" required>
				<textarea name="description" class="Form__textarea" placeholder="Descrição da empresa" required></textarea>
   	    <h3 class="Form__subtitle">Dados do gerente da empresa</h3>
				<input type="text" class="Form__input" name="name" placeholder="Nome do gerente da empresa" required>
				<input type="email" class="Form__input" name="email" placeholder="E-mail de acesso do gerente" required>
				<input type="password" class="Form__input--half" name="password" placeholder="Senha de acesso do gerente" required>
				<input type="password" class="Form__input--half" name="confirm_password" placeholder="Confirmação de senha" required>
				<select name="gender" required class="Form__select">
					<option>Selecionar...</option>
					<option value="M">Homem</option>
					<option value="F">Mulher</option>
    		</select>
    		<button class="Form__button transition" type="submit" class="transition">
        	Cadastrar
        </button>
      </form>
    </div>
  </jsp:body>
</t:template>
