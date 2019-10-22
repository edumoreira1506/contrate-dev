<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Contrate DEV - Login">
	<jsp:body>
    <div class="Login">
      <div class="Login__area">
        <div class="Login__area__header">
          <h1 class="Login__area__header__title">
            Contrate <span class="Login__area__header__title--bold"">DEV</span>
          </h1>
          <h3 class="Login__area__header__description">
            Arranjar um emprego nunca foi tão simples!
          </h3>
        </div>
        <form class="Login__area__form">
          <div class="Login__area__form__field">
            <input class="transition" type="email" required placeholder="e-mail">
          </div>
          <div class="Login__area__form__field">
            <input class="transition" type="password" required placeholder="senha">
          </div>
          <div class="Login__area__form__button">
            <button type="submit" class="transition">
              Login
            </button>
          </div>
        </form>
        <div class="Login__area__form__register">
          <a href="#">
            Registrar-se
          </a>
        </div>
      </div>
    </div>
  </jsp:body>
</t:template>