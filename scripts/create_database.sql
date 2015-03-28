-- Companion SQL to setup_db.py management command.
-- Performs the initial setup of the SQL database for use in HunchLab.

-- Create hunchlab user
CREATE USER v WITH PASSWORD 'hunchlab';

-- Create hunchlab database
CREATE DATABASE hunchlab ENCODING 'UTF-8';

-- Add spatial and hstore capabilities to the hunchlab template
\c hunchlab
CREATE EXTENSION postgis;
CREATE EXTENSION hstore;

-- Grant hunchlab user appropriate ownership of spatial tables
ALTER TABLE spatial_ref_sys OWNER TO hunchlab;
ALTER TABLE geometry_columns OWNER TO hunchlab;
ALTER VIEW geography_columns OWNER TO hunchlab;
