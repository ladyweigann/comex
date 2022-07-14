# Comex - Semana 9

## Chegou a hora de fazer com o que o Comex lide com pagamentos.

A diretoria financeira entrou em contato com nosso time e você precisa modelar como será a solução técnica para essa parte do sistema. Como Dev<T>, vista seu chapéu de pessoa que cuida da Arquitetura do Software! 👷️👷‍♂️️👷‍♀️️

Seu time será responsável por implementar a parte de pagamentos, lidando com boletos, cartões de créditos e Pix. Temos uma equipe de especialistas nesse tipo de sistema.

Atualmente, já há uma equipe desenvolvendo independentemente um sistema de geração de notas fiscais. Há uma subdiretoria da empresa responsável por notas fiscais e auditorias, com seus próprios especialistas de domínio.

### Perguntas: 

- **Você criará um serviço separado ou fará no seu projeto atual?**
  
  _*Neste cenário, seria mais recomendado criar um serviço separado, "estrangulando" o monolito em microsserviços. Embora existam algumas desvantagens em usar a abordagem de microsserviços, tais como: maior complexidade de desenvolvimento e infra, debug mais complexo, entre outras, as vantagens se sobressaem: projetos independentes, falha em 1 serviço é isolada, deploys menores e mais rápidos... Com isso, a aplicação do Comex (Loja, Estoque, Pagamento) pode trabalhar independente. Mesmo que um dos serviços falhe, não impedirá que os outros continuem funcionando até que a falha seja reparada.*_
  
- **O Banco de Dados será separado ou será o mesmo do seu projeto atual?**

  ![Monolito vs Microsservices](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/5b4c2b0a-9dfb-4980-a0c9-1978b2604fc5/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220714%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220714T002740Z&X-Amz-Expires=86400&X-Amz-Signature=fec40b5f8ea41499071474b0aa0ceaa69b9af8801925825cfa1a57c98879e6a2&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

  _*Conforme representando na figura, cada microsserviço terá a sua base de dados para conseguir a independência e flexibilidade em caso de falhas.*_
  
- **Você precisará de um API Gateway? Se sim, quais as responsabilidades dessa peça da sua arquitetura?**

  _*Sim. Os microsserviços precisam se conhecer e se comunicar. Para garantir um ponto único de entrada, precisamos da API Gateway. Que seria, conforme tradução literal, uma porta de entrada. O Gateway fornece um proxy, uma fachada, para as necessidades reais. Um ponto para organizar os fluxos. E precisa ser muito bem organizado para que não se torne um ponto de falha. Seus comportamentos são: Autorizar e redirecionar os requests, limitar o acesso ou o conteúdo trafegado e pode ter uso de decorator para adicionar informações necessárias aos requests.*_
  
- **O sistema de notas fiscais será um projeto separado do de pagamentos ou os times serão unidos?**

  _*Seguindo o padrão adotado, o sistema de geração de notas também será um microsserviço, usando dos mesmos princípios adotados para o microsserviço de pagamentos.*_
