<?php
class Rating
{
    private $id_user;
    private $id_post;
    private $score;
    

    public function __construct(
        string $id_user,
        string $id_post,
        int $score
    ){
        $this->id_user = $id_user;
        $this->id_post = $id_post;
        $this->score = $score;
    }


    // Getter and Setter for id_post
    public function getIdPost(): string {
        return $this->id_post;
    }

    public function setIdPost(string $id_post): void {
        $this->id_post = $id_post;
    }

        // Getter and Setter for id_user
    public function getIdUser(): string {
        return $this->id_user;
    }

    public function setIdUser(string $id_user): void {
        $this->id_user = $id_user;
    }

    // Getter and Setter for score
    public function getScore(): int {
        return $this->score;
    }

    public function setScore(int $score): void {
        $this->score = $score;
    }

}


?>