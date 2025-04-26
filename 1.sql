-- test.loggers определение

-- Drop table

-- DROP TABLE test.loggers;

CREATE TABLE test.loggers (
                              pid int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
                              logger_name varchar(100) NOT NULL,
                              log bool NULL,
                              "level" varchar(10) NULL,
                              CONSTRAINT loggers_pk PRIMARY KEY (pid)
);