public class Wall implements GameObject {
  private Brick[] bricks;

  /**
   * Construct a wall.
   * @param x the x coordinate of the top left corner.
   * @param y the y coordinate of the top left corner.
   */

  public Wall(int x, int y) {
    int count = 0;
    bricks = new Brick[GameConstant.BRICK_ROW_COUNT * GameConstant.BRICK_COLUMN_COUNT];
    for (int column = 0; column < GameConstant.BRICK_COLUMN_COUNT; column++) {
      for (int row = 0; row < GameConstant.BRICK_ROW_COUNT; row++) {
        int brickX = x + column * (GameConstant.BRICK_WIDTH + GameConstant.BRICK_SPACING);
        int brickY = y - row * (GameConstant.BRICK_HEIGHT + GameConstant.BRICK_SPACING);
        bricks[count++] = new Brick(brickX, brickY);
      }
    }
  }

  @Override
  public void draw() {
    for (Brick brick: bricks) {
      brick.draw();
    }
  }

  /**
   * Check if the ball collided with any of the bricks.
   * @param ball the Ball.
   */

  public void collide(Ball ball) {
    for (Brick brick: bricks) {
      ball.collide(brick);
    }
  }

  /**
   * Get the number of broken bricks.
   * @return the number of broken bricks.
   */
  public int getNumberOfBrokenBricks() {
    int count = 0;
    for (Brick brick: bricks) {
      if (brick.isBroken()) {
        count++;
      }
    }
    return count;
  }
}
