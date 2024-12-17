# APIs:

- Permite que dois componentes de software se comuniquem usando um conjunto de definições e protocolos.
- Geralmente aplicações são formadas por diversas APIs conectadas por meio de endpoints.

## Rest:

- É um conjunto de *regras de arquitetura*, não é um protocolo ou um padrão. (Apesar de atualmente ser implicito o uso
  do
  protocolo de comunicação HTTP)
- A implementação de REST pode ser feita de *diversas formas*.
- Existem *seis regras a serem cumpridas para uma api ser RESTful*

### Rest Constraints:

- **Interface uniforme:** Define a *interface entre cliente e servidores*. *Simplifica e desacopla* a arquitetura, assim
  cada parte pode *evoluir de forma independênte*. Existem *quatro principios* guia da interface uniforme:
    - **Baseada em recursos:** Recursos individuais são identificados utilizando URIs como identificadores. Os recursos
      são separados da representação que é retornada pro clienet, ou seja, não devolvemos a base de dados pra ele, e
      sim, um HTML, XML ou JSON que representa parte desse banco.
    - **Manipulação de recurso através de representações:** Quando um *cliente tem a representação de um recurso*,
      incluindo qualquer metadado anexado, ele *tem informações suficiente para modificar ou deletar o recurso no
      servidor*, caso ele tenha persmissão para isso.
    - **Mensagem auto descritivia:** *Cada mensagem tem informações suficiente para dizer como processar a mensagem*.
      Por
      exemplo qual parser é chamado depentendo do tipo de midia (antes conhecimdo como MIME type). *A resposta também
      indica explicitamente a sua capacidade de cache*.
    - **Hypermidia como o motor do estado da aplicação:** Basicamente o *cliente manda o estado por conteudo do corpo da
      requisição, paramentros na query, no cabeçalho e URI chamada*. Os *Services entregam o estado para o cliente por
      meio do corpo do conteudo, códigos de reposta e cabeçalhos de resposta*. Isso é referido técnicamente como
      Hypermedia (Hipermidia). HATEOS também significa que caso necessário um link será retornado no corpo (ou
      cabeçalho) para retornar o objeto em si ou objetos relacionados.
- **Stateless:** Basicamente o *estado necessário para lidar com a solicitação está contido dentro do próprio pedido*,
  seja como parte da URI, parametros strings na query, corpo ou cabeçalho da requisição.
    - A *URI unicamente identifica o recurso* e o *corpo contem o estado (ou a mudança do estado)* daquele recurso.
      Depois
      que o server faz o processamento, o estado apropriado, ou a parte dele que importa é comunicada de volta para o
      cliente via cabeçalho, status e corpo de resposta.
- **Cachable:** Os clientes podem armazenar em cache as resposta. Então as *responses tem que implicitamente ou
  explecitamente se definir como cachables, ou não*, para previnir cliente de reutilizarem dados obsoletos ou
  inapropriados em requisições posteriores
    - Cache bem gerenciado pode parcialmente ou completamente eliminar algumas interações clinte-servidor, melhorando
      escalabilidade e performance.
- **Cliente-server:** A interface uniforme separa clientes dos servidores. Essa separação de tarefas significa que,
  clientes não estão preocupados com o armazenamento de dados, que é interno do servidor. Servers não estão preocupados
  com a interface de usuário ou seu estado, assim o server consegu ser mais simples e mais escalavél.
    - *Servers e clientes podem ser substituidos e desenvolvidos independentemente, desde que a interface não seja
      alterada.*
- **Sistema de camadas:** Um cliente não consegue dizer normalmente se está conectado ao servidor final, ou á um
  intermediário.
    - *Servers intermediários podem melhorar a escalabalidade* de um sistema *possiblitando o balanceamento de carga ou
      cache compartilhado.* Camadas também podem reforcar politicas de segurança.
- **Código em demanda (Opcional):** Basicamente a API consegue aumentar sua funcionalidade para um cliente transferindo
  código para que ele possa executar.
    - O código pode ser usado para estender a funcionalidade do cliente.