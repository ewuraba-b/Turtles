import processing.core.PApplet;//imported processor
import processing.core.PImage;

public class SaveTheTortugas extends PApplet {

    PImage beach;   


    public static void main(String[] args) {    //main method
        PApplet.main("SaveTheTortugas");
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        background(45, 177, 218);
        beach = loadImage("Images/beach.jpg");
    }

    public void draw() {
        fill(255, 204, 255);
        rect(350, 350, 100, 100);

        fill(205, 255, 230);
        rect(350, 175, 100, 100);

        image(beach,350, 525);
    }

    public void mousePressed() {
        button1();
        button2();
    }

    public void button1() {
        if (mouseX > 350 && mouseX < 450 &&
                mouseY > 350 && mouseY < 450) {
            System.out.println("Sup ;)");
        }
    }
    public void button2(){
        if(mouseX > 350 && mouseX < 450 &&
        mouseY > 175 && mouseY < 275){
            System.out.println("Hey Hey Hey!!!");
        }
    }
}

