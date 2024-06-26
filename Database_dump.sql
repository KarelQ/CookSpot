--
-- PostgreSQL database cluster dump
--

-- Started on 2024-06-16 00:25:34 UTC

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE docker;
ALTER ROLE docker WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:3MjLydr1zuDoVRPGm2FaRw==$Bo1Dgo+MSx3WPafCuiyYo0Hm35pVDpbVXnQOJHWceo8=:dldCUi8XdgBWBsgDkhMMYhkjyAFc1iW6vw4xgQ3frN0=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.2

-- Started on 2024-06-16 00:25:34 UTC

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

-- Completed on 2024-06-16 00:25:34 UTC

--
-- PostgreSQL database dump complete
--

--
-- Database "db" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.2

-- Started on 2024-06-16 00:25:34 UTC

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
-- TOC entry 3429 (class 1262 OID 16384)
-- Name: db; Type: DATABASE; Schema: -; Owner: docker
--

CREATE DATABASE db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE db OWNER TO docker;

\connect db

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
-- TOC entry 227 (class 1255 OID 33637)
-- Name: set_log_date(); Type: FUNCTION; Schema: public; Owner: docker
--

CREATE FUNCTION public.set_log_date() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.log_date := CURRENT_DATE;
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.set_log_date() OWNER TO docker;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 25556)
-- Name: bookmarks; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.bookmarks (
    id_user character varying(30) NOT NULL,
    id_post character varying(30) NOT NULL
);


ALTER TABLE public.bookmarks OWNER TO docker;

--
-- TOC entry 221 (class 1259 OID 25560)
-- Name: categories; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.categories (
    id_category integer NOT NULL,
    category_name character varying(255),
    category_desc character varying(255)
);


ALTER TABLE public.categories OWNER TO docker;

--
-- TOC entry 220 (class 1259 OID 25559)
-- Name: categories_id_category_seq; Type: SEQUENCE; Schema: public; Owner: docker
--

CREATE SEQUENCE public.categories_id_category_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categories_id_category_seq OWNER TO docker;

--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 220
-- Name: categories_id_category_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: docker
--

ALTER SEQUENCE public.categories_id_category_seq OWNED BY public.categories.id_category;


--
-- TOC entry 224 (class 1259 OID 33634)
-- Name: logs; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.logs (
    id_user character varying(30),
    log_date date
);


ALTER TABLE public.logs OWNER TO docker;

--
-- TOC entry 222 (class 1259 OID 25568)
-- Name: post_categories; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.post_categories (
    id_post character varying(30) NOT NULL,
    id_category integer NOT NULL
);


ALTER TABLE public.post_categories OWNER TO docker;

--
-- TOC entry 217 (class 1259 OID 25543)
-- Name: posts; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.posts (
    id_post character varying(30) NOT NULL,
    id_user_owner character varying(30) NOT NULL,
    title character varying(255) NOT NULL,
    description text NOT NULL,
    ingredients text NOT NULL,
    recipe text NOT NULL,
    image character varying(255) NOT NULL,
    prep_time character varying(10) NOT NULL,
    difficulty character varying(10) NOT NULL,
    number_of_servings integer NOT NULL,
    created_at character varying(20) NOT NULL,
    "like" integer DEFAULT 0 NOT NULL,
    dislike integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.posts OWNER TO docker;

--
-- TOC entry 218 (class 1259 OID 25552)
-- Name: rating; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.rating (
    id_user character varying(30) NOT NULL,
    id_post character varying(30) NOT NULL,
    score integer NOT NULL,
    CONSTRAINT rating_score_check CHECK (((score >= '-1'::integer) AND (score <= 1)))
);


ALTER TABLE public.rating OWNER TO docker;

--
-- TOC entry 223 (class 1259 OID 25571)
-- Name: roles; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.roles (
    id_role integer NOT NULL,
    role_desc character varying(20) NOT NULL
);


ALTER TABLE public.roles OWNER TO docker;

--
-- TOC entry 215 (class 1259 OID 25532)
-- Name: users; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.users (
    id_user character varying(30) NOT NULL,
    id_role integer DEFAULT 1 NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    username character varying(50) NOT NULL
);


ALTER TABLE public.users OWNER TO docker;

--
-- TOC entry 225 (class 1259 OID 33639)
-- Name: user_logs; Type: VIEW; Schema: public; Owner: docker
--

CREATE VIEW public.user_logs AS
 SELECT logs.id_user,
    users.username,
    users.email,
    logs.log_date
   FROM (public.logs
     JOIN public.users ON (((logs.id_user)::text = (users.id_user)::text)));


ALTER VIEW public.user_logs OWNER TO docker;

--
-- TOC entry 216 (class 1259 OID 25538)
-- Name: users_details; Type: TABLE; Schema: public; Owner: docker
--

CREATE TABLE public.users_details (
    id_users_details character varying(30) NOT NULL,
    id_user character varying(30) NOT NULL,
    first_name character varying(30),
    last_name character varying(50),
    city character varying(50),
    street_name character varying(50),
    street_address character varying(20),
    postal_code character varying(20),
    state character varying(50),
    country character varying(50)
);


ALTER TABLE public.users_details OWNER TO docker;

--
-- TOC entry 226 (class 1259 OID 33643)
-- Name: users_with_details; Type: VIEW; Schema: public; Owner: docker
--

CREATE VIEW public.users_with_details AS
 SELECT u.id_user,
    u.id_role,
    u.email,
    u.password,
    u.username,
    ud.id_users_details,
    ud.first_name,
    ud.last_name,
    ud.city,
    ud.street_name,
    ud.street_address,
    ud.postal_code,
    ud.state,
    ud.country
   FROM (public.users u
     JOIN public.users_details ud ON (((u.id_user)::text = (ud.id_user)::text)));


ALTER VIEW public.users_with_details OWNER TO docker;

--
-- TOC entry 3247 (class 2604 OID 25563)
-- Name: categories id_category; Type: DEFAULT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.categories ALTER COLUMN id_category SET DEFAULT nextval('public.categories_id_category_seq'::regclass);


--
-- TOC entry 3418 (class 0 OID 25556)
-- Dependencies: 219
-- Data for Name: bookmarks; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.bookmarks (id_user, id_post) FROM stdin;
\.


--
-- TOC entry 3420 (class 0 OID 25560)
-- Dependencies: 221
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.categories (id_category, category_name, category_desc) FROM stdin;
1	Appetizers & Snacks	Small bites and finger foods perfect for starting a meal or enjoying on their own.
2	Breakfast & Brunch	Morning delights ranging from hearty breakfast classics to light brunch options.
3	Salads & Dressings	Fresh and vibrant salads paired with homemade dressings for a healthy meal or side.
4	Soups & Stews	Comforting bowls of warmth, ideal for any season, packed with flavor and wholesome ingredients.
5	Main Courses (Meat)	Hearty meat-centric dishes that serve as the focal point of a meal, satisfying and savory.
6	Main Courses (Vegetarian/Vegan)	Plant-based main dishes showcasing the versatility and deliciousness of vegetables, legumes, and grains.
7	Pasta & Noodles	Classic pasta dishes and noodle-based recipes from around the world, offering comfort in every bite.
8	Rice & Grains	Nutritious and filling recipes featuring rice, quinoa, barley, and other grains as the star ingredient.
9	Seafood	Fresh and flavorful seafood recipes celebrating the bounty of the ocean, from fish to shellfish.
\.


--
-- TOC entry 3423 (class 0 OID 33634)
-- Dependencies: 224
-- Data for Name: logs; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.logs (id_user, log_date) FROM stdin;
666b8d092770c2.98573332	2024-06-15
666b8d092770c2.98573332	2024-06-15
666b8d092770c2.98573332	2024-06-15
666e1bbb9e7401.42585095	2024-06-15
666b8d092770c2.98573332	2024-06-15
666e1bbb9e7401.42585095	2024-06-15
\.


--
-- TOC entry 3421 (class 0 OID 25568)
-- Dependencies: 222
-- Data for Name: post_categories; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.post_categories (id_post, id_category) FROM stdin;
666e20799799e2.62953883	5
666e20799799e2.62953883	7
666e2187e5ef60.76685701	5
666e2187e5ef60.76685701	8
666e23d370c238.15772288	5
666e23d370c238.15772288	7
666e25492a3695.17767716	7
666e25492a3695.17767716	9
666e25bed83659.08403124	6
\.


--
-- TOC entry 3416 (class 0 OID 25543)
-- Dependencies: 217
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.posts (id_post, id_user_owner, title, description, ingredients, recipe, image, prep_time, difficulty, number_of_servings, created_at, "like", dislike) FROM stdin;
666e2187e5ef60.76685701	666e1bbb9e7401.42585095	Chicken Tikka Masala	A popular Indian dish featuring marinated chicken cooked in a creamy, spiced tomato sauce. Perfectly paired with rice or naan.	    500g boneless, skinless chicken thighs, cut into bite-sized pieces\r\n    1 cup plain yogurt\r\n    2 tbsp lemon juice\r\n    1 tbsp grated ginger\r\n    3 cloves garlic, minced\r\n    1 tsp ground cumin\r\n    1 tsp ground coriander\r\n    1 tsp ground turmeric\r\n    1 tsp garam masala\r\n    1 tsp paprika\r\n    1 tsp salt\r\n\r\nFor the Sauce:\r\n\r\n    2 tbsp vegetable oil\r\n    1 large onion, finely chopped\r\n    3 cloves garlic, minced\r\n    1 tbsp grated ginger\r\n    1 tsp ground cumin\r\n    1 tsp ground coriander\r\n    1 tsp ground turmeric\r\n    1 tsp garam masala\r\n    1 tsp paprika\r\n    1 tsp cayenne pepper (optional, for extra heat)\r\n    400g can crushed tomatoes\r\n    1 cup heavy cream\r\n    Fresh cilantro, chopped (for garnish)	    Marinate the Chicken:\r\n        In a large bowl, combine the yogurt, lemon juice, ginger, garlic, cumin, coriander, turmeric, garam masala, paprika, and salt.\r\n        Add the chicken pieces, stirring to coat them well.\r\n        Cover and refrigerate for at least 1 hour, or overnight for best results.\r\n\r\n    Cook the Chicken:\r\n        Preheat your oven to 200°C (400°F).\r\n        Arrange the marinated chicken pieces on a baking sheet.\r\n        Bake for 15-20 minutes, or until cooked through. Remove from oven and set aside.\r\n\r\n    Prepare the Sauce:\r\n        Heat the vegetable oil in a large skillet over medium heat.\r\n        Add the chopped onion and cook until soft and golden, about 5 minutes.\r\n        Stir in the garlic and ginger, cooking for another 1-2 minutes.\r\n        Add the ground cumin, coriander, turmeric, garam masala, paprika, and cayenne pepper (if using). Cook for 1 minute until fragrant.\r\n\r\n    Make the Masala:\r\n        Pour in the crushed tomatoes, stirring to combine.\r\n        Simmer for 10 minutes, allowing the flavors to meld.\r\n        Stir in the heavy cream, bringing the sauce to a gentle simmer.\r\n\r\n    Combine and Serve:\r\n        Add the cooked chicken to the sauce, stirring to coat the pieces thoroughly.\r\n        Simmer for another 5-10 minutes, ensuring the chicken is heated through and the sauce has thickened.\r\n        Garnish with fresh cilantro.\r\n        Serve hot with rice or naan.\r\n\r\nEnjoy your flavorful and creamy Chicken Tikka Masala!	666e2187e1b1f5.73468160.jpg	45min	medium	6	16-06-2024	100	2
666e20799799e2.62953883	666e1bbb9e7401.42585095	Spaghetti Carbonara	A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper. It's creamy, flavorful, and perfect for a quick, satisfying meal.	200g spaghetti\r\n100g pancetta, diced\r\n2 large eggs\r\n50g Pecorino Romano cheese, grated\r\n50g Parmesan cheese, grated\r\n2 cloves garlic, minced\r\nFreshly ground black pepper\r\nSalt\r\nFresh parsley, chopped (optional)	Cook the Spaghetti:\r\n\r\n    Bring a large pot of salted water to a boil.\r\n    Add the spaghetti and cook according to package instructions until al dente.\r\n    Reserve 1 cup of pasta water, then drain the spaghetti.\r\n\r\nPrepare the Pancetta:\r\n\r\n    While the pasta is cooking, heat a large skillet over medium heat.\r\n    Add the diced pancetta and cook until crispy, about 5-7 minutes.\r\n    Add the minced garlic to the skillet and sauté for 1-2 minutes until fragrant. Remove from heat.\r\n\r\nMix the Eggs and Cheese:\r\n\r\n    In a medium bowl, whisk together the eggs, Pecorino Romano, and Parmesan until well combined.\r\n\r\nCombine the Ingredients:\r\n\r\n    Add the cooked spaghetti to the skillet with the pancetta and garlic. Toss to combine.\r\n    Remove the skillet from the heat.\r\n    Quickly pour the egg and cheese mixture over the hot pasta, stirring continuously to create a creamy sauce. If the sauce is too thick, add a bit of the reserved pasta water until the desired consistency is reached.\r\n\r\nSeason and Serve:\r\n\r\n    Season with freshly ground black pepper and salt to taste.\r\n    Garnish with chopped fresh parsley if desired.\r\n    Serve immediately and enjoy your delicious spaghetti carbonara!	666e207993fd09.20886036.jpg	30min	easy	4	16-06-2024	10	1
666e23d370c238.15772288	666e1bbb9e7401.42585095	Classic Beef Stroganoff	A rich and creamy Russian dish featuring tender beef strips cooked in a savory mushroom and sour cream sauce, served over egg noodles.	500g beef sirloin or tenderloin, thinly sliced\r\n2 tbsp olive oil\r\n1 large onion, finely chopped\r\n2 cloves garlic, minced\r\n250g mushrooms, sliced\r\n1 cup beef broth\r\n1 tbsp Dijon mustard\r\n1 tbsp Worcestershire sauce\r\n1 cup sour cream\r\n1 tbsp flour (optional, for thickening)\r\nSalt and pepper to taste\r\nFresh parsley, chopped (for garnish)\r\n300g egg noodles	    Cook the Beef:\r\n        Heat 1 tbsp of olive oil in a large skillet over medium-high heat.\r\n        Season the beef slices with salt and pepper.\r\n        Sear the beef in batches, cooking for 1-2 minutes on each side until browned. Remove from skillet and set aside.\r\n\r\n    Prepare the Vegetables:\r\n        In the same skillet, add the remaining 1 tbsp of olive oil.\r\n        Sauté the chopped onion until soft and translucent, about 5 minutes.\r\n        Add the minced garlic and cook for another 1-2 minutes.\r\n\r\n    Cook the Mushrooms:\r\n        Add the sliced mushrooms to the skillet.\r\n        Cook until they release their juices and start to brown, about 5-7 minutes.\r\n\r\n    Make the Sauce:\r\n        Pour in the beef broth, scraping up any browned bits from the bottom of the skillet.\r\n        Stir in the Dijon mustard and Worcestershire sauce.\r\n        Simmer for 5 minutes, allowing the flavors to meld.\r\n\r\n    Combine and Thicken:\r\n        If the sauce needs thickening, mix the flour with a little water to create a slurry and stir it into the sauce.\r\n        Add the sour cream, stirring until the sauce is creamy and smooth.\r\n        Return the cooked beef to the skillet, mixing it into the sauce. Simmer for another 5 minutes until the beef is heated through.\r\n\r\n    Cook the Noodles:\r\n        While the sauce simmers, cook the egg noodles according to package instructions.\r\n        Drain and set aside.\r\n\r\n    Serve:\r\n        Plate the cooked egg noodles.\r\n        Spoon the beef stroganoff over the noodles.\r\n        Garnish with chopped fresh parsley.\r\n\r\nEnjoy your hearty and comforting Classic Beef Stroganoff!	666e23d36b73f0.81294531.jpg	1,5h	hard	4	16-06-2024	50	1
666e25492a3695.17767716	666b8d092770c2.98573332	Lemon Garlic Shrimp Scampi	A light and zesty dish featuring shrimp sautéed in a lemon garlic butter sauce, served over linguine. Perfect for a quick and elegant meal.	400g linguine\r\n500g large shrimp, peeled and deveined\r\n4 tbsp butter\r\n2 tbsp olive oil\r\n4 cloves garlic, minced\r\n1/4 tsp red pepper flakes (optional)\r\n1/4 cup white wine (or chicken broth)\r\nJuice and zest of 1 lemon\r\n1/4 cup fresh parsley, chopped\r\nSalt and pepper to taste\r\nLemon wedges (for serving)	    Cook the Linguine:\r\n        Bring a large pot of salted water to a boil.\r\n        Add the linguine and cook according to package instructions until al dente.\r\n        Reserve 1/2 cup of pasta water, then drain the linguine and set aside.\r\n\r\n    Sauté the Shrimp:\r\n        While the pasta is cooking, heat 2 tbsp of butter and 1 tbsp of olive oil in a large skillet over medium-high heat.\r\n        Season the shrimp with salt and pepper.\r\n        Add the shrimp to the skillet and cook for 1-2 minutes on each side, until pink and opaque. Remove from the skillet and set aside.\r\n\r\n    Make the Sauce:\r\n        In the same skillet, add the remaining 2 tbsp of butter and 1 tbsp of olive oil.\r\n        Add the minced garlic and red pepper flakes (if using), sautéing for about 1 minute until fragrant.\r\n        Pour in the white wine (or chicken broth), lemon juice, and lemon zest. Simmer for 2-3 minutes, allowing the sauce to reduce slightly.\r\n\r\n    Combine and Serve:\r\n        Return the cooked shrimp to the skillet, tossing to coat in the sauce.\r\n        Add the cooked linguine to the skillet, tossing everything together. If the sauce is too thick, add a bit of the reserved pasta water until the desired consistency is reached.\r\n        Stir in the chopped fresh parsley and adjust seasoning with salt and pepper to taste.\r\n        Serve immediately with lemon wedges on the side.\r\n\r\nEnjoy your bright and flavorful Lemon Garlic Shrimp Scampi!	666e254925d730.61663565.jpg	20min	easy	3	16-06-2024	12	50
666e25bed83659.08403124	666b8d092770c2.98573332	Veggie Stir-Fry with Tofu	A colorful and nutritious stir-fry featuring tofu and a variety of vegetables in a savory soy-ginger sauce. Perfect for a quick and healthy weeknight dinner.	    400g firm tofu, cubed\r\n    2 tbsp vegetable oil, divided\r\n    1 red bell pepper, sliced\r\n    1 yellow bell pepper, sliced\r\n    1 large carrot, julienned\r\n    1 broccoli crown, cut into florets\r\n    1 cup snap peas\r\n    3 cloves garlic, minced\r\n    1 tbsp grated ginger\r\n    1/4 cup soy sauce\r\n    2 tbsp hoisin sauce\r\n    1 tbsp rice vinegar\r\n    1 tsp sesame oil\r\n    1 tbsp cornstarch mixed with 2 tbsp water\r\n    2 green onions, sliced\r\n    Sesame seeds (for garnish)\r\n    Cooked rice (for serving)\r\n	Prepare the Tofu:\r\n\r\n    Press the tofu to remove excess moisture, then cut it into 1-inch cubes.\r\n    Heat 1 tbsp of vegetable oil in a large skillet or wok over medium-high heat.\r\n    Add the tofu cubes and cook until golden and crispy on all sides, about 6-8 minutes. Remove from skillet and set aside.\r\n\r\nCook the Vegetables:\r\n\r\n    In the same skillet, add the remaining 1 tbsp of vegetable oil.\r\n    Add the garlic and ginger, sautéing for about 1 minute until fragrant.\r\n    Add the bell peppers, carrot, broccoli, and snap peas. Stir-fry for 5-7 minutes, until the vegetables are tender-crisp.\r\n\r\nMake the Sauce:\r\n\r\n    In a small bowl, whisk together the soy sauce, hoisin sauce, rice vinegar, and sesame oil.\r\n    Pour the sauce over the vegetables, stirring to coat.\r\n\r\nThicken the Sauce:\r\n\r\n    Add the cooked tofu back to the skillet.\r\n    Stir in the cornstarch mixture, cooking for another 1-2 minutes until the sauce has thickened and everything is well coated.\r\n\r\nServe:\r\n\r\n    Sprinkle the stir-fry with sliced green onions and sesame seeds.\r\n    Serve hot over cooked rice.	666e25bed49900.42934672.jpg	30min	easy	2	16-06-2024	15	50
\.


--
-- TOC entry 3417 (class 0 OID 25552)
-- Dependencies: 218
-- Data for Name: rating; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.rating (id_user, id_post, score) FROM stdin;
\.


--
-- TOC entry 3422 (class 0 OID 25571)
-- Dependencies: 223
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.roles (id_role, role_desc) FROM stdin;
1	user
0	admin
\.


--
-- TOC entry 3414 (class 0 OID 25532)
-- Dependencies: 215
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.users (id_user, id_role, email, password, username) FROM stdin;
666b8d092770c2.98573332	0	test@test.pl	$2y$10$myvdR9yjlHrt8WdIWVf2eOZ6KOn2iatgVqjZVDDroMCUu4X.CzNsi	Tester
666e1bbb9e7401.42585095	1	user1@email.com	$2y$10$Fl0Cvh/zWUgafk9aAfjFru1aWI18RefApwgTmRcna.x57XG3Sq1eq	User_1
\.


--
-- TOC entry 3415 (class 0 OID 25538)
-- Dependencies: 216
-- Data for Name: users_details; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.users_details (id_users_details, id_user, first_name, last_name, city, street_name, street_address, postal_code, state, country) FROM stdin;
\.


--
-- TOC entry 3431 (class 0 OID 0)
-- Dependencies: 220
-- Name: categories_id_category_seq; Type: SEQUENCE SET; Schema: public; Owner: docker
--

SELECT pg_catalog.setval('public.categories_id_category_seq', 20, true);


--
-- TOC entry 3256 (class 2606 OID 25567)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id_category);


--
-- TOC entry 3254 (class 2606 OID 25551)
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id_post);


--
-- TOC entry 3258 (class 2606 OID 25575)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id_role);


--
-- TOC entry 3252 (class 2606 OID 25542)
-- Name: users_details users_details_pkey; Type: CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.users_details
    ADD CONSTRAINT users_details_pkey PRIMARY KEY (id_users_details);


--
-- TOC entry 3250 (class 2606 OID 25537)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- TOC entry 3268 (class 2620 OID 33638)
-- Name: logs before_insert_logs; Type: TRIGGER; Schema: public; Owner: docker
--

CREATE TRIGGER before_insert_logs BEFORE INSERT ON public.logs FOR EACH ROW EXECUTE FUNCTION public.set_log_date();


--
-- TOC entry 3264 (class 2606 OID 25586)
-- Name: bookmarks bookmarks_id_post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmarks_id_post_fkey FOREIGN KEY (id_post) REFERENCES public.posts(id_post) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3265 (class 2606 OID 25581)
-- Name: bookmarks bookmarks_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmarks_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id_user) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3266 (class 2606 OID 25606)
-- Name: post_categories post_categories_id_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.post_categories
    ADD CONSTRAINT post_categories_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.categories(id_category) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3267 (class 2606 OID 25601)
-- Name: post_categories post_categories_id_post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.post_categories
    ADD CONSTRAINT post_categories_id_post_fkey FOREIGN KEY (id_post) REFERENCES public.posts(id_post) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3261 (class 2606 OID 25611)
-- Name: posts posts_id_user_owner_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_id_user_owner_fkey FOREIGN KEY (id_user_owner) REFERENCES public.users(id_user) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3262 (class 2606 OID 25596)
-- Name: rating rating_id_post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.rating
    ADD CONSTRAINT rating_id_post_fkey FOREIGN KEY (id_post) REFERENCES public.posts(id_post) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3263 (class 2606 OID 25591)
-- Name: rating rating_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.rating
    ADD CONSTRAINT rating_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id_user) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3260 (class 2606 OID 25616)
-- Name: users_details users_details_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.users_details
    ADD CONSTRAINT users_details_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id_user) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3259 (class 2606 OID 25576)
-- Name: users users_id_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: docker
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_role_fkey FOREIGN KEY (id_role) REFERENCES public.roles(id_role) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2024-06-16 00:25:34 UTC

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.2

-- Started on 2024-06-16 00:25:34 UTC

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

-- Completed on 2024-06-16 00:25:34 UTC

--
-- PostgreSQL database dump complete
--

-- Completed on 2024-06-16 00:25:34 UTC

--
-- PostgreSQL database cluster dump complete
--

