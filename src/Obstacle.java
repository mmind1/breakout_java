public class Obstacle extends Sprite {

  /**
   * Construct an Obstacle.
   *
   * @param x coordinate of the top left corner.
   * @param y coordinate of the top left corner.
   */
  public Obstacle(int x, int y) {
    super(x, y,
            GameConstant.OBSTACLE_WIDTH,
            GameConstant.OBSTACLE_HEIGHT,
            GameConstant.OBSTACLE_COLOR
    );
    super.setSpeed(GameConstant.OBSTACLE_SPEED,0);
  }

  @Override
  public void draw() {
    StdDraw.setPenColor(color);
    double halfWidth = width / 2.0;
    double halfHeight = height / 2.0;
    double centerX = topLeftX + halfWidth;
    double centerY = topLeftY - halfHeight;
    StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
  }

  @Override
  public void move() {
    super.move();
    if (left() < 0) {
      deltaX = GameConstant.OBSTACLE_SPEED;
    } else if (right() > GameConstant.CANVAS_WIDTH) {
      deltaX = -GameConstant.OBSTACLE_SPEED;
    }
  }
}
