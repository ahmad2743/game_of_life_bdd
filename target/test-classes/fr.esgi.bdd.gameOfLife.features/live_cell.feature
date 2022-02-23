Feature: Evolving a living cell
  In order to create a functioning rules engine
  As a programmer of Conway's Game of Life
  I can evolve a single living cell

  Scenario: Living cell with 0 neighbors dies
    Given the following setup:
      """
      ...
      .*.
      ...
      """
    When I evolve the board
    Then I should see the following board:
    """
    ...
    ...
    ...
    """

  Scenario: Living cell with 1 neighbor dies
    Given the following setup:
      """
      .*.
      .*.
      ...
      """
    When I evolve the board
    Then I should see the following board:
    """
    ...
    ...
    ...
    """

  Scenario: Living cell with 2 neighbors lives
    Given the following setup:
      """
      .*.
      .**
      ...
      """
    When I evolve the board
    Then I should see the following board:
    """
    .**
    .**
    ...
    """

  