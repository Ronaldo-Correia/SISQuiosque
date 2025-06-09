@import com.ifba.SISQuiosque.dto.CardapioItemDTO

@template.layout.main(content = @`
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cardápio do Quiosque</title>
    <style>
        .menu-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            padding: 20px;
        }
        h1 {
        text-align: center;
        color: #f2c029;
        margin-bottom: 1.5rem;
        font-size: 1.8rem;
    }
        .card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            text-align: center;
        }
        .card img {
            max-width: 200px;
            height: 150px;
            border-radius: 5px;
        }
        .cart {
            margin: 20px;
            padding: 20px;
            border: 1px solid #eee;
            border-radius: 8px;
        }
        button {
            justify-content: center;
            background-color: #BF303C;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }
        .click:hover {
            background-color: #F2C029;
        }
    </style>
</head>
<body>

<header>
    <h1>Cardápio do Quiosque</h1>
</header>

<section class="menu-container">
    <div class="card">
        <img src="/img/sucoL.png" alt="Suco">
        <h3>Suco de Laranja</h3>
        <p>Natural e gelado. R$ 6,00</p>
        <button class="click" onclick="addToCart('Suco de Laranja', 6.00)">Adicionar</button>
    </div>
    <div class="card">
        <img src="/img/pastel.png" alt="Pastel">
        <h3>Pastel de Carne</h3>
        <p>Crocante e saboroso. R$ 7,50</p>
        <button class="click" onclick="addToCart('Pastel de Carne', 7.50)">Adicionar</button>
    </div>
    <div class="card">
        <img src="/img/pratoF.png" alt="PF">
        <h3>Prato Feito</h3>
        <p>Arroz, feijão, carne e salada. R$ 15,00</p>
        <button class="click" onclick="addToCart('Prato Feito', 15.00)">Adicionar</button>
    </div>
    <div class="card">
        <img src="/img/acai.png" alt="Açaí">
        <h3>Açaí no Copo</h3>
        <p>500ml com banana e granola. R$ 10,00</p>
        <button class="click" onclick="addToCart('Açaí no Copo', 10.00)">Adicionar</button>
    </div>
</section>

<section class="cart">
    <h2>Carrinho</h2>
    <ul id="cart-list"></ul>
    <div class="total" id="total">Total: R$ 0,00</div>
    <br>
    <button class="click" onclick="finalizarPedido()">Fazer Pedido</button>

</section>

<script>
    let cart = [];

    function addToCart(nome, preco) {
        cart.push({ nome, preco });
        renderCart();
    }

    function renderCart() {
        const cartList = document.getElementById('cart-list');
        const totalDisplay = document.getElementById('total');
        cartList.innerHTML = '';
        let total = 0;
        cart.forEach(item => {
            total += item.preco;
            const li = document.createElement('li');
            li.textContent = item.nome + ' - R$ ' + item.preco.toFixed(2);
            cartList.appendChild(li);
        });
        totalDisplay.textContent = 'Total: R$ ' + total.toFixed(2);
    }

   function finalizarPedido() {
    if (cart.length === 0) {
        alert('Seu carrinho está vazio!');
        return;
    }

    localStorage.setItem('carrinho', JSON.stringify(cart));
    
    fetch('/api/carrinho/salvar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'same-origin', // IMPORTANTE! Envia os cookies de sessão
        body: JSON.stringify(cart)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/finalizar-pedido";
        } else {
            throw new Error('Erro ao salvar carrinho');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Erro ao finalizar pedido: ' + error.message);
    });
}


</script>

</body>
</html>
`)