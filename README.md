# SIS.Quiosque
#🏥 SIS.Quiosque - Trabalho Sistema distribuídos

Sistema Web de Pedidos e Pagamentos

🔧 Tecnologias Utilizadas

* Java 17/21
* Spring Boot
* JTE (Java Template Engine)
* HTML/CSS
* Banco de dados H2/PostgreSQL

📝 Funcionalidades

* 1. Autenticação e Acesso: 
Permitir login de usuários previamente cadastrados. 
Permitir o cadastro de novos usuários. 
Permitir a recuperação de senha por meio da tela de login (ex: via e-mail). 
Redirecionar o usuário autenticado diretamente para o menu principal do sistema após o login. 

* 2. Interface e Navegação 
Exibir uma home de apresentação do sistema com layout intuitivo. 
Implementar um menu principal intuitivo, com ícones e atalhos para as principais funcionalidades (adaptado para o perfil do usuário: cliente ou vendedor). 
Garantir que o sistema seja responsivo, funcionando adequadamente em diferentes tamanhos de tela (desktop, tablet e celular). 

* 3. Funcionalidades para o Cliente 
Tela de cadastro de pedido, com as seguintes opções: 
Descrição do pedido. 
Escolha da forma de pagamento: 
Dinheiro: cliente informa o valor a ser pago para cálculo de troco. 
Pix: gerar e exibir QR Code para pagamento. 
Cartão: apenas selecionar a opção. 
Tela de listagem de pedidos realizados, com possibilidade de: 
Visualizar o status de cada pedido. 
Cancelar pedidos ainda não processados. 

* 4. Funcionalidades para o Vendedor (Dono do Quiosque) 
Tela de gerenciamento de pedidos recebidos, com: 
Visualização da lista de pedidos. 
Alteração do status dos pedidos (ex: "Em preparo", "Pronto", "Entregue"). 
Confirmação da entrega e do pagamento.

* 5. Armazenamento e Dados 
Persistir todos os pedidos em banco de dados relacional, garantindo integridade e histórico de transações. 
