INSERT INTO Cliente (cpf, email, nascimento, nome, senha, telefone)
VALUES
    ('12345678901', 'cliente1@email.com', '1990-01-15', 'Cliente Um', 'senha123', '(11) 1234-5678'),
    ('98765432109', 'cliente2@email.com', '1985-03-22', 'Cliente Dois', 'senha456', '(22) 9876-5432'),
    ('55555555555', 'cliente3@email.com', '2000-07-10', 'Cliente Três', 'senha789', '(33) 5555-5555'),
    ('77777777777', 'cliente4@email.com', '1995-12-05', 'Cliente Quatro', 'senha101', '(44) 7777-7777'),
    ('22222222222', 'cliente5@email.com', '1988-09-20', 'Cliente Cinco', 'senha202', '(55) 2222-2222');


INSERT INTO Cupom (codigo, desconto)
VALUES
    ('CUPOM01', 10.0),
    ('CUPOM02', 15.5),
    ('CUPOM03', 20.0),
    ('CUPOM04', 12.75),
    ('CUPOM05', 8.0);

INSERT INTO Endereco (bairro, cep, cidade, logradouro)
VALUES
    ('Centro', '12345-678', 'Cidade A', 'Rua Principal, 123'),
    ('Bairro 1', '98765-432', 'Cidade B', 'Avenida Secundária, 456'),
    ('Bairro 2', '54321-987', 'Cidade C', 'Travessa da Esquina, 789'),
    ('Bairro 3', '13579-246', 'Cidade D', 'Rua das Flores, 101'),
    ('Bairro 4', '87654-321', 'Cidade E', 'Avenida das Árvores, 222');

INSERT INTO Cliente_Endereco (id_cliente, id_endereco)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);

INSERT INTO Funcionario (cpf, email, nascimento, nome, senha, tipoAcesso)
VALUES
    ('123.456.789-01', 'funcionario1@email.com', '1990-01-15', 'Funcionário Um', 'senha123', 'GERENTE'),
    ('987.654.321-09', 'funcionario2@email.com', '1985-03-22', 'Funcionário Dois', 'senha456', 'SUPERVISOR'),
    ('555.555.555-55', 'funcionario3@email.com', '2000-07-10', 'Funcionário Três', 'senha789', 'ATENDENTE'),
    ('777.777.777-77', 'funcionario4@email.com', '1995-12-05', 'Funcionário Quatro', 'senha101', 'GERENTE'),
    ('222.222.222-22', 'funcionario5@email.com', '1988-09-20', 'Funcionário Cinco', 'senha202', 'ADMIN');

INSERT INTO Item (descricao, kCal, nome, preco)
VALUES ('Pizza de Calabresa', 1200, 'Pizza Calabresa', 25.50),
       ('Sprite', 180, 'Sprite', 4.50),
       ('Coca-Cola', 200, 'Coca-Cola', 5.50),
       ('Pizza de Frango', 1300, 'Pizza Frango', 23.75),
       ('Pizza de Margherita', 1100, 'Pizza Margherita', 22.00);

INSERT INTO Pizza (id, ingredientes, tamanhoPizza, tempoDePreparo)
VALUES (1, 'Calabresa, queijo, molho de tomate', 'MEDIA', 20),
       (4, 'Frango, queijo, molho de tomate', 'PEQUENA', 18),
       (5, 'Queijo, molho de tomate, manjericão', 'GRANDE', 25);

INSERT INTO Bebida (id, ml)
VALUES (2, 350),
       (3, 1000);

INSERT INTO EnderecoPedido (id_endereco, bairro, cep, cidade, logradouro)
VALUES (1, 'Centro', '12345-678', 'Cidade A', 'Rua Principal, 123'),
       (2, 'Bairro 1', '98765-432', 'Cidade B', 'Avenida Secundária, 456'),
       (3, 'Bairro 2', '54321-987', 'Cidade C', 'Travessa da Esquina, 789'),
       (4, 'Bairro 3', '13579-246', 'Cidade D', 'Rua das Flores, 101'),
       (5, 'Bairro 4', '87654-321', 'Cidade E', 'Avenida das Árvores, 222');

INSERT INTO Pedido (formaPagamento, total, id_cliente, id_cupom, id_endereco)
VALUES
    ('CARTAO', 50.00, 1, 1, 1),
    ('DINHEIRO', 35.00, 2, NULL, 2),
    ('PIX', 42.50, 3, NULL, 3),
    ('CARTAO', 60.00, 4, 2, 4),
    ('DINHEIRO', 27.50, 5, NULL, 5);

INSERT INTO StatusPedido (horario, status, id_pedido)
VALUES
    ('2023-10-01 10:16:00', 'AGUARDANDO_PAGAMENTO', 1),
    ('2023-10-01 11:30:00', 'EM_PREPARO', 1),
    ('2023-10-01 15:45:00', 'EM_ENTREGA', 1),
    ('2023-10-02 14:21:00', 'AGUARDANDO_PAGAMENTO', 2),
    ('2023-10-02 16:00:00', 'CANCELADO', 2),
    ('2023-10-03 08:31:00', 'AGUARDANDO_PAGAMENTO', 3),
    ('2023-10-03 12:45:00', 'EM_PREPARO', 3),
    ('2023-10-03 16:20:00', 'EM_ENTREGA', 3),
    ('2023-10-03 20:10:00', 'ENTREGUE', 3),
    ('2023-10-04 16:46:00', 'AGUARDANDO_PAGAMENTO', 4),
    ('2023-10-04 18:15:00', 'EM_PREPARO', 4),
    ('2023-10-04 22:30:00', 'EM_ENTREGA', 4),
    ('2023-10-05 09:40:00', 'ENTREGUE', 4),
    ('2023-10-05 12:11:00', 'AGUARDANDO_PAGAMENTO', 5),
    ('2023-10-05 14:30:00', 'CANCELADO', 5);

