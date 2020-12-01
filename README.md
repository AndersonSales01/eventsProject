# Events Project

## Requisitos para executar o Projeto
* Para executar o projeto, será necessário a utilização do Android Studio 4.1 ou superior.

## Architecture
Nesse projeto foi utilizado o **Clean Architecture + MVVM**, onde a junção dessas duas arquiteturas traz uma proposta bastante interessante e também trabalha bastante com os principios do SOLID. As principais vantagens é o desacoplamento, organização do código, facilidade na testabilidade e clean code, na minha visão acredito que única desvantagem é a curva de aprendizado, pois as vezes requer um tempo para que os desenvolvedores entendam os conceitos e reponsabilidade de cada camada da arquitetura.


![Arquitetura](https://github.com/AndersonSales01/eventsProject/tree/main/imgs/image_architecture.png)
![Fluxo comunicação](https://github.com/AndersonSales01/eventsProject/tree/main/imgs/comunication_flow.png)

## Tecnologias
Segue abaixo as seguintes bibliotecas e frameworks utilizadas:

- **Dagger:**

Nesse projeto utilizei a biblioteca Dagger para fazer a injeção de dependência. Eu particularmente gosto bastante da sua proposta, apesar de sua configuração ser bastante complicada, comparado com outras bibliotecas como koin e kodein, porém o dagger é bem mais robusto e vem sendo mantido e recomendado pela própria google, como a principal biblioteca para injeção de dependências.  Uma das principais vantagens do dagger é que a verificação da árvore de dependências que foi desenhada, é feito em tempo de compilação diferente do koin e kodein que são feitos em tempo de execução, com isso trazemos mais segurança para que os usuários finais não se depararem com erros ocasionados por problemas na injeção de dependências. Optando em utilizar o koin ou kodein, os testes deverão ser bem mais rigorosos e a google vem investindo bastante no Hilt que é baseado no Dagger, que tem a proposta trazer uma experiência bem menos dolorosa para os desenvolvedores e com isso resolvendo os principais problemas enfrentados com Dagger.

- **Coroutines:**

Coroutines é bastante usado para realizar processamentos assíncronos sem necessidade de consumir muitos recursos do dispositivo, com isso tendo processos bem mais leves e performáticos. As principais vantagens é a curva de aprendizado, que  é bem menor comparado com RxJava, o código é bem mais legível e fácil de entender e a principal desvantagem na minha visão é que apesar de hoje ter bastante material na comunidade sobre coroutines, existem algumas coisas que os desenvolvedores podem sentir um pouco de dificuldade de encontrar nos fóruns.

- Picasso;
- Retrofit;
- Google Maps;
- Lottie;
- Mockk;
- Expresso para testes instrumentais;
- Androidx;
- Arquitetura Componentes;
