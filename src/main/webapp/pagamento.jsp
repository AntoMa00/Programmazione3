<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pagamento</title>
</head>
<body>
Schermata dei pagamenti


<form action="tipopagamento" method="post">
    <input type="hidden" name="tipologia" value="contanti">
    <input type="hidden" name="quantita" value="${sessionScope.quantita}">
    <button>
        Contanti
    </button>
</form>

<form action="tipopagamento" method="post">
    <input type="hidden" name="tipologia" value="bancomat">
    <input type="hidden" name="quantita" value="${sessionScope.quantita}">
    <button>
        Bancomat
    </button>
</form>

<form action="tipopagamento" method="post">
    <input type="hidden" name="tipologia" value="cartadicredito">
    <input type="hidden" name="quantita" value="${sessionScope.quantita}">
    <button>
        Carta di Credito
    </button>
</form>


</body>
</html>