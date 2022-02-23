Feature: Evolving a grid with some cells in it
  In order to create a functioning rules engine
  As a programmer of Conway's Game of Life
  I can evolve a multiple cell grid

  Scenario: Sparse grid with nobody staying alive
    Given the following setup:
      """
      .....
      .*.*.
      .....
      .*.*.
      .....
      """
    When I evolve the board
    Then I should see the following board:
    """
    .....
    .....
    .....
    .....
    .....
    """
  Scenario: Over-crowded grid
    Given the following setup:
      """
      .....
      .***.
      .***.
      .***.
      .....
      """
    When I evolve the board
    Then I should see the following board:
      """
      ..*..
      .*.*.
      *...*
      .*.*.
      ..*..
      """
  Scenario: Multiple dead cells coming to life
    Given the following setup:
      """
      .....
      .....
      .***.
      .....
      .....
      """
    When I evolve the board
    Then I should see the following board:
    """
    .....
    ..*..
    ..*..
    ..*..
    .....
    """
