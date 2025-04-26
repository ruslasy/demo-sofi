-- test.logs определение

-- Drop table

-- DROP TABLE test.logs;

CREATE TABLE test.logs (
                           pid int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
                           message varchar(2000) NULL,
                           url varchar(1000) NULL,
                           ip varchar(15) NULL,
                           stack_trace text NULL,
                           add_info text NULL,
                           logger_pid int8 NOT NULL,
                           CONSTRAINT logs_pk PRIMARY KEY (pid),
                           CONSTRAINT logs_loggers_fk FOREIGN KEY (logger_pid) REFERENCES test.loggers(pid)
);
CREATE INDEX logs_logger_pid_idx ON test.logs USING btree (logger_pid);