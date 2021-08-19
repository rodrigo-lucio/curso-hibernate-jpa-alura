
    create table Categoria (
       id  bigserial not null,
        nome varchar(255),
        primary key (id)
    )

    create table produtos (
       id  bigserial not null,
        dataCadastro date,
        descricao varchar(255),
        nome varchar(255),
        preco numeric(19, 2),
        categoria_id int8,
        primary key (id)
    )

    alter table if exists produtos 
       add constraint FKjrpviit6cxoleojljlhebkh3k 
       foreign key (categoria_id) 
       references Categoria
