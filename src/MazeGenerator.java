
import java.util.Random;

public class MazeGenerator {

    private Maze maze;
    private Random random;
    private ArrayStack<Cell> stack;

    public MazeGenerator(Maze maze) throws StackException {
        this.maze = maze;
        random = new Random();

        stack = new ArrayStack<>(
                maze.getRows() * maze.getCols()
        );

        // Start at the top-left cell
        Cell start = maze.getCell(0, 0);
        start.setVisited(true);
        stack.push(start);
    }

    // Performs one step of maze generation
    public void step() throws StackException {

        if (stack.isEmpty()) {
            return;
        }

        Cell current = stack.peek();

        Cell[] neighbours = new Cell[4];
        int count = 0;

        int row = current.getRow();
        int col = current.getCol();

        // Check the cell above
        if (row > 0 && !maze.getCell(row - 1, col).isVisited()) {
            neighbours[count] = maze.getCell(row - 1, col);
            count++;
        }

        // Check the cell below
        if (row < maze.getRows() - 1 &&
                !maze.getCell(row + 1, col).isVisited()) {
            neighbours[count] = maze.getCell(row + 1, col);
            count++;
        }

        // Check the cell to the left
        if (col > 0 && !maze.getCell(row, col - 1).isVisited()) {
            neighbours[count] = maze.getCell(row, col - 1);
            count++;
        }

        // Check the cell to the right
        if (col < maze.getCols() - 1 &&
                !maze.getCell(row, col + 1).isVisited()) {
            neighbours[count] = maze.getCell(row, col + 1);
            count++;
        }

        if (count > 0) {

            // Pick a random unvisited neighbour
            Cell next = neighbours[random.nextInt(count)];

            // Remove the wall between the two cells
            removeWalls(current, next);

            // Visit and push the new cell
            next.setVisited(true);
            stack.push(next);

        } else {

            // No unvisited neighbours, so backtrack
            stack.pop();
        }
    }

    // Checks whether generation has finished
    public boolean isFinished() {
        return stack.isEmpty();
    }

    // Allows the original full-generation method to still work
    public void generateMaze() throws StackException {
        while (!isFinished()) {
            step();
        }
    }

    private void removeWalls(Cell current, Cell next) {

        int rowDifference = current.getRow() - next.getRow();
        int colDifference = current.getCol() - next.getCol();

        if (rowDifference == 1) {
            current.removeTopWall();
            next.removeBottomWall();
        }
        else if (rowDifference == -1) {
            current.removeBottomWall();
            next.removeTopWall();
        }
        else if (colDifference == 1) {
            current.removeLeftWall();
            next.removeRightWall();
        }
        else if (colDifference == -1) {
            current.removeRightWall();
            next.removeLeftWall();
        }
    }
}

