-- V1: initial schema for the ATM domain (see docs/entity_model.md).
--
-- H2 runs in PostgreSQL-compatibility mode (MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE),
-- so this migration is written in PostgreSQL-flavoured SQL: one sequence per table,
-- consumed as a column DEFAULT via nextval(). Keep new SQL PostgreSQL-compatible.

-- Sequences (one per table) backing the surrogate primary keys.
CREATE SEQUENCE IF NOT EXISTS customer_seq START WITH 1 INCREMENT BY 1;

-- CUSTOMER — a bank account identified by a unique name, holding a cash balance.
-- FR-001/FR-002/FR-004/FR-006; balance is never negative (FR-015).
CREATE TABLE IF NOT EXISTS customer (
    id                bigint        NOT NULL DEFAULT nextval('customer_seq'),
    name              varchar(255)  NOT NULL,
    balance           numeric(19, 2) NOT NULL DEFAULT 0,
    created           timestamp,
    created_by        varchar(255),
    last_modified     timestamp,
    last_modified_by  varchar(255),
    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT uk_customer_name UNIQUE (name),
    CONSTRAINT ck_customer_balance_non_negative CHECK (balance >= 0)
);
