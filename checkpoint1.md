# atividade-kotlin

## Descrição
O projeto é um aplicativo simples que consiste em uma tela de menu, de perfil e de pedidos, com redirecionamento e passagem de parâmetros entre telas

## Objetivo da prova
Este projeto consiste em uma atividade desenvolvida para aplicar conceitos de direcionamento de rotas e passagem de parâmetros entre elas no Kotlin.

## Evolução 1:
Na primeira melhoria do projeto, foi implementada a possibilidade de passagem de parâmetro "nome" na tela de perfil, possibilitando então que ao redirecionar para a página em questão, seja passado um parâmetro do tipo String, no caso o nome do usuário.

A implementação dessa funcionalidade se deu no arquivo MainActivity.kt, MenuScreen.kt e no PerfilScreen.kt. No MainActivity foi preparado o composable para receber um argumento, ajustado na rota, e dentro da própria função para obter o valor da rota e injetar no componente.

*MainActivity.kt*
```kt
composable(
    route = "perfil/{nome}"
) {
    val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
    PerfilScreen(
        modifier = Modifier.padding(innerPadding), 
        navController, nome!!
    )
}
```

Dessa forma, é necessário configurar o PerfilScreen para receber esse parâmetro nome, passando o tipo esperado. É importante notar que este é um parâmetro obrigatório.

*PerfilScreen.kt*
```kt
nome: String
```

Além disso, para vermos essa mudança, foi adicionado no Text a chamada desse parâmetro:

*PerfilScreen.kt*
```kt
text = "PERFIL - $nome",
```

Na tela de Menu é onde ocorre o redirecionamento do componente via clique do botão, portanto foi necessário adicionar uma "/" seguida pelo nome (Mockado por enquanto).

*MenuScreen.kt*
```kt
onClick = { navController.navigate("perfil/Matheus Amaral") }
```

## Evolução 2:
Na segunda melhoria incluímos a mesma funcionalidade de passagem de parâmetros na tela de Pedidos, mas com algumas diferenças da tela de Perfil.

A principal diferença é que dessa vez o parâmetro não é obrigatório. Isso faz com que a implementação tenha algumas diferenças. Na MainActivity usamos "?" seguido pelo nome do argumento para sinalizar um parâmetro opcional passado via query params:

*MainActivity.kt*
```kt
composable(
    route = "pedidos?cliente={cliente}",
    arguments = listOf(navArgument("cliente") {
        defaultValue = "Cliente Genérico"
})
) {
    PedidosScreen(
        modifier = Modifier.padding(innerPadding), 
        navController, 
        it.arguments?.getString("cliente")
    )
}
```

Outra diferença interessante a notar é a presença do parâmetro "arguments" no composable. Nesse caso precisamos dele para definir valores padrão para os argumentos passados que no caso podem ser nulos, além de outras configurações.

Agora, fizemos a configuração do PedidosScreen para receber esse novo argumento:

*PedidosScreen.kt*
```kt
cliente: String?
```

Note que dessa vez temos a presença do identificador "?" que indica um argumento não obrigatório.

E para visualizarmos o parâmetro funcionando, chamamos em Text:

*PedidosScreen.kt*
```kt
text = "PEDIDOS - $cliente",
```

Para finalizar a funcionalidade, implementamos a passagem de parâmetros no MenuScreen:

*MenuScreen.kt*
```kt
onClick = { navController.navigate("pedidos?cliente=Professor Carreiras") }
```

## Evolução 3:
Na última evolução do projeto por enquanto, foi adicionado mais um argumento a ser passado na tela de Perfil:

*MainActivity.kt*
```kt
composable(
    route = "perfil/{nome}/{idade}",
    arguments = listOf(
        navArgument("nome") { type = NavType.StringType },
        navArgument("idade") { type = NavType.IntType }
    )
) {
    val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
    val idade: Int? = it.arguments?.getInt("idade", 0)
    PerfilScreen(
        modifier = Modifier.padding(innerPadding),
        navController,
        nome!!,
        idade!!
    )
}
```

Interessante notar que adicionamos o argumento "arguments" para o perfil, onde configuramos também o tipo do dado a ser passado.

Como adicionamos o roteamento de um argumento novo para a tela de perfil, devemos ajustar o PerfilScreen para receber esse novo dado:

*PerfilScreen.kt*
```kt
idade: Int
```

Adicionamos um parâmetro do tipo Int obrigatório.