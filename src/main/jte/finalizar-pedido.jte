@template.layout.main(content = @`
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Finalizar Pedido</title>
   <style>
    body {
        font-family: Arial, sans-serif;
        padding: 1rem;
        background-color: #f9f9f9;
        margin: 0;
        min-height: 100vh;
    }

    h1 {
        text-align: center;
        color: #f2c029;
        margin-bottom: 1.5rem;
        font-size: 1.8rem;
    }

    .pedido-item {
        display: flex;
        justify-content: space-between;
        padding: 0.8rem 0;
        border-bottom: 1px solid #eee;
        font-size: 1rem;
    }

    .total {
        font-weight: bold;
        font-size: 1.3rem;
        text-align: right;
        margin-top: 1.5rem;
        padding-top: 1rem;
        border-top: 2px solid #eee;
        color: #333;
    }

    .form-group {
        margin-top: 1.5rem;
    }

    label {
        display: block;
        margin-bottom: 0.6rem;
        font-weight: bold;
        color: #555;
        font-size: 0.95rem;
    }

    input, select {
        width: 100%;
        padding: 0.9rem;
        margin-bottom: 1.2rem;
        border-radius: 8px;
        border: 1px solid #ddd;
        font-size: 1rem;
        box-sizing: border-box;
        transition: border 0.3s;
    }

    input:focus, select:focus {
        border-color: #BF303C;
        outline: none;
        box-shadow: 0 0 0 2px rgba(191, 48, 60, 0.2);
    }

    button#buttonConfirmar , button#buttonLimpar {
        width: 40%;
        padding: 1rem;
        margin: 1.5rem 0;
        border-radius: 8px;
        border: none;
        background-color: #BF303C;
        color: white;
        font-weight: bold;
        font-size: 1.1rem;
        cursor: pointer;
        transition: all 0.3s;
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 0.5rem;
        margin-left: 30%;
    }

    #buttonConfirmar:hover {
        background-color: #F2C029;
        transform: translateY(-2px);
    }
    #buttonLimpar {
        background-color: #6c757d;
    }

    #buttonLimpar:hover {
        background-color: #5a6268;
        transform: translateY(-2px);
    }
    #qrCodePix {
        display: none;
        margin: 1.5rem 0;
        text-align: center;
        padding: 1.5rem;
        background-color: #f8f9fa;
        border-radius: 10px;
    }

    #qrCodePix img {
        max-width: 100%;
        height: auto;
        margin: 0 auto;
    }

    #trocoGroup {
        display: none;
    }

    /* Media Queries para Responsividade */
    @media (max-width: 768px) {
        body {
            padding: 0.5rem;
        }

        .container {
            padding: 1rem;
            border-radius: 0;
            box-shadow: none;
        }

        h1 {
            font-size: 1.5rem;
            margin-bottom: 1rem;
        }

        .pedido-item {
            padding: 0.7rem 0;
            font-size: 0.95rem;
        }

        input, select {
            padding: 0.8rem;
        }

        button#buttonConfirmar {
            padding: 0.9rem;
            font-size: 1rem;
        }
    }

    @media (max-width: 480px) {
        h1 {
            font-size: 1.3rem;
        }

        .pedido-item {
            flex-direction: column;
            gap: 0.3rem;
        }

        .total {
            font-size: 1.2rem;
        }

        #qrCodePix {
            padding: 1rem;
        }
    }
</style>
</head>
<body>

    <h1>Finalizar Pedido</h1>

    <!-- Lista dos produtos do pedido -->
    <div id="itensPedido"></div>

    <!-- Total do pedido -->
    <div class="total">
        Total: R$ <span id="totalPedido">0.00</span>
    </div>

    <!-- Forma de pagamento -->
    <div class="form-group">
        <label for="formaPagamento">Forma de Pagamento:</label>
        <select id="formaPagamento" onchange="atualizarPagamento()">
            <option value="" disabled selected>Selecione</option>
            <option value="DINHEIRO">Dinheiro</option>
            <option value="PIX">Pix</option>
            <option value="CARTAO">Cartão</option>
        </select>
    </div>

    <!-- Dinheiro: valor pago e troco -->
    <div class="form-group" id="valorPagoGroup" style="display: none;">
        <label for="valorPago">Valor Pago (R$):</label>
        <input type="number" id="valorPago" step="0.01" placeholder="Ex: 30.00" oninput="calcularTroco()">
    </div>

    <div class="form-group" id="trocoGroup" style="display: none;">
        <label>Troco (R$):</label>
        <input type="text" id="troco" readonly>
    </div>

    <!-- QR Code Pix -->
    <div id="qrCodePix">
        <p>Escaneie o QR Code abaixo para pagar via Pix:</p>
        <img src="/img/qrPix.jpg" alt="QR Code Pix">
    </div>

    <div class="button-container">
        <button type="button" id="buttonLimpar" onclick="limparCarrinho()">Limpar Carrinho</button>
        <button type="button" id="buttonConfirmar" onclick="confirmarPedido()">Confirmar Pedido</button>
    </div>
<script>
    const carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];
    const itensPedidoDiv = document.getElementById('itensPedido');
    const totalSpan = document.getElementById('totalPedido');

    let total = 0;

    carrinho.forEach(item => {
        const itemDiv = document.createElement('div');
        itemDiv.classList.add('pedido-item');

        const nomeSpan = document.createElement('span');
        nomeSpan.textContent = item.nome;

        const precoSpan = document.createElement('span');
        precoSpan.textContent = 'R$ ' + item.preco.toFixed(2);

        itemDiv.appendChild(nomeSpan);
        itemDiv.appendChild(precoSpan);
        itensPedidoDiv.appendChild(itemDiv);

        total += item.preco;
    });

    totalSpan.textContent = total.toFixed(2);

    function atualizarPagamento() {
        const forma = document.getElementById('formaPagamento').value;
        const valorPagoGroup = document.getElementById('valorPagoGroup');
        const trocoGroup = document.getElementById('trocoGroup');
        const qrCode = document.getElementById('qrCodePix');

        valorPagoGroup.style.display = forma === 'DINHEIRO' ? 'block' : 'none';
        trocoGroup.style.display = forma === 'DINHEIRO' ? 'block' : 'none';
        qrCode.style.display = forma === 'PIX' ? 'block' : 'none';

        if (forma !== 'DINHEIRO') {
            document.getElementById('valorPago').value = '';
            document.getElementById('troco').value = '';
        }
    }
      function limparCarrinho() {
        if (confirm('Tem certeza que deseja limpar o carrinho?')) {
            localStorage.removeItem('carrinho');
            window.location.reload();
        }
    }

    function calcularTroco() {
        const valorPago = parseFloat(document.getElementById('valorPago').value);
        const troco = valorPago - total;
        document.getElementById('troco').value = troco >= 0 ? troco.toFixed(2) : '0.00';
    }

    function confirmarPedido() {
        const formaPagamento = document.getElementById('formaPagamento').value;
        if (!formaPagamento) {
            alert("Selecione a forma de pagamento.");
            return;
        }

        fetch('/api/pedidos', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        itens: carrinho,
        formaPagamento: formaPagamento,
        valorPago: parseFloat(document.getElementById('valorPago').value) || null
    })
})

        .then(response => {
            if (response.ok) {
                localStorage.removeItem('carrinho');
                alert("Pedido finalizado com sucesso!");
                window.location.href = "/cardapio";
            } else {
                throw new Error('Erro ao finalizar pedido.');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert("Erro ao finalizar pedido: " + error.message);
        });
    }
</script>

</body>
</html>
`)
