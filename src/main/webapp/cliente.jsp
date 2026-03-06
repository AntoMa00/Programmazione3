<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Cliente</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/index.css">
</head>


<body>

<div class="gerardo">

<%--  <form action="preferiti.html">--%>
<%--    <button>--%>
<%--      Preferiti--%>
<%--    </button>--%>
<%--  </form>--%>

  <form action="viewcarrello">
    <button>
      Carrello
    </button>
  </form>

<%--  <form action="login.html">--%>
<%--    <button>--%>
<%--      it.bripobe.Login fatto--%>
<%--    </button>--%>
<%--  </form>--%>

<%--  <form action="login.html">--%>
<%--    <button>--%>
<%--      Logout--%>
<%--    </button>--%>
<%--  </form>--%>


</div>

<div class="dropdown">
  <button class="dropbtn">Menu</button>
  <div class="dropdown-content">
    <form action="salva" method="get">
        <button>Tutti</button>
    </form>
    <form action="specifico" method="post">
      <input type="hidden" name="categoria" value="Vestiti">
      <button>Vestiti</button>
    </form>
    <form action="specifico" method="post">
      <input type="hidden" name="categoria" value="Orologi">
      <button>Orologi</button>
    </form>
    <form action="specifico" method="post">
      <input type="hidden" name="categoria" value="diari">
      <button>Diari</button>
    </form>
  </div>
</div>

<div>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <ul>
    <div>

      <ul>
        <c:forEach var="prodotto" items="${listaProdotti}">
          <li>
              ${prodotto.nome} - ${prodotto.descrizione} - ${prodotto.quantita} -
              ${prodotto.prezzoBase} - ${prodotto.categoria} - ${prodotto.sconto} -
              ${prodotto.prezzoScontato}
              <form action="selezionaprodotto" method="post">
                <input type="hidden" name="codice" value="${prodotto.codice}">
                <input type="hidden" name="nome" value="${prodotto.nome}">
                <input type="hidden" name="descrizione" value="${prodotto.descrizione}">
                <input type="hidden" name="quantita" value="${prodotto.quantita}">
                <input type="hidden" name="prezzoBase" value="${prodotto.prezzoBase}">
                <input type="hidden" name="categoria" value="${prodotto.categoria}">
                <input type="hidden" name="sconto" value="${prodotto.sconto}">
                <input type="hidden" name="prezzoScontato" value="${prodotto.prezzoScontato}">
                <button>Seleziona Prodotto</button>
              </form>

          </li>
        </c:forEach>
      </ul>
    </div>

  </ul>
</div>

<%--<form action="back" method="post">--%>
<%--  <input type="hidden" name="back" value="login.html">--%>
<%--  <button>--%>
<%--    Freccia--%>
<%--  </button>--%>
<%--</form>--%>

</body>
</html>
