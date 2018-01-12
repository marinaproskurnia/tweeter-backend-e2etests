Feature: Positive scenarios for Tweeter backend

  @a
  Scenario Outline: The new user creates a post
    When the new user provides his "<name>" and posts some "<text>"


    Examples:
    | name | text        |
    | Adam | Adam's text |


