@template.layout.main(content = @`
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Finalizar Pedido</title>

    @if(carrinhoVazio != null && carrinhoVazio.equals("true"))
        <p style="color: red;">O carrinho está vazio.</p>
    @endif

    <style>
        /* (mesmo estilo que você já usava) */
        /* ... [mantive todo o CSS como estava] ... */
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
    <button type="button" id="buttonConfirmar" onclick="finalizarPedido()">Confirmar Pedido</button>
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

    function finalizarPedido() {
        const formaPagamento = document.getElementById('formaPagamento').value;
        if (!formaPagamento) {
            alert("Selecione a forma de pagamento.");
            return;
        }

        const valorPago = parseFloat(document.getElementById('valorPago').value) || null;

        const pedido = {
            itens: carrinho,
            formaPagamento: formaPagamento,
            valorPago: valorPago
        };

        fetch("/api/pedido/finalizar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(pedido)
        })
        .then(response => {
            if (response.ok) {
                alert("Pedido finalizado com sucesso!");
                localStorage.removeItem('carrinho');
                window.location.href = "/cardapio";
            } else {
                alert("Erro ao finalizar pedido.");
            }
        })
        .catch(error => {
            console.error("Erro:", error);
            alert("Erro ao finalizar pedido: " + error.message);
        });
    }
</script>

</body>
</html>
`)
