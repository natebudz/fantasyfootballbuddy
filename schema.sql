--
-- PostgreSQL database dump
--

\restrict DwtSaVAd78z8GYAi6KNV6Qxamqtc4dNQ2ed71CGY5ygl8pwnh9pCoaFUxEjFtue

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: fantasyscoring; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fantasyscoring (
    scoring_id integer NOT NULL,
    scoring_text character varying(10) NOT NULL,
    stat_id integer NOT NULL,
    points numeric(10,2) CONSTRAINT fantasyscoring_pounts_not_null NOT NULL
);


ALTER TABLE public.fantasyscoring OWNER TO postgres;

--
-- Name: fantasyscoring_scoring_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fantasyscoring_scoring_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fantasyscoring_scoring_id_seq OWNER TO postgres;

--
-- Name: fantasyscoring_scoring_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fantasyscoring_scoring_id_seq OWNED BY public.fantasyscoring.scoring_id;


--
-- Name: nflgame; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nflgame (
    game_id character varying(20) NOT NULL,
    season_year integer NOT NULL,
    week character varying(20) NOT NULL,
    game_date date,
    home_team_id integer,
    away_team_id integer,
    home_team_score integer,
    away_team_score integer,
    status character varying(20)
);


ALTER TABLE public.nflgame OWNER TO postgres;

--
-- Name: nflgame_game_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nflgame_game_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nflgame_game_id_seq OWNER TO postgres;

--
-- Name: nflgame_game_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nflgame_game_id_seq OWNED BY public.nflgame.game_id;


--
-- Name: nflplayer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nflplayer (
    player_id integer NOT NULL,
    first_name character varying(25),
    last_name character varying(25),
    position_id integer,
    team_id integer,
    status character varying(20),
    birthdate date,
    height character varying(10),
    weight integer
);


ALTER TABLE public.nflplayer OWNER TO postgres;

--
-- Name: nflplayer_player_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nflplayer_player_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nflplayer_player_id_seq OWNER TO postgres;

--
-- Name: nflplayer_player_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nflplayer_player_id_seq OWNED BY public.nflplayer.player_id;


--
-- Name: nflplayergamestat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nflplayergamestat (
    player_game_stat_id integer NOT NULL,
    player_id integer,
    game_id character varying(20),
    stat_id integer,
    game_stat_value numeric(10,2)
);


ALTER TABLE public.nflplayergamestat OWNER TO postgres;

--
-- Name: nflplayergamestat_player_game_stat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nflplayergamestat_player_game_stat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nflplayergamestat_player_game_stat_id_seq OWNER TO postgres;

--
-- Name: nflplayergamestat_player_game_stat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nflplayergamestat_player_game_stat_id_seq OWNED BY public.nflplayergamestat.player_game_stat_id;


--
-- Name: nflplayerseasonstat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nflplayerseasonstat (
    player_season_stat_id integer NOT NULL,
    player_id integer NOT NULL,
    season integer NOT NULL,
    stat_id integer NOT NULL,
    season_stat_value numeric(10,2)
);


ALTER TABLE public.nflplayerseasonstat OWNER TO postgres;

--
-- Name: nflplayerseasonstat_player_season_stat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nflplayerseasonstat_player_season_stat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nflplayerseasonstat_player_season_stat_id_seq OWNER TO postgres;

--
-- Name: nflplayerseasonstat_player_season_stat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nflplayerseasonstat_player_season_stat_id_seq OWNED BY public.nflplayerseasonstat.player_season_stat_id;


--
-- Name: nflposition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nflposition (
    position_id integer NOT NULL,
    position_name character varying(7),
    position_type character varying(20)
);


ALTER TABLE public.nflposition OWNER TO postgres;

--
-- Name: nflposition_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nflposition_position_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nflposition_position_id_seq OWNER TO postgres;

--
-- Name: nflposition_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nflposition_position_id_seq OWNED BY public.nflposition.position_id;


--
-- Name: nflstat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nflstat (
    stat_id integer NOT NULL,
    stat_code character varying(10),
    stat_name character varying(30),
    category character varying(20),
    unit character varying(20)
);


ALTER TABLE public.nflstat OWNER TO postgres;

--
-- Name: nflstat_stat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nflstat_stat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nflstat_stat_id_seq OWNER TO postgres;

--
-- Name: nflstat_stat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nflstat_stat_id_seq OWNED BY public.nflstat.stat_id;


--
-- Name: nflteam; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nflteam (
    team_id integer NOT NULL,
    team_name character varying(30) NOT NULL,
    city character varying(20) NOT NULL,
    conference character(3),
    division character varying(5)
);


ALTER TABLE public.nflteam OWNER TO postgres;

--
-- Name: nflteam_team_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nflteam_team_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nflteam_team_id_seq OWNER TO postgres;

--
-- Name: nflteam_team_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nflteam_team_id_seq OWNED BY public.nflteam.team_id;


--
-- Name: fantasyscoring scoring_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fantasyscoring ALTER COLUMN scoring_id SET DEFAULT nextval('public.fantasyscoring_scoring_id_seq'::regclass);


--
-- Name: nflplayer player_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayer ALTER COLUMN player_id SET DEFAULT nextval('public.nflplayer_player_id_seq'::regclass);


--
-- Name: nflplayergamestat player_game_stat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayergamestat ALTER COLUMN player_game_stat_id SET DEFAULT nextval('public.nflplayergamestat_player_game_stat_id_seq'::regclass);


--
-- Name: nflplayerseasonstat player_season_stat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayerseasonstat ALTER COLUMN player_season_stat_id SET DEFAULT nextval('public.nflplayerseasonstat_player_season_stat_id_seq'::regclass);


--
-- Name: nflposition position_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflposition ALTER COLUMN position_id SET DEFAULT nextval('public.nflposition_position_id_seq'::regclass);


--
-- Name: nflstat stat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflstat ALTER COLUMN stat_id SET DEFAULT nextval('public.nflstat_stat_id_seq'::regclass);


--
-- Name: nflteam team_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflteam ALTER COLUMN team_id SET DEFAULT nextval('public.nflteam_team_id_seq'::regclass);


--
-- Name: fantasyscoring fantasyscoring_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fantasyscoring
    ADD CONSTRAINT fantasyscoring_pkey PRIMARY KEY (scoring_id);


--
-- Name: nflgame nflgame_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflgame
    ADD CONSTRAINT nflgame_pkey PRIMARY KEY (game_id);


--
-- Name: nflgame nflgame_season_year_week_home_team_id_away_team_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflgame
    ADD CONSTRAINT nflgame_season_year_week_home_team_id_away_team_id_key UNIQUE (season_year, week, home_team_id, away_team_id);


--
-- Name: nflplayer nflplayer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayer
    ADD CONSTRAINT nflplayer_pkey PRIMARY KEY (player_id);


--
-- Name: nflplayergamestat nflplayergamestat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayergamestat
    ADD CONSTRAINT nflplayergamestat_pkey PRIMARY KEY (player_game_stat_id);


--
-- Name: nflplayergamestat nflplayergamestat_player_id_game_id_stat_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayergamestat
    ADD CONSTRAINT nflplayergamestat_player_id_game_id_stat_id_key UNIQUE (player_id, game_id, stat_id);


--
-- Name: nflplayerseasonstat nflplayerseasonstat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayerseasonstat
    ADD CONSTRAINT nflplayerseasonstat_pkey PRIMARY KEY (player_season_stat_id);


--
-- Name: nflplayerseasonstat nflplayerseasonstat_player_id_season_stat_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayerseasonstat
    ADD CONSTRAINT nflplayerseasonstat_player_id_season_stat_id_key UNIQUE (player_id, season, stat_id);


--
-- Name: nflposition nflposition_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflposition
    ADD CONSTRAINT nflposition_pkey PRIMARY KEY (position_id);


--
-- Name: nflstat nflstat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflstat
    ADD CONSTRAINT nflstat_pkey PRIMARY KEY (stat_id);


--
-- Name: nflteam nflteam_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflteam
    ADD CONSTRAINT nflteam_pkey PRIMARY KEY (team_id);


--
-- Name: fantasyscoring fantasyscoring_stat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fantasyscoring
    ADD CONSTRAINT fantasyscoring_stat_id_fkey FOREIGN KEY (stat_id) REFERENCES public.nflstat(stat_id);


--
-- Name: nflgame nflgame_away_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflgame
    ADD CONSTRAINT nflgame_away_team_id_fkey FOREIGN KEY (away_team_id) REFERENCES public.nflteam(team_id);


--
-- Name: nflgame nflgame_home_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflgame
    ADD CONSTRAINT nflgame_home_team_id_fkey FOREIGN KEY (home_team_id) REFERENCES public.nflteam(team_id);


--
-- Name: nflplayer nflplayer_position_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayer
    ADD CONSTRAINT nflplayer_position_id_fkey FOREIGN KEY (position_id) REFERENCES public.nflposition(position_id);


--
-- Name: nflplayer nflplayer_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayer
    ADD CONSTRAINT nflplayer_team_id_fkey FOREIGN KEY (team_id) REFERENCES public.nflteam(team_id);


--
-- Name: nflplayergamestat nflplayergamestat_game_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayergamestat
    ADD CONSTRAINT nflplayergamestat_game_id_fkey FOREIGN KEY (game_id) REFERENCES public.nflgame(game_id);


--
-- Name: nflplayergamestat nflplayergamestat_player_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayergamestat
    ADD CONSTRAINT nflplayergamestat_player_id_fkey FOREIGN KEY (player_id) REFERENCES public.nflplayer(player_id);


--
-- Name: nflplayergamestat nflplayergamestat_stat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayergamestat
    ADD CONSTRAINT nflplayergamestat_stat_id_fkey FOREIGN KEY (stat_id) REFERENCES public.nflstat(stat_id);


--
-- Name: nflplayerseasonstat nflplayerseasonstat_player_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayerseasonstat
    ADD CONSTRAINT nflplayerseasonstat_player_id_fkey FOREIGN KEY (player_id) REFERENCES public.nflplayer(player_id);


--
-- Name: nflplayerseasonstat nflplayerseasonstat_stat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nflplayerseasonstat
    ADD CONSTRAINT nflplayerseasonstat_stat_id_fkey FOREIGN KEY (stat_id) REFERENCES public.nflstat(stat_id);


--
-- PostgreSQL database dump complete
--

\unrestrict DwtSaVAd78z8GYAi6KNV6Qxamqtc4dNQ2ed71CGY5ygl8pwnh9pCoaFUxEjFtue

