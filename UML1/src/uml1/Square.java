package uml1;

public class Square {
    private double side;

    public square() {
        this.side = 1;
    }

    public square(double s){
        this.side = s;
    }

    public void setSide(double s){
        this.side = s;
    }

    public double getSide(){
        return side;
    }

    public double getArea(){
        return side*side;
    }

    public double getPerimeter(){
        return 4*side;
    }

    public double getDiagonal(){
        return side * Math.sqrt(2);
    }

    
}
