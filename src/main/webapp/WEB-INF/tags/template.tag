<%-- 
    Document   : template
    Created on : Oct 21, 2019, 17:22:46 PM
    Author     : Eduardo Moreira
--%>

<%@tag description="Main template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>


<html>
<head>
<title>${title}</title>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="icon" type="image/png" href="favicon.png" />
<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/login.css"></link>
<link rel="stylesheet" href="resources/css/template.css"></link>

</head>
<body>
  <div class="Template">
    <div class="Template__menu">
      <div class="row">
        <div class="col-md-3">
          <h1 class="Template__menu__title">
            Contrate <span class="Template__menu__title--bold">DEV</span>
          </h1>
        </div>
        <div class="col-md-9">
          <ul class="Template__menu__links">
            <li class="Template__menu__links_link transition">
	            <a href="/contrate-dev" class="transition">Inicial</a>            
	          </li>
            <c:if test="${not empty username}">
              <c:if test="${role eq 'a'}">
	             <li class="Template__menu__links_link transition">
	               <a href="a/usuarios/listar" class="transition">Usuários</a>            
	           	 </li>
	           	 <li class="Template__menu__links_link transition">
	               <a href="a/empresas/listar" class="transition">Empresas</a>            
	           	 </li>
	           	 <li class="Template__menu__links_link transition">
	               <a href="a/empresas/cadastrar" class="transition">Nova empresa</a>            
	           	 </li>
	           	 <li class="Template__menu__links_link transition">
	               <a href="a/usuarios/cadastrar" class="transition">Novo usuário</a>            
	           	 </li>
	           </c:if>
	           <c:if test="${role eq 'g'}">
	           	 <li class="Template__menu__links_link transition">
	               <a href="g/vagas/cadastrar" class="transition">Nova vaga</a>            
	           	 </li>
	           	 <li class="Template__menu__links_link transition">
	               <a href="g/vagas/listar" class="transition">Vagas</a>            
	           	 </li>
	           </c:if>
             <c:if test="${role eq 'u'}">
             <li class="Template__menu__links_link transition">
	               <a href="u/vagas/listar" class="transition">Vagas</a>            
	           	 </li>
	           	 <li class="Template__menu__links_link transition">
	             <a href="editar-perfil" class="transition">Editar perfil</a>            
	           </li>
             </c:if>
              <li class="Template__menu__links_link transition">
	             <a href="logout" class="transition">Sair</a>            
	           </li>
		    </c:if>
          </ul>
        </div>
      </div>
    </div>
    <jsp:doBody />
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
  <script src="https://kit.fontawesome.com/a294e857dc.js" crossorigin="anonymous"></script>
  <script src="resources/js/main.js"></script>
  <c:if test="${not empty errors}">
    <c:forEach var="error" items="${errors}">
	  <script>
	    swal('Ops', '${error.message}', 'error')
	  </script>
    </c:forEach>
  </c:if>
  <c:if test="${not empty success}">
    <script>
      swal('Boa!', ${success}, 'success')
    </script>
  </c:if>
  <c:if test="${not empty message}">
    <script>
      swal('Ops', ${message}, 'error')
    </script>
  </c:if>
</body>
</html>