-- test.params определение

-- Drop table

-- DROP TABLE test.params;

CREATE TABLE test.params (
                             pid int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
                             params text NULL,
                             log_pid int8 NOT NULL,
                             CONSTRAINT params_pk PRIMARY KEY (pid),
                             CONSTRAINT params_logs_fk FOREIGN KEY (log_pid) REFERENCES test.logs(pid)
);
CREATE INDEX params_log_pid_idx ON test.params USING btree (log_pid);