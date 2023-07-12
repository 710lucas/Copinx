<img src="https://raw.githubusercontent.com/Luxs710/Copinx/main/src/main/resources/public/Logo.svg?raw=true">

Copinx é uma ideia de uma rede social baseada no [letterboxd](https://letterboxd.com), onde são feitas avaliações de águas.

Este repositório é apenas uma implementação básica dessa ideia, buscando treinar meus conhecimentos em java, Spring, html, css e javascript.

A versão disponível no momento apresenta várias funções que ainda precisam ser implementadas de acordo com o formato do site, porém já funcionam e podem ser utilizadas como uma API Rest

<img src="https://github.com/Luxs710/Copinx/blob/dev/imagens/login.png?raw=true">
<img src="https://github.com/Luxs710/Copinx/blob/dev/imagens/home.png?raw=true">
<img src="https://github.com/Luxs710/Copinx/blob/dev/imagens/review.png?raw=true">

## Caracteristicas da API Copinx

A API apresenta duas classes principais, a classe Água e a classe Review, apresentando também a classe usuário.

#### Classe Água:

A classe Água armazena as informações relacionadas à uma água, como um filme do letterboxd armazena as informações relacionadas àquele filme, sendo essas características: seu nome, sua descrição e reviews associadas à Água.

Sendo assim, podemos observar que a Água possui um repositório de reviews, com o qual é feita uma comunicação, permitindo o calculo de sua nota media e a organização das reviews de acordo com a nota.

#### Classe Review:

A classe review possui informações que dizem respeito à uma review especifica de uma água, guardando informações como: a descrição da review, o usuário que fez a review, a água que está sendo avaliada e a nota que foi dada à esta água.

Desse modo, podemos ter um repositório de reviews gerais, podendo organiza-las de inúmeras maneiras de acordo com estas informações presentes nelas.

#### Classe Usuário

A classe usuário permite com que usuários sejam criados, nos quais serão armazenadas informações referentes às avaliações feitas e usuarios que são seguidos e que seguem um determinado usuário.

Novamente, essas informações nos permitem organizar melhor os dados do serviço de avaliação, no qual podemos, por exemplo, gerar uma média das avaliações do usuário, podendo até comparar esta média com a média de seus amigos, ou podemos também comparar esta média com seus amigos, mostrando avaliações que possuem caracteristicas em comuns.

------

Vale ressaltar que este projeto foi feito apenas como uma maneira de demonstrar esta ideia de site e não deve ser usado, da maneira que se encontra neste repositório, como algo a ser levado à serio.

