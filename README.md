# homebroker-integrated-with-db

Esse software foi feito em modelo mvc e tem como objetivo desenvolver e aplicar os ensinamentos na disciplina de Orientação à Objetos, tendo como princípio a implementação de cruds e acesso ao banco de dados. O software delimita funcionalidades de bancos e corretoras, como fazer um depósito, saque e transferência, tendo registro do historico dessas transações, assim sendo na parte da bolsa seria a compra e venda de ativos, priorizando a segurança da compra/venda e o registro das atividades feitas pelo usuário. Existem dois tipos de usuários, o adm e o comum, ambos tem acesso semelhante a sua conta, sendo que todas as contas possuem as mesmas funcionalidades, e portanto o adm consegue gerenciar os cruds de todas entidades, a partir de controladores que comunicam com o bancobanco.

Usuario - O adm representa a bolsa, tanto o adm tanto o comum acessa sua conta da mesma forma

Conta - Detem das funcionalidades de deposito, saque e transferencia, com um histórico para essas transações, e pode se acessar o homebroker, para compra e venda de ativos e também o gerenciamento dos ativos possuidos.
