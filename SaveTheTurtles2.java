import processing.core.PApplet; //imported processor
import processing.core.PImage;
import java.util.Random;
import processing.core.PFont;
public class SaveTheTurtles2 extends PApplet {
    PImage beach;
    PImage recycle;
    PImage plastic;
    PImage plastic2;
    PImage plastic3;
    PImage turtle;
    PImage crab;
    PFont font1;
    PFont font2;
    PFont font3;
    PFont font4;

    int turtleX = 10;
    int turtleY = 210;

    public int startTime;
    public int timer;

    Random random = new Random();

    int startX = 100;
    int startY = 700;

    int obstacle1X = random.nextInt(901) + 100;             //spawns the plastic pieces in random locations within the screen
    int obstacle1Y = random.nextInt(501) + 250;

    int obstacle2X = random.nextInt(901) + 100;
    int obstacle2Y = random.nextInt(501) + 250;

    int obstacle3X = random.nextInt(901) + 100;
    int obstacle3Y = random.nextInt(501) + 250;

    int crab1X = 1000;
    int crab1Y = 475;

    boolean collision1 = true;
    boolean collision2 = true;
    boolean collision3 = true;

    boolean hitCrab = true;

    boolean gameover = false;

    public enum STATE{
        NEXT,
        OVER,
        HIT,
        L3,
    }

    public STATE State = STATE.L3;

    public static void main(String[] args) {
        PApplet.main("SaveTheTurtles2");
    }
    public void settings(){
        size (1100,850);
    }
    public void setup(){
        beach = loadImage("Images/beach3.png");          //beach background
        beach.resize(1100,850);
        background(beach);

        recycle = loadImage("Images/recycle.png");       //recycle bin

        plastic = loadImage("Images/plastic1.png");       //plastic pieces
        plastic2 = loadImage("Images/plastic2.jpg");
        plastic3 = loadImage("Images/plastic3.jpg");

        turtle = loadImage("Images/cuteturtle.png");         //turtle

        crab = loadImage("Images/angryCrab.png");

        font1 = createFont("Times New Roman", 30, true);
        font2 = createFont("Times New Roman", 10, true);
        font3 = createFont("Times New Roman", 40, true);
        font4 = createFont("Times New Roman", 35, true);

        startTime = millis();
    }
    public void draw() {
        if (State == STATE.L3) {

            if (timer % 1000 == 0) {                     //turtle timer -- after each  millisecond, the turtle will move right 1 pixel
                turtleX = turtleX + 1;
                background(beach);
                recycle.resize(70, 70);
                image(recycle, startX, startY);
                turtle.resize(100, 100);
                image(turtle, turtleX, turtleY);
                crab.resize(100,100);
                image(crab, crab1X, crab1Y);
            }

            if (crab1Y == 475){
                if (timer % 1000 == 0) {                     //crab timer
                    crab1X = crab1X - 5;
                    background(beach);
                    recycle.resize(70, 70);
                    image(recycle, startX, startY);
                    turtle.resize(100, 100);
                    image(turtle, turtleX, turtleY);
                    crab.resize(100,100);
                    image(crab, crab1X, crab1Y);
                }
            }
            else{
                if (timer % 1000 == 0) {
                    crab1X = crab1X + 5;
                    background(beach);
                    recycle.resize(70, 70);
                    image(recycle, startX, startY);
                    turtle.resize(100, 100);
                    image(turtle, turtleX, turtleY);
                    crab.resize(100,100);
                    image(crab, crab1X, crab1Y);
                }
            }
            if (crab1X == 100){
                crab1Y = 476;
            }else if(crab1X == 1000){
                crab1Y = 475;
            }

            if ((collision1 || collision2 || collision3) && turtleX > 1000) {     //Game Over
                State = STATE.OVER;
            } else if ((!collision1 && !collision2 && !collision3 && hitCrab) && turtleX < 1000) {
                State = STATE.NEXT;
            }

            if (!hitCrab) {     //Game Over
                State = STATE.HIT;
            }

            if (collision1 == true) {                                 //if plastic is untouched
                plastic.resize(90, 90);
                image(plastic, obstacle1X, obstacle1Y);
            }
            obstacleInteraction();

            if (collision2 == true) {
                plastic2.resize(90, 90);
                image(plastic, obstacle2X, obstacle2Y);
            }
            obstacleInteraction2();

            if (collision3 == true) {
                plastic3.resize(90, 90);
                image(plastic, obstacle3X, obstacle3Y);
            }
            obstacleInteraction3();



            if (hitCrab == true) {                                 //if crab is untouched
                crab.resize(100,100);
                image(crab, crab1X, crab1Y);
            }
            crabInteraction();
        }
        else if (State == STATE.NEXT){
            fill(191,191,191);
            rect(100,100, 900, 650);
            textFont(font1, 45);
            fill(0, 0, 0);
            text("Congratulations!!", 390, 400);
            text("You cleared the plastic and saved the turtles!!", 123, 450);

            rect(145,650, 360, 50);
            textFont(font2, 35);
            fill(255,255,255);
            text("Click Backspace to Exit", 155, 687);

            fill(0, 0, 0);
            rect(595,650, 360, 50);
            textFont(font2, 35);
            fill(255,255,255);
            text("Click Enter to Continue", 605, 687);
        }
        else if (State == STATE.OVER){
            fill(191,191,191);
            rect(100,100, 900, 650);
            textFont(font1, 50);
            fill(0, 0, 0);
            text("OH NO!! You didn't clear all the plastic!!", 123, 400);
            text("GAME OVER :(", 400, 450);

            rect(370,650, 360, 50);
            textFont(font2, 35);
            fill(255,255,255);
            text("Click Backspace to Exit", 380, 687);
        }
        else if (State == STATE.HIT){
            fill(191,191,191);
            rect(100,100, 900, 650);
            textFont(font1, 50);
            fill(0, 0, 0);
            text("OH NO!! You got pinched by a crab!!", 170, 400);
            text("GAME OVER :(", 400, 450);

            rect(370,650, 360, 50);
            textFont(font2, 35);
            fill(255,255,255);
            text("Click Backspace to Exit", 380, 687);
        }
    }
    public void keyPressed() {                                  //moving recycle bin
        if (State == STATE.L3) {
            if (keyCode == LEFT) {
                startX = startX - 10;
            }
            if (keyCode == RIGHT) {
                startX = startX + 10;
            }
            if (keyCode == UP) {
                startY = startY - 10;
            }
            if (keyCode == DOWN) {
                startY = startY + 10;
            }

        } else if (State == STATE.OVER) {
            if (keyCode == BACKSPACE) {
                exit();
            }
        } else if (State == STATE.NEXT) {
            if (keyCode == BACKSPACE) {
                exit();
            }
        } else if(State == STATE.HIT) {
            if (keyCode == BACKSPACE) {
                exit();
            }
        }
    }

    public void obstacleInteraction(){        //if plastic pieces are touched
        if(abs(startX - obstacle1X) < 70 && (abs(startY - obstacle1Y) < 70)){
            collision1 = false;
        }
    }
    public void obstacleInteraction2(){
        if(abs(startX - obstacle2X) < 70 && (abs(startY - obstacle2Y) < 70)){
            collision2 = false;
        }
    }
    public void obstacleInteraction3(){
        if(abs(startX - obstacle3X) < 70 && (abs(startY - obstacle3Y) < 70)){
            collision3 = false;
        }
    }

    public void crabInteraction(){        //if crab is touched
        if(abs(startX - crab1X) < 70 && (abs(startY - crab1Y) < 70)){
            hitCrab = false;
        }
    }
}