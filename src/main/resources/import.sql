INSERT INTO Cliente (cpf, email, nascimento, nome, senha, telefone)
VALUES
    ('12345678901', 'cliente1@email.com', '1990-01-15', 'Cliente Um', 'SZFFPNIAlM/v9x99vgGIwCNQ77utvSHj2M0zEU+4FqyyLdjz4pJXq8f3h3MrwHqrECoQa6PjBzLS6JIH90ahzA==', '(11) 1234-5678'),
    ('98765432109', 'cliente2@email.com', '1985-03-22', 'Cliente Dois', '7Z8JpAPdZ0m1IbUAme9skIRXWZfVqtr9NxsBAC60mswvwJeWoSs1D7PPVFMWEjijqwc/UunkXIWew4Q6JLyUlQ==', '(22) 9876-5432'),
    ('55555555555', 'cliente3@email.com', '2000-07-10', 'Cliente Três', 'a2NoHy3mrmxKOny0aDsaOUzSRPq63yqb4oiDtW7jdqnb06UMyqfMAAMKAjUB2CZsGITmePlbCJVHmGx3R5Xffw==', '(33) 5555-5555'),
    ('77777777777', 'cliente4@email.com', '1995-12-05', 'Cliente Quatro', 'cJiaaOsVab6pkwBAB3fqdqb0owoAO7emOXlNy7sLC0DhZCWOC36WaOlIBMHZzpJ8dUwskIXOHRIfSBPYD4ccSA==', '(44) 7777-7777'),
    ('22222222222', 'cliente5@email.com', '1988-09-20', 'Cliente Cinco', 'GdwAYxZUVBmGS6IliQzPjRFyB5I+V+0qMeEQQQqP+Fgj0/N8XUTZcK81K3aZvvkzkUo2lYY+XCBHG1V4HoUzNQ==', '(55) 2222-2222');


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
    ('123.456.789-01', 'funcionario1@email.com', '1990-01-15', 'Funcionário Um', 'pa1V0iDad2Ng0n8nZCS57MN8JSclhUZEibB0nBqYifc+eBvubU1QmeXi6+0wqVKwxnlfo7YTusgLEUV+5Cisaw==', 'GERENTE'),
    ('987.654.321-09', 'funcionario2@email.com', '1985-03-22', 'Funcionário Dois', 'nkhKu5cT14ipd3SlevMxXdooTXdVgcBk7S6RiFPf4bDdLuATtx3LrFV+IKnxv2GTbPJ4A7f0bhDJIwj9PaZ4/A==', 'SUPERVISOR'),
    ('555.555.555-55', 'funcionario3@email.com', '2000-07-10', 'Funcionário Três', '05kmKRH3cltqSZp0G8y/6L2X+5d9n55cuOkG4SUW23e61kGhmdOXnnub/qKJBvcKyxxAF5gfcik+or1svRkxzA==', 'ATENDENTE'),
    ('777.777.777-77', 'funcionario4@email.com', '1995-12-05', 'Funcionário Quatro', 'rfXQvShKmWvZoaZQkyySBlZgN3tNvsd19Q2BT+YuXmt7Fv2tw1XDg2DR1wsSoehhOnzeRA134d+uuymV1f/IAw==', 'GERENTE'),
    ('222.222.222-22', 'funcionario5@email.com', '1988-09-20', 'Funcionário Cinco', 'smHl1KSe31Cv5ACzrpQzXqEWy1PnHFDFlZ8IrR4k231tNNQJRfg57Gt67iX1HFzi7kkP5Tiy+z/nMXNAvJ5GSA==', 'ADMIN');

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

INSERT INTO Pedido (formaPagamento, id_cliente, id_cupom, id_endereco)
VALUES
    ('CARTAO', 1, 1, 1),
    ('DINHEIRO', 2, NULL, 2),
    ('PIX', 3, NULL, 3),
    ('CARTAO', 4, 2, 4),
    ('DINHEIRO', 5, NULL, 5);


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

INSERT INTO ItemPedido (preco, quant, id_item, id_pedido, tamanho)
VALUES (25.50, 2, 1, 1, 'MEDIA'),
       (25.50, 1, 2, 2, '350ML'),
       (25.50, 1, 3, 2, '1000ML'),
       (25.50, 1, 4, 2, 'PEQUENA'),
       (25.50, 4, 2, 3, '350ML'),
       (25.50, 5, 3, 4, '1000ML'),
       (25.50, 9, 4, 5, 'PEQUENA');