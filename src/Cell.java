
public class Cell {

    // Walls are true when they exist
    private boolean topWall = true;
    private boolean bottomWall = true;
    private boolean leftWall = true;
    private boolean rightWall = true;

    // Has this cell been visited?
    private boolean visited = false;

    // Position in the maze
    private int row;
    private int col;

    // Constructor
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Getters
    public boolean hasTopWall() {
        return topWall;
    }

    public boolean hasBottomWall() {
        return bottomWall;
    }

    public boolean hasLeftWall() {
        return leftWall;
    }

    public boolean hasRightWall() {
        return rightWall;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Setters
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // Remove walls
    public void removeTopWall() {
        topWall = false;
    }

    public void removeBottomWall() {
        bottomWall = false;
    }

    public void removeLeftWall() {
        leftWall = false;
    }

    public void removeRightWall() {
        rightWall = false;
    }
}
