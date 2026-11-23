
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Maze maze = new Maze(10, 10);

            MazeGenerator generator;

            try {
                generator = new MazeGenerator(maze);
            } catch (StackException e) {
                System.out.println(e.getMessage());
                return;
            }

            MazePanel panel = new MazePanel(maze);

            JFrame frame = new JFrame("Maze Generator and Solver");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 700);
            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Timer for DFS maze generation
            Timer generationTimer = new Timer(50, event -> {

                try {
                    if (!generator.isFinished()) {

                        generator.step();
                        panel.repaint();

                    } else {

                        // Stop maze generation
                        ((Timer) event.getSource()).stop();

                        System.out.println("Maze generation complete!");

                        // Start the BFS solver
                        try {
                            MazeSolver solver = new MazeSolver(maze);

                            Timer solverTimer = new Timer(50, solverEvent -> {

                                try {
                                    if (!solver.isFinished()) {

                                        solver.step();

                                        // Display BFS exploration
                                        panel.setExploredCells(
                                                solver.getExploredCells()
                                        );

                                    } else {

                                        // Stop BFS animation
                                        ((Timer) solverEvent.getSource()).stop();

                                        // Display the final shortest path
                                        panel.setSolutionPath(
                                                solver.getSolutionPath()
                                        );

                                        System.out.println(
                                                "Maze solving complete!"
                                        );

                                        System.out.println(
                                                "Path length: " +
                                                solver.getSolutionPath().size()
                                        );
                                    }

                                } catch (QueueException e) {
                                    System.out.println(e.getMessage());
                                }
                            });

                            solverTimer.start();

                        } catch (QueueException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } catch (StackException e) {
                    System.out.println(e.getMessage());
                }
            });

            generationTimer.start();
        });
    }
}
