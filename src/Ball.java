public class Ball extends Sprite {
  private int radius;

  /**
   * Construct a Ball.
   *
   * @param x coordinate of the top left corner.
   * @param y coordinate of the top left corner.
   */
  public Ball(int x, int y) {
    super(x, y,
            GameConstant.BALL_RADIUS,
            GameConstant.BALL_RADIUS,
            GameConstant.BALL_COLOR
    );
    this.radius = GameConstant.BALL_RADIUS;
    super.setSpeed(GameConstant.BALL_SPEED, -GameConstant.BALL_SPEED);
  }

  @Override
  public void draw() {
    StdDraw.setPenColor(color);
    double halfRadius = radius / 2.0;
    double centerX = topLeftX + halfRadius;
    double centerY = topLeftY - halfRadius;
    StdDraw.filledCircle(centerX, centerY, halfRadius);
  }
  /**
   * Bounce off the edges of canvas.
   */

  public void bounce() {
    if (left() < 0 || right() > GameConstant.CANVAS_WIDTH) {
      // bouncing off the lest/right edges
      deltaX *= -1; // switches direction on x-axis
    }
    if (top() > GameConstant.CANVAS_HEIGHT) {
      // bounce off the top/bottom edges
      deltaY *= -1; // switches direction on y-axis
    }
  }

  /**
   * Ball gets deflected when collides with the paddle.
   * @param paddle the Paddle.
   */

  public void collide(Paddle paddle) {
    if (this.left() <= paddle.right()
            && this.right() >= paddle.left()
            && paddle.top() >= this.bottom()) {
      deltaY *= -1; // switches direction on y-axis
    }
  }
  
  /**
   * Ball gets deflected when collides with the brick.
   * @param brick the Brick.
   */

  public void collide(Brick brick) {
    if (this.left() <= brick.right()
            && this.right() >= brick.left()
            && brick.bottom() < this.top()
            && !brick.isBroken()) {
      deltaY *= -1; // switches direction on y-axis
      brick.breakIt();
    }
  }

  /**
   * Ball gets deflected when collides with obstacle from bottom, only when ball is moving upwards.
   * @param obstacle the Obstacle.
   */

  public void collide(Obstacle obstacle) {
    if (deltaY > 0 // Deflects ball when it collides with obstacle from bottom only when ball is moving upwards
            && this.left() <= obstacle.right()
            && this.right() >= obstacle.left()
            && this.top() == obstacle.bottom()) {
      deltaY *= -1; // switches direction on y-axis
    }
  }
}