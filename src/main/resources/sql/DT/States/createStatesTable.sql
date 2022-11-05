CREATE TABLE public."States"
(
    id text,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public."States"
    OWNER to postgres;

INSERT INTO public."States"(id)
VALUES  ('Excelente'),
        ('Bueno'),
        ('Malo'),
        ('Pésimo');


