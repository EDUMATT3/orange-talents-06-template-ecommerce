insert into categoria(id, nome, categoria_mae_id) values
(null, 'categoria 1', null),
(null, 'subcategoria 1', 1);

--senha = senha
insert into usuario(id, login, senha) values
(null, 'usuario1@email.com', '$2a$10$/.sUBBdqMAphwKaPz.dmCel1RrDt01Lt5zYIUWRSsxJCRPu5u5XhK'),
(null, 'usuario2@email.com', '$2a$10$/.sUBBdqMAphwKaPz.dmCel1RrDt01Lt5zYIUWRSsxJCRPu5u5XhK');

insert into produto(id, nome, valor, quantidade, descricao, categoria_id, usuario_id) values
(null, 'prod 1', 10.3, 3, 'desc prod 1', 1, 1),
(null, 'prod 2', 29, 4, 'desc prod 2', 2, 2);

insert into caracteristica_produto(id, descricao, nome, produto_id) values
(null, 'desc carac 1', 'nome carac 1', 1),
(null, 'desc carac 2', 'nome carac 2', 1),
(null, 'desc carac 3', 'nome carac 3', 1),
(null, 'desc carac 4', 'nome carac 4', 2),
(null, 'desc carac 5', 'nome carac 5', 2),
(null, 'desc carac 6', 'nome carac 6', 2);

insert into imagem_produto(id, link, produto_id) values
(null, 'link imagem 1', 1),
(null, 'link imagem 2', 1),
(null, 'link imagem 3', 2);

insert into opiniao(id, nota, titulo, descricao, produto_id, usuario_id) values
(null, 3, 'titulo opiniao 1', 'desc opiniao 1', 1, 1),
(null, 1, 'titulo opiniao 2', 'desc opiniao 1', 1, 2),
(null, 5, 'titulo opiniao 3', 'desc opiniao 1', 1, 2);

insert into pergunta(id, titulo, produto_id, interessado_id, created_at) values
(null, 'titulo pergunta 1', 1, 1, now()),
(null, 'titulo pergunta 2', 2, 2, now()),
(null, 'titulo pergunta 3', 1, 1, now());