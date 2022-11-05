CREATE TABLE public."Types"
(
    id text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public."Types"
    OWNER to postgres;

INSERT INTO public."Types"(id)
VALUES  ('Especiales'),
        ('Equipo'),
        ('Jugadores'),
        ('Escudo'),
        ('Historia');