--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE animals (
    animal_id integer NOT NULL,
    name character varying,
    description text,
    is_endangered character varying,
    health character varying,
    age character varying
);


ALTER TABLE animals OWNER TO t1k1;

--
-- Name: animals_animal_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE animals_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animals_animal_id_seq OWNER TO t1k1;

--
-- Name: animals_animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE animals_animal_id_seq OWNED BY animals.animal_id;


--
-- Name: rangers; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE rangers (
    ranger_id integer NOT NULL,
    name character varying,
    email character varying,
    phone_number character varying,
    badge_number integer
);


ALTER TABLE rangers OWNER TO t1k1;

--
-- Name: rangers_ranger_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE rangers_ranger_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rangers_ranger_id_seq OWNER TO t1k1;

--
-- Name: rangers_ranger_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE rangers_ranger_id_seq OWNED BY rangers.ranger_id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE sightings (
    sighting_id integer NOT NULL,
    animal_id integer,
    ranger_id integer,
    location character varying,
    sighting_date character varying,
    is_endangered character varying,
    timestamp_sighting_date timestamp without time zone
);


ALTER TABLE sightings OWNER TO t1k1;

--
-- Name: sightings_sighting_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE sightings_sighting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_sighting_id_seq OWNER TO t1k1;

--
-- Name: sightings_sighting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE sightings_sighting_id_seq OWNED BY sightings.sighting_id;


--
-- Name: animals animal_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY animals ALTER COLUMN animal_id SET DEFAULT nextval('animals_animal_id_seq'::regclass);


--
-- Name: rangers ranger_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY rangers ALTER COLUMN ranger_id SET DEFAULT nextval('rangers_ranger_id_seq'::regclass);


--
-- Name: sightings sighting_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY sightings ALTER COLUMN sighting_id SET DEFAULT nextval('sightings_sighting_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY animals (animal_id, name, description, is_endangered, health, age) FROM stdin;
1	Bear	Grey	Endangered	healthy	young
2	Goat	Brown	Not Endangered	okay	adult
3	Deer	Brown	Endangered	okay	adult
\.


--
-- Name: animals_animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('animals_animal_id_seq', 3, true);


--
-- Data for Name: rangers; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY rangers (ranger_id, name, email, phone_number, badge_number) FROM stdin;
1	Bob	test@gmail.com	805-279-0376	987246
2	hey	asldkfjsadfy	983704987`	9087433
3	askldsjfh	lskjlasfdj 	9837983498	348789
\.


--
-- Name: rangers_ranger_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('rangers_ranger_id_seq', 3, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY sightings (sighting_id, animal_id, ranger_id, location, sighting_date, is_endangered, timestamp_sighting_date) FROM stdin;
1	1	1	NE Quadrant	2017-04-04 01:25	Endangered	2017-04-04 01:25:00
2	3	3	SE Quadrant	2017-04-07 08:25	Endangered	2017-04-07 08:25:00
3	3	2	NE Quadrant	2017-04-12 23:45	Endangered	2017-04-12 23:45:00
4	3	3	SE Quadrant	2017-04-05 02:05	Endangered	2017-04-05 02:05:00
5	3	2	NW Quadrant	2017-04-03 03:25	Endangered	2017-04-03 03:25:00
\.


--
-- Name: sightings_sighting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('sightings_sighting_id_seq', 5, true);


--
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);


--
-- Name: rangers rangers_pkey; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY rangers
    ADD CONSTRAINT rangers_pkey PRIMARY KEY (ranger_id);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (sighting_id);


--
-- PostgreSQL database dump complete
--

