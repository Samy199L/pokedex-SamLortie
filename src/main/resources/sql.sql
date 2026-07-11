-- Table: public.pokemons

-- DROP TABLE IF EXISTS public.pokemons;

CREATE TABLE IF NOT EXISTS public.pokemons
(
    experience_base integer,
    crie text COLLATE pg_catalog."default",
    grandeur integer,
    id integer NOT NULL,
    is_default boolean,
    nom character varying(100) COLLATE pg_catalog."default",
    ordre integer,
    espece character varying(100) COLLATE pg_catalog."default",
    image text COLLATE pg_catalog."default",
    stats text COLLATE pg_catalog."default",
    type_poke character varying(100) COLLATE pg_catalog."default",
    weight integer,
    CONSTRAINT pokemons_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pokemons
    OWNER to postgres;