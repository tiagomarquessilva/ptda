--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.15
-- Dumped by pg_dump version 11.5 (Ubuntu 11.5-3.pgdg18.04+1)

-- Started on 2019-11-15 18:37:45 WET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 637 (class 1247 OID 66834)
-- Name: profile; Type: TYPE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TYPE public.profile AS ENUM (
    'ADMIN',
    'GUEST',
    'USER'
);


ALTER TYPE public.profile OWNER TO "ptda-2018-gr2";

--
-- TOC entry 653 (class 1247 OID 67247)
-- Name: type_of_equipment; Type: TYPE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TYPE public.type_of_equipment AS ENUM (
    'POOL',
    'SPRINKLER',
    'PETFEEDER',
    'ENTERTAINMENTSYSTEM',
    'LIGHTBULB',
    'BLIND',
    'VACUUMROBOT',
    'HUMIDIFIER_DEHUMIDIFIER',
    'THERMOSTATS',
    'DOOR',
    'SENSOR',
    'BINARY_STATE',
    'PERCENT_STATE'
);


ALTER TYPE public.type_of_equipment OWNER TO "ptda-2018-gr2";

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 62976)
-- Name: alarms; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.alarms (
    element_id integer NOT NULL,
    state text NOT NULL,
    CONSTRAINT alarms_state_check CHECK (((state = 'ACTIVE'::text) OR (state = 'DEACTIVE'::text)))
);


ALTER TABLE public.alarms OWNER TO "ptda-2018-gr2";

--
-- TOC entry 187 (class 1259 OID 62987)
-- Name: alerts; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.alerts (
    id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    title text,
    message text,
    origin_element_id integer NOT NULL
);


ALTER TABLE public.alerts OWNER TO "ptda-2018-gr2";

--
-- TOC entry 186 (class 1259 OID 62985)
-- Name: alerts_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.alerts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alerts_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2306 (class 0 OID 0)
-- Dependencies: 186
-- Name: alerts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.alerts_id_seq OWNED BY public.alerts.id;


--
-- TOC entry 209 (class 1259 OID 69010)
-- Name: consumption; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.consumption (
    element_id integer NOT NULL,
    date_and_time timestamp without time zone NOT NULL,
    consumption_in_wh integer
);


ALTER TABLE public.consumption OWNER TO "ptda-2018-gr2";

--
-- TOC entry 188 (class 1259 OID 63001)
-- Name: door_codes; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.door_codes (
    door_id integer NOT NULL,
    code integer NOT NULL
);


ALTER TABLE public.door_codes OWNER TO "ptda-2018-gr2";

--
-- TOC entry 190 (class 1259 OID 63017)
-- Name: elements; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.elements (
    id integer NOT NULL,
    room_id integer NOT NULL,
    name text,
    consumes text NOT NULL,
    socketportnumber integer,
    usedonlybyadmin boolean NOT NULL,
    ip text,
    type public.type_of_equipment,
    CONSTRAINT elements_consumes_check CHECK (((consumes = 'ELETRICITY'::text) OR (consumes = 'WATER'::text) OR (consumes = 'GAS'::text)))
);


ALTER TABLE public.elements OWNER TO "ptda-2018-gr2";

--
-- TOC entry 189 (class 1259 OID 63015)
-- Name: elements_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.elements_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.elements_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2307 (class 0 OID 0)
-- Dependencies: 189
-- Name: elements_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.elements_id_seq OWNED BY public.elements.id;


--
-- TOC entry 191 (class 1259 OID 63038)
-- Name: humidifier_dehumidifier; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.humidifier_dehumidifier (
    element_id integer NOT NULL,
    ideal_humidity real
);


ALTER TABLE public.humidifier_dehumidifier OWNER TO "ptda-2018-gr2";

--
-- TOC entry 206 (class 1259 OID 67196)
-- Name: pet_feeders; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.pet_feeders (
    element_id integer NOT NULL,
    amount_of_water_to_eject real NOT NULL,
    amount_of_food_to_eject real NOT NULL
);


ALTER TABLE public.pet_feeders OWNER TO "ptda-2018-gr2";

--
-- TOC entry 193 (class 1259 OID 63073)
-- Name: rooms; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.rooms (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.rooms OWNER TO "ptda-2018-gr2";

--
-- TOC entry 192 (class 1259 OID 63071)
-- Name: rooms_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.rooms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rooms_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2308 (class 0 OID 0)
-- Dependencies: 192
-- Name: rooms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.rooms_id_seq OWNED BY public.rooms.id;


--
-- TOC entry 195 (class 1259 OID 63084)
-- Name: routine_monthly_dates; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.routine_monthly_dates (
    routine_id integer NOT NULL,
    start_time time without time zone NOT NULL,
    end_time time without time zone NOT NULL,
    repeat_every_n_months integer NOT NULL,
    happens_day integer NOT NULL,
    id integer NOT NULL,
    "Column" integer
);


ALTER TABLE public.routine_monthly_dates OWNER TO "ptda-2018-gr2";

--
-- TOC entry 194 (class 1259 OID 63082)
-- Name: routine_monthly_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.routine_monthly_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.routine_monthly_dates_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2309 (class 0 OID 0)
-- Dependencies: 194
-- Name: routine_monthly_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.routine_monthly_dates_id_seq OWNED BY public.routine_monthly_dates.id;


--
-- TOC entry 197 (class 1259 OID 63092)
-- Name: routine_weekly_dates; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.routine_weekly_dates (
    routine_id integer NOT NULL,
    start_time time without time zone NOT NULL,
    end_time time without time zone NOT NULL,
    happens_monday boolean NOT NULL,
    happens_tuesday boolean NOT NULL,
    happens_wednesday boolean NOT NULL,
    happens_thursday boolean NOT NULL,
    happens_friday boolean NOT NULL,
    happens_saturday boolean NOT NULL,
    happens_sunday boolean NOT NULL,
    repeat_every_n_weeks integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.routine_weekly_dates OWNER TO "ptda-2018-gr2";

--
-- TOC entry 196 (class 1259 OID 63090)
-- Name: routine_weekly_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.routine_weekly_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.routine_weekly_dates_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2310 (class 0 OID 0)
-- Dependencies: 196
-- Name: routine_weekly_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.routine_weekly_dates_id_seq OWNED BY public.routine_weekly_dates.id;


--
-- TOC entry 199 (class 1259 OID 63100)
-- Name: routine_yearly_dates; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.routine_yearly_dates (
    routine_id integer NOT NULL,
    start_time time without time zone NOT NULL,
    end_time time without time zone NOT NULL,
    repeat_every_n_years integer NOT NULL,
    happens_day_month date NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.routine_yearly_dates OWNER TO "ptda-2018-gr2";

--
-- TOC entry 198 (class 1259 OID 63098)
-- Name: routine_yearly_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.routine_yearly_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.routine_yearly_dates_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 198
-- Name: routine_yearly_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.routine_yearly_dates_id_seq OWNED BY public.routine_yearly_dates.id;


--
-- TOC entry 201 (class 1259 OID 63108)
-- Name: routines; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.routines (
    id integer NOT NULL,
    date_of_creation timestamp without time zone NOT NULL,
    user_id integer,
    task_id integer,
    routine_day date
);


ALTER TABLE public.routines OWNER TO "ptda-2018-gr2";

--
-- TOC entry 200 (class 1259 OID 63106)
-- Name: routines_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.routines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.routines_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 200
-- Name: routines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.routines_id_seq OWNED BY public.routines.id;


--
-- TOC entry 207 (class 1259 OID 67590)
-- Name: tasks; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.tasks (
    id integer NOT NULL,
    room_id integer,
    routine_id integer,
    type_equip public.type_of_equipment
);


ALTER TABLE public.tasks OWNER TO "ptda-2018-gr2";

--
-- TOC entry 202 (class 1259 OID 63127)
-- Name: thermostats; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.thermostats (
    element_id integer NOT NULL,
    ideal_temperature real
);


ALTER TABLE public.thermostats OWNER TO "ptda-2018-gr2";

--
-- TOC entry 203 (class 1259 OID 63136)
-- Name: users; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.users (
    username text NOT NULL,
    password text NOT NULL,
    name text NOT NULL,
    alarmselement_id integer NOT NULL,
    profile text NOT NULL
);


ALTER TABLE public.users OWNER TO "ptda-2018-gr2";

--
-- TOC entry 205 (class 1259 OID 63155)
-- Name: zones; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.zones (
    id integer NOT NULL,
    room_id integer NOT NULL,
    created_by_admin_username text NOT NULL,
    date_of_creation timestamp without time zone NOT NULL,
    name text
);


ALTER TABLE public.zones OWNER TO "ptda-2018-gr2";

--
-- TOC entry 208 (class 1259 OID 68956)
-- Name: zones_elements; Type: TABLE; Schema: public; Owner: ptda-2018-gr2
--

CREATE TABLE public.zones_elements (
    zone_id integer NOT NULL,
    element_id integer NOT NULL
);


ALTER TABLE public.zones_elements OWNER TO "ptda-2018-gr2";

--
-- TOC entry 204 (class 1259 OID 63153)
-- Name: zones_id_seq; Type: SEQUENCE; Schema: public; Owner: ptda-2018-gr2
--

CREATE SEQUENCE public.zones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.zones_id_seq OWNER TO "ptda-2018-gr2";

--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 204
-- Name: zones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ptda-2018-gr2
--

ALTER SEQUENCE public.zones_id_seq OWNED BY public.zones.id;


--
-- TOC entry 2100 (class 2604 OID 62990)
-- Name: alerts id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.alerts ALTER COLUMN id SET DEFAULT nextval('public.alerts_id_seq'::regclass);


--
-- TOC entry 2101 (class 2604 OID 63020)
-- Name: elements id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.elements ALTER COLUMN id SET DEFAULT nextval('public.elements_id_seq'::regclass);


--
-- TOC entry 2103 (class 2604 OID 63076)
-- Name: rooms id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.rooms ALTER COLUMN id SET DEFAULT nextval('public.rooms_id_seq'::regclass);


--
-- TOC entry 2104 (class 2604 OID 63087)
-- Name: routine_monthly_dates id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_monthly_dates ALTER COLUMN id SET DEFAULT nextval('public.routine_monthly_dates_id_seq'::regclass);


--
-- TOC entry 2105 (class 2604 OID 63095)
-- Name: routine_weekly_dates id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_weekly_dates ALTER COLUMN id SET DEFAULT nextval('public.routine_weekly_dates_id_seq'::regclass);


--
-- TOC entry 2106 (class 2604 OID 63103)
-- Name: routine_yearly_dates id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_yearly_dates ALTER COLUMN id SET DEFAULT nextval('public.routine_yearly_dates_id_seq'::regclass);


--
-- TOC entry 2107 (class 2604 OID 63111)
-- Name: routines id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routines ALTER COLUMN id SET DEFAULT nextval('public.routines_id_seq'::regclass);


--
-- TOC entry 2108 (class 2604 OID 63158)
-- Name: zones id; Type: DEFAULT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.zones ALTER COLUMN id SET DEFAULT nextval('public.zones_id_seq'::regclass);


--
-- TOC entry 2276 (class 0 OID 62976)
-- Dependencies: 185
-- Data for Name: alarms; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.alarms (element_id, state) FROM stdin;
1	ACTIVE
\.


--
-- TOC entry 2278 (class 0 OID 62987)
-- Dependencies: 187
-- Data for Name: alerts; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.alerts (id, date, title, message, origin_element_id) FROM stdin;
\.


--
-- TOC entry 2300 (class 0 OID 69010)
-- Dependencies: 209
-- Data for Name: consumption; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.consumption (element_id, date_and_time, consumption_in_wh) FROM stdin;
\.


--
-- TOC entry 2279 (class 0 OID 63001)
-- Dependencies: 188
-- Data for Name: door_codes; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.door_codes (door_id, code) FROM stdin;
\.


--
-- TOC entry 2281 (class 0 OID 63017)
-- Dependencies: 190
-- Data for Name: elements; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.elements (id, room_id, name, consumes, socketportnumber, usedonlybyadmin, ip, type) FROM stdin;
1	1	Frigorífico	ELETRICITY	2000	f	127.0.0.1	BINARY_STATE
2	1	Estore Cozinha	ELETRICITY	2001	f	127.0.0.1	PERCENT_STATE
3	1	Lâmpada 1 Cozinha	ELETRICITY	2002	f	127.0.0.1	LIGHTBULB
4	1	Lâmpada 2 Cozinha	ELETRICITY	2003	f	127.0.0.1	LIGHTBULB
5	1	Lâmpada 3 Cozinha	ELETRICITY	2004	f	127.0.0.1	LIGHTBULB
6	1	Fogão	GAS	2005	f	127.0.0.1	BINARY_STATE
10	1	Detetor de Fumo Cozinha	ELETRICITY	2009	f	127.0.0.1	SENSOR
9	1	Humidificador/Desumidificador Cozinha	ELETRICITY	2008	f	127.0.0.1	SENSOR
11	1	Sistema de Som Cozinha	ELETRICITY	2010	f	127.0.0.1	ENTERTAINMENTSYSTEM
8	1	Sensor de Humidade	ELETRICITY	2007	f	127.0.0.1	SENSOR
7	1	Sensor Temperatura Cozinha	ELETRICITY	2006	f	127.0.0.1	SENSOR
12	1	TV Cozinha	ELETRICITY	2011	f	127.0.0.1	ENTERTAINMENTSYSTEM
13	1	Máquina de Café Cozinha	ELETRICITY	2012	f	127.0.0.1	BINARY_STATE
\.


--
-- TOC entry 2282 (class 0 OID 63038)
-- Dependencies: 191
-- Data for Name: humidifier_dehumidifier; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.humidifier_dehumidifier (element_id, ideal_humidity) FROM stdin;
\.


--
-- TOC entry 2297 (class 0 OID 67196)
-- Dependencies: 206
-- Data for Name: pet_feeders; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.pet_feeders (element_id, amount_of_water_to_eject, amount_of_food_to_eject) FROM stdin;
\.


--
-- TOC entry 2284 (class 0 OID 63073)
-- Dependencies: 193
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.rooms (id, name) FROM stdin;
1	kitchen
2	living_room
3	bedroom_1
4	bedroom_2
5	office
6	bathroom
7	toilet
9	garage
8	garden_back
10	garden_front
\.


--
-- TOC entry 2286 (class 0 OID 63084)
-- Dependencies: 195
-- Data for Name: routine_monthly_dates; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.routine_monthly_dates (routine_id, start_time, end_time, repeat_every_n_months, happens_day, id, "Column") FROM stdin;
\.


--
-- TOC entry 2288 (class 0 OID 63092)
-- Dependencies: 197
-- Data for Name: routine_weekly_dates; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.routine_weekly_dates (routine_id, start_time, end_time, happens_monday, happens_tuesday, happens_wednesday, happens_thursday, happens_friday, happens_saturday, happens_sunday, repeat_every_n_weeks, id) FROM stdin;
\.


--
-- TOC entry 2290 (class 0 OID 63100)
-- Dependencies: 199
-- Data for Name: routine_yearly_dates; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.routine_yearly_dates (routine_id, start_time, end_time, repeat_every_n_years, happens_day_month, id) FROM stdin;
\.


--
-- TOC entry 2292 (class 0 OID 63108)
-- Dependencies: 201
-- Data for Name: routines; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.routines (id, date_of_creation, user_id, task_id, routine_day) FROM stdin;
\.


--
-- TOC entry 2298 (class 0 OID 67590)
-- Dependencies: 207
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.tasks (id, room_id, routine_id, type_equip) FROM stdin;
\.


--
-- TOC entry 2293 (class 0 OID 63127)
-- Dependencies: 202
-- Data for Name: thermostats; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.thermostats (element_id, ideal_temperature) FROM stdin;
\.


--
-- TOC entry 2294 (class 0 OID 63136)
-- Dependencies: 203
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.users (username, password, name, alarmselement_id, profile) FROM stdin;
teste	password123	Teste	1	ADMIN
ml	ml123	Manuel Carlos	1	USER
jc	jc123	José Carlos	1	ADMIN
lj		Jose Jorge	1	GUEST
\.


--
-- TOC entry 2296 (class 0 OID 63155)
-- Dependencies: 205
-- Data for Name: zones; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.zones (id, room_id, created_by_admin_username, date_of_creation, name) FROM stdin;
2	2	teste	2019-01-15 00:00:00	dinnig_room
3	8	teste	2019-01-15 00:00:00	pool
1	1	teste	2019-01-15 00:00:00	Zona do Café
\.


--
-- TOC entry 2299 (class 0 OID 68956)
-- Dependencies: 208
-- Data for Name: zones_elements; Type: TABLE DATA; Schema: public; Owner: ptda-2018-gr2
--

COPY public.zones_elements (zone_id, element_id) FROM stdin;
1	3
1	13
\.


--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 186
-- Name: alerts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.alerts_id_seq', 1, false);


--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 189
-- Name: elements_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.elements_id_seq', 3, true);


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 192
-- Name: rooms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.rooms_id_seq', 1, false);


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 194
-- Name: routine_monthly_dates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.routine_monthly_dates_id_seq', 1, false);


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 196
-- Name: routine_weekly_dates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.routine_weekly_dates_id_seq', 1, false);


--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 198
-- Name: routine_yearly_dates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.routine_yearly_dates_id_seq', 1, false);


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 200
-- Name: routines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.routines_id_seq', 1, false);


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 204
-- Name: zones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ptda-2018-gr2
--

SELECT pg_catalog.setval('public.zones_id_seq', 1, false);


--
-- TOC entry 2110 (class 2606 OID 62984)
-- Name: alarms alarms_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.alarms
    ADD CONSTRAINT alarms_pkey PRIMARY KEY (element_id);


--
-- TOC entry 2112 (class 2606 OID 62995)
-- Name: alerts alerts_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.alerts
    ADD CONSTRAINT alerts_pkey PRIMARY KEY (id);


--
-- TOC entry 2144 (class 2606 OID 69014)
-- Name: consumption consumption_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.consumption
    ADD CONSTRAINT consumption_pkey PRIMARY KEY (element_id, date_and_time);


--
-- TOC entry 2114 (class 2606 OID 63005)
-- Name: door_codes door_codes_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.door_codes
    ADD CONSTRAINT door_codes_pkey PRIMARY KEY (door_id, code);


--
-- TOC entry 2117 (class 2606 OID 63028)
-- Name: elements elements_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.elements
    ADD CONSTRAINT elements_pkey PRIMARY KEY (id);


--
-- TOC entry 2119 (class 2606 OID 63046)
-- Name: humidifier_dehumidifier humidifier_dehumidifier_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.humidifier_dehumidifier
    ADD CONSTRAINT humidifier_dehumidifier_pkey PRIMARY KEY (element_id);


--
-- TOC entry 2137 (class 2606 OID 67205)
-- Name: pet_feeders pet_feeders_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.pet_feeders
    ADD CONSTRAINT pet_feeders_pkey PRIMARY KEY (element_id);


--
-- TOC entry 2121 (class 2606 OID 63081)
-- Name: rooms rooms_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);


--
-- TOC entry 2123 (class 2606 OID 63089)
-- Name: routine_monthly_dates routine_monthly_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_monthly_dates
    ADD CONSTRAINT routine_monthly_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 2125 (class 2606 OID 63097)
-- Name: routine_weekly_dates routine_weekly_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_weekly_dates
    ADD CONSTRAINT routine_weekly_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 2127 (class 2606 OID 63105)
-- Name: routine_yearly_dates routine_yearly_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_yearly_dates
    ADD CONSTRAINT routine_yearly_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 2129 (class 2606 OID 63117)
-- Name: routines routines_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routines
    ADD CONSTRAINT routines_pkey PRIMARY KEY (id);


--
-- TOC entry 2139 (class 2606 OID 67594)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- TOC entry 2131 (class 2606 OID 63135)
-- Name: thermostats thermostats_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.thermostats
    ADD CONSTRAINT thermostats_pkey PRIMARY KEY (element_id);


--
-- TOC entry 2133 (class 2606 OID 63143)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- TOC entry 2142 (class 2606 OID 68960)
-- Name: zones_elements zones_elements_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.zones_elements
    ADD CONSTRAINT zones_elements_pkey PRIMARY KEY (zone_id, element_id);


--
-- TOC entry 2135 (class 2606 OID 63164)
-- Name: zones zones_pkey; Type: CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.zones
    ADD CONSTRAINT zones_pkey PRIMARY KEY (id);


--
-- TOC entry 2115 (class 1259 OID 67245)
-- Name: fki_C; Type: INDEX; Schema: public; Owner: ptda-2018-gr2
--

CREATE INDEX "fki_C" ON public.door_codes USING btree (door_id);


--
-- TOC entry 2140 (class 1259 OID 68971)
-- Name: fki_element_id_fk; Type: INDEX; Schema: public; Owner: ptda-2018-gr2
--

CREATE INDEX fki_element_id_fk ON public.zones_elements USING btree (element_id);


--
-- TOC entry 2147 (class 2606 OID 67240)
-- Name: door_codes codes_element_id; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.door_codes
    ADD CONSTRAINT codes_element_id FOREIGN KEY (door_id) REFERENCES public.elements(id);


--
-- TOC entry 2158 (class 2606 OID 68966)
-- Name: zones_elements element_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.zones_elements
    ADD CONSTRAINT element_id_fk FOREIGN KEY (element_id) REFERENCES public.elements(id);


--
-- TOC entry 2145 (class 2606 OID 63185)
-- Name: alarms fkalarms594429; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.alarms
    ADD CONSTRAINT fkalarms594429 FOREIGN KEY (element_id) REFERENCES public.elements(id);


--
-- TOC entry 2146 (class 2606 OID 63180)
-- Name: alerts fkalerts878696; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.alerts
    ADD CONSTRAINT fkalerts878696 FOREIGN KEY (origin_element_id) REFERENCES public.elements(id);


--
-- TOC entry 2148 (class 2606 OID 63170)
-- Name: elements fkelements256977; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.elements
    ADD CONSTRAINT fkelements256977 FOREIGN KEY (room_id) REFERENCES public.rooms(id);


--
-- TOC entry 2149 (class 2606 OID 63225)
-- Name: humidifier_dehumidifier fkhumidifier569362; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.humidifier_dehumidifier
    ADD CONSTRAINT fkhumidifier569362 FOREIGN KEY (element_id) REFERENCES public.elements(id);


--
-- TOC entry 2156 (class 2606 OID 67235)
-- Name: pet_feeders fkpet_feeder418587; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.pet_feeders
    ADD CONSTRAINT fkpet_feeder418587 FOREIGN KEY (element_id) REFERENCES public.elements(id);


--
-- TOC entry 2150 (class 2606 OID 63245)
-- Name: routine_monthly_dates fkroutine_mo399408; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_monthly_dates
    ADD CONSTRAINT fkroutine_mo399408 FOREIGN KEY (routine_id) REFERENCES public.routines(id);


--
-- TOC entry 2151 (class 2606 OID 63190)
-- Name: routine_weekly_dates fkroutine_we23927; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_weekly_dates
    ADD CONSTRAINT fkroutine_we23927 FOREIGN KEY (routine_id) REFERENCES public.routines(id);


--
-- TOC entry 2152 (class 2606 OID 63250)
-- Name: routine_yearly_dates fkroutine_ye917454; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.routine_yearly_dates
    ADD CONSTRAINT fkroutine_ye917454 FOREIGN KEY (routine_id) REFERENCES public.routines(id);


--
-- TOC entry 2153 (class 2606 OID 63220)
-- Name: thermostats fkthermostat37922; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.thermostats
    ADD CONSTRAINT fkthermostat37922 FOREIGN KEY (element_id) REFERENCES public.elements(id);


--
-- TOC entry 2154 (class 2606 OID 63255)
-- Name: users fkusers279180; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkusers279180 FOREIGN KEY (alarmselement_id) REFERENCES public.alarms(element_id);


--
-- TOC entry 2155 (class 2606 OID 63175)
-- Name: zones fkzones681629; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.zones
    ADD CONSTRAINT fkzones681629 FOREIGN KEY (room_id) REFERENCES public.rooms(id);


--
-- TOC entry 2157 (class 2606 OID 68961)
-- Name: zones_elements zone_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: ptda-2018-gr2
--

ALTER TABLE ONLY public.zones_elements
    ADD CONSTRAINT zone_id_fk FOREIGN KEY (zone_id) REFERENCES public.zones(id);


-- Completed on 2019-11-15 18:37:48 WET

--
-- PostgreSQL database dump complete
--

