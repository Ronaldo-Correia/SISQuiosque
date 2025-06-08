@model List<com.ifba.SISQuiosque.entity.Produto> produtos
@model double total

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <title>Cardápio do Quiosque</title>
    <link rel="stylesheet" href="/css/cardapio.css" />
</head>
<body>

<header>
    <h1>Cardápio do Quiosque</h1>
</header>

<section class="menu-container">
    <div id="listaProdutos">
    @for(produto : produtos) {
        <div class="card">
            <img src="@{produto.imagemUrl}" alt="@{produto.nome}" />
            <h3>@{produto.nome}</h3>
            <p>@{produto.descricao} - R$ @{String.format("%.2f", produto.preco)}</p>
            <button class="add-btn" data-nome="@{produto.nome}" data-preco="@{produto.preco}">Adicionar</button>
        </div>
    }
    @endfor
    </div>
</section>

<section class="cart">
    <h2>🛒 Seu Carrinho</h2>
    <ul id="cart-list"></ul>
    <div class="total">Total: R$ @String.format("%.2f", total)</div>
    <br />
    <button onclick="finalizarPedido()">Finalizar Pedido</button>
</section>

<script>
    let cart = [];

    function addToCart(nome, preco) {
        cart.push({ nome, preco });
        renderCart();
    }

    function renderCart() {
        const cartList = document.getElementById('cart-list');
        const totalDisplay = document.querySelector('.total');
        cartList.innerHTML = '';
        let total = 0;
        cart.forEach(item => {
            total += item.preco;
            const li = document.createElement('li');
            li.textContent = `${item.nome} - R$ ${item.preco.toFixed(2)}`;
            cartList.appendChild(li);
        });
        totalDisplay.textContent = `Total: R$ ${total.toFixed(2)}`;
    }

    function finalizarPedido() {
        if (cart.length === 0) {
            alert('Seu carrinho está vazio!');
            return;
        }
        localStorage.setItem('carrinho', JSON.stringify(cart));
        window.location.href = "/cadastrar-pedido";
    }

    // Adiciona listeners aos botões de "Adicionar"
    document.querySelectorAll('.add-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            const nome = btn.getAttribute('data-nome');
            const preco = parseFloat(btn.getAttribute('data-preco'));
            addToCart(nome, preco);
        });
    });
</script>

</body>
</html>

