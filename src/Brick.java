public class Brick extends Block {
  private boolean broken;


  /**
   * Construct a Brick.
   *
   * @param x coordinate of the top left corner.
   * @param y coordinate of the top left corner.
   */
  public Brick(int x, int y) {
    super(x, y,
            GameConstant.BRICK_WIDTH,
            GameConstant.BRICK_HEIGHT,
            GameConstant.BRICK_COLOR
    );
    this.broken = false;
  }

  @Override
  public void draw() {
    if (broken) {
      return;
    }
    StdDraw.setPenColor(color);
    double halfWidth = width / 2.0;
    double halfHeight = height / 2.0;
    double centerX = topLeftX + halfWidth;
    double centerY = topLeftY - halfHeight;
    StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
  }

  /**
   * Break this brick.
   */
  public void breakIt() {
    broken = true;
  }

  /**
   * Check is this Brick is broken.
   * @return true if this Brick is broken, and false otherwise.
   */
  public boolean isBroken() {
    return broken;
  }
}