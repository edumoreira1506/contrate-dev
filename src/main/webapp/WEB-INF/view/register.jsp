<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Registro">
	<jsp:body>
    <div class="Form">
	  <h2 class="Form__title">Registrar</h2>    
   	  <form action="registrar-se" method="POST">
				<input type="text" class="Form__input" name="name" placeholder="Nome completo" required>
				<input type="text" class="Form__input" name="language" placeholder="Sua linguagem principal" required>
				<textarea name="description" class="Form__textarea" placeholder="Uma breve descrição sobre você" required></textarea>
				<input type="email" class="Form__input" name="email" placeholder="E-mail de acesso" required>
				<input type="text" class="Form__input" name="cellphone" placeholder="Telefone/Celular" required>
				<input type="text" class="Form__input" name="github" placeholder="Github" required>
				<input type="password" class="Form__input--half" name="password" placeholder="Senha de acesso" required>
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