import java.awt.Font;

public class BreakoutGame {
  private static Paddle paddle;
  private static Ball ball;
  private static Wall wall;
  private static Obstacle obstacle;

  /**
   * The program starts here.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    setupCanvas();
    setupGameObjects();

    int score = 0;
    boolean isGameOver = false;
    boolean won = false;
    while (!isGameOver && !won) {
      StdDraw.clear();

      drawGameObjects();
      moveGameObjects();
      handleCollisions();

      isGameOver = ball.bottom() <= 0;
      score = wall.getNumberOfBrokenBricks();
      won = score == GameConstant.BRICK_ROW_COUNT * GameConstant.BRICK_COLUMN_COUNT;
      displayScore(score);

      StdDraw.show();
      StdDraw.pause(GameConstant.FRAME_DELAY);
    }
    displayResult(won);
  }

  private static void handleCollisions() {
    ball.collide(paddle);
    ball.collide(obstacle);
    wall.collide(ball);
  }

  private static void moveGameObjects() {
    ball.move();
    ball.bounce();
    paddle.move();
    paddle.changeDirection();
    obstacle.move();
  }

  private static void drawGameObjects() {
    paddle.draw();
    obstacle.draw();
    wall.draw();
    ball.draw();
  }

  private static void setupGameObjects() {
    paddle = new Paddle(
            (GameConstant.CANVAS_WIDTH - GameConstant.PADDLE_WIDTH) / 2,
            GameConstant.PADDLE_OFFSET + GameConstant.PADDLE_HEIGHT
    );

    obstacle = new Obstacle(
            (GameConstant.CANVAS_WIDTH - GameConstant.OBSTACLE_WIDTH) / 2,
            GameConstant.OBSTACLE_OFFSET + GameConstant.OBSTACLE_HEIGHT
    );

    ball = new Ball(
            GameConstant.CANVAS_WIDTH  / 2,
            GameConstant.CANVAS_HEIGHT  / 2
    );

    wall = new Wall(130, GameConstant.CANVAS_HEIGHT - 60);
  }

  private static void displayScore(int score) {
    StdDraw.setPenColor(GameConstant.TEXT_COLOR);
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 24));
    StdDraw.textLeft(
            20,
            GameConstant.CANVAS_HEIGHT - 30,
            "Score: " + score
    );
  }

  private static void displayResult(boolean won) {
    StdDraw.clear();
    StdDraw.setPenColor(GameConstant.TEXT_COLOR);
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 48));
    StdDraw.text(
            GameConstant.CANVAS_WIDTH / 2.0,
            GameConstant.CANVAS_HEIGHT / 2.0,
            (won ? "You won!" : "You lost!")
    );
    StdDraw.show();
  }

  private static void setupCanvas() {
    StdDraw.setCanvasSize(GameConstant.CANVAS_WIDTH, GameConstant.CANVAS_HEIGHT);
    StdDraw.setXscale(0, GameConstant.CANVAS_WIDTH);
    StdDraw.setYscale(0, GameConstant.CANVAS_HEIGHT);
    StdDraw.enableDoubleBuffering();
  }
}