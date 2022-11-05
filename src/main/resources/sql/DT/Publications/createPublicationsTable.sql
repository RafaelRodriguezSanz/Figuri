CREATE TABLE public."Publications"
(
    id text NOT NULL,
    user_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public."Users" ("CI") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."Publications"
    OWNER to postgres;