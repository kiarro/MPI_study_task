# postgres
FROM postgres

EXPOSE 5432

ENV POSTGRES_USER alien
ENV POSTGRES_PASSWORD alien
ENV POSTGRES_DB alien_base
# copy sql script for creating tables
COPY create_base.sql /docker-entrypoint-initdb.d/