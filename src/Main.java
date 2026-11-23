import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Maze maze = new Maze(10, 10);

            MazeGenerator generator = new MazeGenerator(maze);

            try {
                generator.generateMaze();
            } catch (StackException e) {
                System.out.println(e.getMessage());
                return;
            }

            MazePanel panel = new MazePanel(maze);

            MazeSolver solver = new MazeSolver(maze);

            try {
             panel.setSolutionPath(solver.solveMaze());
                } catch (QueueException e) {
                 System.out.println(e.getMessage());
            }

            JFrame frame = new JFrame("Maze Generator and Solver");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 700);
            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}