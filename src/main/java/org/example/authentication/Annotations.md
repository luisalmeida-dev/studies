# Autenticação:

- É o *processo de verificar a identidade de um cliente que está tentando acessar uma API*, assegurando que somente
  usuários ou aplicações autorizadas possa interagir com os recursos da API.
- Há diversas tecnicas de autenticação variando em complexidade e nivél de segurança.
- Protege os dados sensiveis, previne acesso não autorizado, permite o rastreio de uso e pode prover o controle granular
  sobre os recursos da API.

## Basic Authentication [RFC7617](https://datatracker.ietf.org/doc/html/rfc7617):

- Consiste em *transmitir as credeciais como pares de user-id/senha, codificado utilizando base64.*
- *Não é considerado um metodo seguro de autenticação*, a não ser que seja utilizado em conjunto com sistemas de
  segurança
  externos como um TLS(Transport Layer Security), já que o user-id e senha são passados pela rede como texto.
- Devemos *sempre utilizar HTTPS ao utilizar Basic Auth*, para evitar que o user-id:senha seja pega na conexão da rede.
- Funcionamento da Basic Auth: [Basic Auth](https://roadmap.sh/guides/basic-authentication.png)

## Token Authentication :

- É um protocolo que permite usuarios verificarem sua identidade, e em retorno receber um token de acesso unico **(
  access token)**.
- Durante a vida de um *token os usuarios podem acessar o site ou app para qual o token foi emitido ao invez de ter que
  reinserir as credencias* a cada vez que entram no mesmo app, site ou recurso protejido.
- *O usuário retem acesso enquanto o token é valido*. Uma vez que o usuário sai do app o token é invalidado.
- *Token oferecem uma segunda camada de segurança* e administradores tem controle detalhado sobre cada ação ou
  transação.
- Funcionametno da Token Auth: [Token Auth](https://roadmap.sh/guides/token-authentication.png)
- **Tipos de token de autenticação:**
    - **Connected:** Chaves, discos, drivres ou outros intens fisicos que conectão no sistema para o acesso.
      *Dispositovo USB ou smartcard* são um exemplo.
    - **Contactless:** Um dispositivo está perto o suficiente de um server para comunicar com ele, mas não conceta
      fisicamente. Um *celular com NFC* é um exemplo.
    - **Disconnected:** Um dispositivo pode comunicar com o servidor atraves de grandes distâncias, mesmo que não toque
      o outro dispositivo. Por exemplo a *utilização de autenticação de dois fatores.*

## JWT Authentication (Json Web Token) [RFC7519](https://datatracker.ietf.org/doc/html/rfc7519):

- É formado por um Header, Payload e Signature
- É uma representação compacta de claims(*um pedaço de informação sobre um sujeito. É a representada como um par
  chave/valor sendo elas o nome da claim e seu valor*) destinado a ambientes com espaço restringido, como o cabeçalho de
  autorização de um HTTP.
- JWT codifica as claims para serem transimitadas como objeto JSON usado como payload de uma estrutura *JSON Web
  Signature(JWS)* ou como *texto em uma JSON Web Encryption(JWE)*. Permite que as claims sejam assinadas digitalmente ou
  *protegidas integralmente com um Message Authentication Code(MAC)* ou encriptadas.
- Funcionamento da JWT Auth: [JWT Auth](https://roadmap.sh/guides/jwt-authentication.png)
- **Nomes de clamis registrados:**
    - Iss(Issuer): Basicamente identifica que gerou o JWT, geralemte é especifico de cada aplicação.
    - Sub(Subject): Identifica a parte principal que o token JWT representa, pode ser um usuário, aplicação, dispotivo
      ou qualquer outra identidade.
    - Aud(Audience): Indica para quem o token é valido, por exemplo uma api de pedidos em um e-commerce, as outra apis
      da aplicação não conseguirão utiliza o token.
    - Exp(Expiration time): Identifica o tempo no qual o token não deve ser mais valido.
    - Nbf(Not before): Identifica o data e horario a partir da qual o token se torna válido.
    - Iat(Issued At): Identifica o tempo em que o token foi gerado, ajuda a determinar a idade do token.
    - Jti(JWT ID): Identificador unico de cada token, ajuda a distinguir um token do outro mesmo que sejam gerados para
      o mesmo usuario.
        - Também ajuda a previnir que um token seja utilizado mais de uma vez.
- **Cabeçalho do Token:**
    - Indica o tipo de criptografia aplicada ao JWT e opcionalmente, propriedades adicionais do token como:
        - Typ(Type) header parameter:  É um rotulo para indicar o formato do token (*Sempre Vai ser JWT nesse caso*).
        - Cty(Content Type): Indica o tipo de conteudo presente no payload do token
        - Alg: Indica o tipo de algoritimo utilizado para criptografar o token

```json
/*
- Resultado dos octetos que representam o JWT em formato UTF-8 criptografados
*/
eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9

/*
- Conjunto de Claims para o JWT
*/
{
  "iss": "joe",
  "exp": 1300819380,
  "http://example.com/is_root": true
}

/*
- O conjunto de claims apos seus octetos serem criptografados em base65
*/
eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ

/*
- Assinatura que pega o header e payloads codificados e gera a hash criptografada utilizando SHA-256 e base64 para codificar esse SHA gerado.
*/
dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk

/*
- Concatenando as partes codificadas na ordem de Header, payload e signature separados por ponto ('.') gera o token JWT completo.
*/
eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ.dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk
```

## OAuth [RFC6749](https://datatracker.ietf.org/doc/html/rfc6749):

- É um padrão aberto de autenticaçaõ.
- Funciona via HTTPS e dispositivos autorizados. APIs, servers e aplicações com access token ao invés de credenciais.
- Existe OAuth 1.0 e OAuth 2.0, são totalmente diferente e não podem ser utilizadas em conjunto. OAuth 2.0 é mais
  utilizado atualmente
- Foi criada em resposta ao padrão de autenticação direta como o Basic Auth. Ao invés de mandar nome de usuário e senha
  em cada requisição, o usuário envia uma *API Key ID e secret*
- Basicamente *permite que uma aplicação terceira obter acesso limitado a um serviço HTTP, seja em nome de um dono de
  recurso orquestrando uma interação de aprovação entre o dono e o serviço HTTP, ou permitindo o acesso em nome da
  aplicação terceira mesmo.*
- **Define quatro papéis:**
    - **Dono do recurso:** *Entidade capaz de conceder acesso a um recurso protegido*. Quando o dono é uma pessoa é
      chamado de end-use.
    - **Server do recurso:** *O server armazenando o recurso protegido, capaz de aceitar e responder requisições usando
      um access token.*
    - **Cliente:** *Uma aplicação fazer requisições aos recursos protegidos em nome do dono do recurso e com sua
      autorização.* O termo cliente naõ indica nenhuma implementação em particular, pode ser uma aplicação em um server,
      desktop ou outro dispositivo)
    - **Server de autorização:** O server que emite access tokens para o cliente depois de ser autenticado com sucesso.
