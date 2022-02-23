Feature: Cell configurations that are static
  In order to create a functioning rules engine
  As a programmer of Conway's Game of Life
  I can see static structures surviving in my world

  Scenario: Block
    Given the following setup:
    """
    .....
    .**..
    .**..
    .....
    .....
    """
    When I evolve the board
    Then I should see the following board:
    """
    .....
    .**..
    .**..
    .....
    .....
    """
  Scenario: Beehive
    Given the following setup:
    """
    ......
    ..**..
    .*..*.
    ..**..
    ......
    """
    When I evolve the board
    Then I should see the following board:
    """
    ......
    ..**..
    .*..*.
    ..**..
    ......
    """
  Scenario: Loaf
    Given the following setup:
    """
    ......
    ..**..
    .*..*.
    ..*.*.
    ...*..
    ......
    """
    When I evolve the board
    Then I should see the following board:
    """
    ......
    ..**..
    .*..*.
    ..*.*.
    ...*..
    ......
    """

  Scenario: Boat
    Given the following setup:
    """
    .....
    .**..
    .*.*.
    ..*..
    .....
    """
    When I evolve the board
    Then I should see the following board:
    """
    .....
    .**..
    .*.*.
    ..*..
    .....
    """