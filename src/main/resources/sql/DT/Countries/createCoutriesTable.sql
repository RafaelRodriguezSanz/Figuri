CREATE TABLE public."Countries"
(
    id text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public."Countries"
    OWNER to postgres;

INSERT INTO public."Countries"(id)
VALUES  ('Qatar'),
        ('Alemania'),
        ('Dinamarca'),
        ('Francia'),
        ('Bélgica'),
        ('Croacia'),
        ('España'),
        ('Serbia'),
        ('Inglaterra'),
        ('Suiza'),
        ('Países Bajos'),
        ('Portugal'),
        ('Polonia'),
        ('Gales'),
        ('Brasil'),
        ('Argentina'),
        ('Uruguay'),
        ('Ecuador'),
        ('Canadá'),
        ('México'),
        ('Estados Unidos'),
        ('Costa Rica'),
        ('Irán'),
        ('Corea del Sur'),
        ('Japón'),
        ('Arabia Saudita'),
        ('Australia'),
        ('Ghana'),
        ('Senegal'),
        ('Túnez'),
        ('Marruecos'),
        ('Camerún');