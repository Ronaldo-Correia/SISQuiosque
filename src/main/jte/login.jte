<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="icon" type="image/x-icon" href="/favicon.ico">


</head>
<body>


    <div class="login-container">

        <form action="/login" method="post" class="form-section">


            <div class="titulo"> 
                <h1>LOGIN</h1>
                <div class="barra-horizontal"></div>
            </div>   
            
            <div class="espaco-input">
                <label for="usuario">Usuário*</label>
                <input type="text" id="usuario" name="username" placeholder="Digite seu nome de usuário" required /> 
            </div>
        
            <div class="espaco-input">
                <label for="senha">Senha*</label>
                <input type="password" id="senha" name="password" placeholder="Digite sua senha" required />
            </div>
        
            <div class="lembrar-me">
                <input type="checkbox" id="lembrar-me" name="lembrar-me"/>
                <label for="lembrar-me">Lembrar-me</label>
                <a href="/esqueci-senha" style=" text-align: right; display: block;">Esqueci minha senha</a>
            </div>
        
            <button>Entrar</button>

            <p>Não tem cadastro? <a href="/cadastro">Clique aqui!</a></p>
        
        </form>
          

        <!-- Imagem -->
        <div class="image-section">
            <div class="alinharSegundaParte">
                <div>
                <h2>Seja Bem-Vindo</h2>
                <script src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs" type="module"></script>
<dotlottie-player src="https://lottie.host/c89524af-8323-4340-9897-f09847b1d555/sH2kCgTYnV.lottie" background="transparent" speed="1" style="width: 300px; height: 300px" loop autoplay></dotlottie-player>
            </div>
        </div>
    </div>
    </div>
</body>
</html>

