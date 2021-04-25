package sgc;

/**
 * Class to create and manage edge objects
 */
public class Edge {
    //Coordinate values
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    /**
     * Constructor for an Edge object
     * @param startX : x coordinate of the start of the line
     * @param startY : y coordinate of the start of the line
     * @param endX : end x coordinate
     * @param endY : end y coordinate
     */
    public Edge(int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * Getters to get the coordinate values of the edges
     */
    public int getStartY(){
        return startY;
    }
    public int getStartX(){
        return startX;
    }
    public int getEndX() {
        return endX;
    }
    public int getEndY(){
        return endY;
    }
}