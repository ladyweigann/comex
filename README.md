# Comex - Semana 10

## Diagrama de integração entre os serviços

Planeje e faça um diagrama da integração entre os serviços:

- loja (o comex que você vem trabalhando)
- pagamentos
- notas fiscais

Quando um pedido for feito na loja, deve ser criado um pagamento e gerada a nota fiscal.

Pense em integrações síncronas e assíncronas.

Para as integrações síncronas, pense em chamadas HTTP entre os serviços.
Para as integrações assícronas, pense em tópicos, partições, chaves e consumer groups, usando JSON como formato de mensagens no Kafka.

### Requisição síncrona

![rascunho-diagrama](https://user-images.githubusercontent.com/101409570/179773869-2972bcec-a42b-4d7e-82f5-a883ba2d10f1.png)


### Requisição assíncrona, com kafka

![rascunho-kafka](https://user-images.githubusercontent.com/101409570/179774241-dbf67b15-a6b1-4b90-b7dd-f517d6fcc6b4.png)

## Entendendo as requisições com kafka

Temos os 3 principais pilares: producer, broker e consumer. O producer envia as mensagens para os chamados "topics", que são um tipo de container de mensagens. O tópico é dividido em partitions. Cada uma das mensagens dentro das partições recebe uma chave, chamada de offset. Essa chave é quem decide em qual partição a mensagem vai cair para que os chamados consumer groups possam "consumi-las".

- Cada partição é uma estrutura de dados separada que garante a ordem das mensagens. Uma partição não pode oferecer suporte a consumidores concorrentes do mesmo grupo, portanto, nosso aplicativo de fatura pode ter apenas uma instância consumindo cada partição.
- Consumers em um grupo de consumidores irão coordenar as partições e garantir que uma partição não seja consumida por mais de um consumidor do mesmo grupo. Portanto, se tivermos mais consumers que partições, os consumidores extras ficaram em reserva.

![kafka](https://user-images.githubusercontent.com/101409570/179779092-0ca18129-9416-4368-b8dd-12356b53fb64.png)






