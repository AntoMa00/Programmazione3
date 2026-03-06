<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
Schermata del cahethtrh

<div>
    <ul>
      <c:forEach var="carrello" items="${listaCarrello}">
        <li>
            ${carrello.nome} - ${carrello.descrizione} - ${carrello.prezzoBase}

          <!-- Select quantità per questo prodotto -->
          Quantità:
          <select onchange="vaiAllaServlet(this.value, '${carrello.codice}')" name="quantita_${carrello.codice}">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
          </select>
          <!-- Form elimina separato -->
          <form action="elimina" method="post" style="display:inline;">
            <input type="hidden" name="codice" value="${carrello.codice}">
            <button>Elimina</button>
          </form>
        </li>
      </c:forEach>
    </ul>

</div>

<%--<p>Username: ${sessionScope.username}</p>--%>
<%--<p>TotaleCarrello: ${totaleCarrello}</p>--%>

<h3>Totale da pagareeee: &euro; ${totaleCarrello}</h3>

<form action="pagamentoQuantita" method="post">
  <!-- Passa il totale al backend -->
  <input type="hidden" name="totale" value="${totaleCarrello}" />
  <input type="hidden" name="quantita" id="quantitaHidden">
  <button>Paga</button>
</form>

<form action="back" method="post">
  <input type="hidden" name="back" value="salva">
  <button>
    Freccia
  </button>
</form>

<script>
  // serve quando selezioni la quantità nel carrello di quante cose di quel prodotto vuoi acquistare,
  // poiché quel valore di serve quando schiacci Paga ma lo avevi per questioni di estetica nel div del pulsante Elimina.
  // Con JS, passi quella quantità alla funzione passaQuantita che la rispedisce a Paga
  function passaQuantita() {
    document.getElementById("quantitaHidden").value =
            document.getElementById("quantita").value;
  }
  function vaiAllaServlet(quantita, codice) {
    // reindirizza a un'altra Servlet / JSP passando i dati
    window.location.href = `pagamentoQuantita?codice=${codice}&quantita=${quantita}`;
  }
</script>

</body>
</html>
