<?php


require_once 'src/controllers/DefaultController.php';
require 'Routing.php';


$path = trim($_SERVER['REQUEST_URI'], '/');
$path = parse_url( $path, PHP_URL_PATH);

Routing::get('index', 'DefaultController');
Routing::get('login', 'DefaultController');
Routing::get('createaccount', 'DefaultController');

Routing::run($path);


