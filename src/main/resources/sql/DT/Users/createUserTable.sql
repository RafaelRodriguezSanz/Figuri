CREATE TABLE IF NOT EXISTS public."Users"
(
    "Nombre" text NOT NULL,
    "Apellido" text NOT NULL,
    "CI" integer NOT NULL,
    "Telefono" integer,
    "Contrasenia" text NOT NULL,
    "Direccion" text,
    "Foto" text,
    CONSTRAINT "Users_pkey" PRIMARY KEY ("CI")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Users"
    OWNER to postgres;