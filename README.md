# homebroker-integrated-with-db

Esse software foi feito em modelo mvc e tem como objetivo desenvolver e aplicar os ensinamentos na disciplina de Orientação à Objetos, tendo como princípio a implementação de cruds e acesso ao banco de dados. O software delimita funcionalidades de bancos e corretoras, como fazer um depósito, saque e transferência, tendo registro do historico dessas transações, e um homebroker que seria a compra e venda de ativos, priorizando a segurança da operação e o registro das atividades feitas pelo usuário

Usuario - O adm representa a bolsa, tanto o adm tanto o comum acessa sua conta da mesma forma e com as mesmas funcionalidades, já o adm tem acesso aos cruds de todas entidades de negócio do sistema e pode distribuir dividendos

Conta - Detem das funcionalidades de deposito, saque e transferencia, com um histórico para essas transações, e pode se acessar o homebroker, para compra e venda de ativos e o gerenciamento dos mesmos

Transação - Toda transação e taxa paga ou recebida pelo usuario é registrado no banco de dados, assim ele pode ter acesso ao seu histórico de transações, que seriam deposito, saque, transferencia, taxa do mes, taxa de ordem, dividendos

Ativo - O ativo será registrado primeiramente no banco de dados e poderá ser comprado apenas por ordem zero (a ordem zero não gera um pedido de ordem assim ela se funciona exclusivamente para comprar um ativo direto do banco), após o usuario possuir um ativo ele poderá criar uma ordem de venda para caso outro usuario poder compra-lo por uma ordem de compra

Ordem - Uma ordem é uma solicitação de compra/venda de um determinado ativo, para uma ordem ser executada ela precisa de outra contrária que a satisfaça, assim sendo uma ordem de compra será executada apenas com ordens de venda que tenham o preço inferior ou igual ao da ordem de compra, e uma ordem de venda é executada apenas com ordens de compra que tenham o preço superior ou igual ao da ordem de compra, portanto se no caso da ordem de compra ser mais cara que a ordem de venda, elas serão executadas com o preço da ordem de venda, o fator que determina o preço atual do ativo é o valor da ordem de venda executada
