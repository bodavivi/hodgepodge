DROP TABLE if exists available_merchant_game;
DROP TABLE if exists available_mixture_game;
DROP TABLE if exists unavailable_merchant_game;
DROP TABLE if exists unavailable_mixture_game;
DROP TABLE if exists mixture_alchemist;
DROP TABLE if exists hand_merchant_alchemist;
DROP TABLE if exists played_merchant_alchemist;

DROP TABLE if exists alchemist;
DROP TABLE if exists game;
DROP TABLE if exists merchant;
DROP TABLE if exists mixture;

CREATE TABLE alchemist (
    id               bigint auto_increment primary key,
    gold_coins       int    not null,
    ingr_chicken_leg int    not null,
    ingr_feather     int    not null,
    ingr_mushroom    int    not null,
    ingr_root        int    not null,
    silver_coins     int    not null,
    game_id          bigint null
);

CREATE TABLE game (
    id        bigint auto_increment primary key,
    game_code varchar(255) null
);

CREATE TABLE merchant (
    id bigint auto_increment primary key
);

CREATE TABLE mixture (
    id               bigint auto_increment primary key,
    name             varchar(255) null,
    gold_coin        int,
    ingr_chicken_leg int not null,
    ingr_feather     int not null,
    ingr_mushroom    int not null,
    ingr_root        int not null,
    point            int not null,
    silver_coin      int
);

CREATE TABLE available_merchant_game (
    merchant_id bigint not null,
    game_id     bigint not null,
    foreign key (merchant_id) references merchant(id),
    foreign key (game_id) references game(id),
    unique (merchant_id, game_id)
);

CREATE TABLE available_mixture_game (
    mixture_id bigint not null,
    game_id     bigint not null,
    foreign key (mixture_id) references mixture(id),
    foreign key (game_id) references game(id),
    unique (mixture_id, game_id)
);

CREATE TABLE unavailable_merchant_game (
    merchant_id bigint not null,
    game_id     bigint not null,
    foreign key (merchant_id) references merchant(id),
    foreign key (game_id) references game(id),
    unique (merchant_id, game_id)
);

CREATE TABLE unavailable_mixture_game (
    mixture_id bigint not null,
    game_id     bigint not null,
    foreign key (mixture_id) references mixture(id),
    foreign key (game_id) references game(id),
    unique (mixture_id, game_id)
);

CREATE TABLE mixture_alchemist (
    mixture_id   bigint not null,
    alchemist_id bigint not null,
    foreign key (mixture_id) references mixture(id),
    foreign key (alchemist_id) references alchemist(id),
    unique (mixture_id, alchemist_id)
);

CREATE TABLE played_merchant_alchemist (
    alchemist_id bigint not null,
    merchant_id  bigint not null,
    foreign key (merchant_id) references merchant(id),
    foreign key (alchemist_id) references alchemist(id),
    unique (merchant_id, alchemist_id)
);

CREATE TABLE hand_merchant_alchemist (
    alchemist_id bigint not null,
    merchant_id  bigint not null,
    foreign key (merchant_id) references merchant(id),
    foreign key (alchemist_id) references alchemist(id),
    unique (merchant_id, alchemist_id)
);
