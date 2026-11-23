public class GeneratorTest {

    public static void main(String[] args) throws StackException {

        Maze maze = new Maze(10, 10);

        MazeGenerator generator = new MazeGenerator(maze);

        generator.generateMaze();

        int visitedCount = 0;

        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {

                if (maze.getCell(row, col).isVisited()) {
                    visitedCount++;
                }
            }
        }

        System.out.println("Maze generated!");
        System.out.println("Total cells: " +
                (maze.getRows() * maze.getCols()));
        System.out.println("Visited cells: " + visitedCount);
    }
}