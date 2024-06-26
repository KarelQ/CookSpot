<?php
require_once __DIR__.'/../models/Category.php';
require_once __DIR__.'/../repository/CategoryRepository.php';
require_once __DIR__.'/../repository/PostRepository.php';

class CategoryController extends AppController{

private $categoryRepository;
private $postRepository;

    public function __construct()
    {
        parent::__construct();
        $this->categoryRepository = new CategoryRepository();
        $this->postRepository = new PostRepository();
    }

    public function explore() {
        $categories = $this->categoryRepository->getCategoriesList();
        $this -> render('explore',['categories' => $categories] );
    }

    
    public function categorypage() {
        if ($this->isGet() && isset($_GET['id'])) {
            $id_category = $_GET['id'];  
            $posts = [];
            $posts_id = $this->categoryRepository->getPostsListFromIdCategory($id_category);
            
            if($posts_id == false){
                return $this -> render('mainpage',['posts' => $posts, 'message' => 'Ups... Look like it\'s nothing here '] );
            }
            
            foreach($posts_id as $id_post) {
                $posts[] = $this->postRepository->getPost($id_post);
            }

             return $this -> render('mainpage',['posts' => $posts] );
            
            
        } else {
            echo "Post ID is not specified.";
        }
    }

}