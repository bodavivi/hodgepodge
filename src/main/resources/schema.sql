DROP TABLE if exists available_merchant_game;
DROP TABLE if exists available_mixture_game;
DROP TABLE if exists unavailable_merchant_game;
DROP TABLE if exists unavailable_mixture_game;
DROP TABLE if exists mixture_alchemist;
DROP TABLE if exists hand_merchant_alchemist;
DROP TABLE if exists played_merchant_alchemist;
DROP TABLE if exists alchemists;
DROP TABLE if exists games;
DROP TABLE if exists merchants;
DROP TABLE if exists mixtures;

CREATE TABLE alchemists (
    id               bigint auto_increment primary key,
    gold_coins       int    not null,
    ingr_chicken_leg int    not null,
    ingr_feather     int    not null,
    ingr_mushroom    int    not null,
    ingr_root        int    not null,
    silver_coins     int    not null,
    game_id          bigint null
);

CREATE TABLE games (
    id        bigint auto_increment primary key,
    game_code varchar(255) null
);

CREATE TABLE merchants (
    id                      bigint auto_increment primary key,
    dtype                   VARCHAR(80),
    obtain_root             int,
    obtain_mushroom         int,
    obtain_feather          int,
    obtain_chicken_leg      int,
    trade_root_from         int,
    trade_root_to           int,
    trade_mushroom_from     int,
    trade_mushroom_to       int,
    trade_feather_from      int,
    trade_feather_to        int,
    trade_chicken_leg_from  int,
    trade_chicken_leg_to    int,
    number_of_updates       int
);

CREATE TABLE mixtures (
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
    foreign key (merchant_id) references merchants(id),
    foreign key (game_id) references games(id),
    unique (merchant_id, game_id)
);

CREATE TABLE available_mixture_game (
    mixture_id bigint not null,
    game_id     bigint not null,
    foreign key (mixture_id) references mixtures(id),
    foreign key (game_id) references games(id),
    unique (mixture_id, game_id)
);

CREATE TABLE unavailable_merchant_game (
    merchant_id bigint not null,
    game_id     bigint not null,
    foreign key (merchant_id) references merchants(id),
    foreign key (game_id) references games(id),
    unique (merchant_id, game_id)
);

CREATE TABLE unavailable_mixture_game (
    mixture_id bigint not null,
    game_id     bigint not null,
    foreign key (mixture_id) references mixtures(id),
    foreign key (game_id) references games(id),
    unique (mixture_id, game_id)
);

CREATE TABLE mixture_alchemist (
    mixture_id   bigint not null,
    alchemist_id bigint not null,
    foreign key (mixture_id) references mixtures(id),
    foreign key (alchemist_id) references alchemists(id),
    unique (mixture_id, alchemist_id)
);

CREATE TABLE played_merchant_alchemist (
    alchemist_id bigint not null,
    merchant_id  bigint not null,
    foreign key (merchant_id) references merchants(id),
    foreign key (alchemist_id) references alchemists(id),
    unique (merchant_id, alchemist_id)
);

CREATE TABLE hand_merchant_alchemist (
    alchemist_id bigint not null,
    merchant_id  bigint not null,
    foreign key (merchant_id) references merchants(id),
    foreign key (alchemist_id) references alchemists(id),
    unique (merchant_id, alchemist_id)
);
