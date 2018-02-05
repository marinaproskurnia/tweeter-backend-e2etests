Feature: Positive scenarios for Tweeter backend

  Scenario Outline: The new user creates a post
    Given the new user provides his <name>
    And the new user posts some <text>
    When the POST request for the new user is sent
    Then the status of the response should be 200
    And the post record has been added to DB

    Examples:
      | name           | text                                                                    |
      | Adam           | Adam's text                                                             |
      | Mary Elizabeth | Mary Elizabeth wrote more words with some special characters: * & 123 $ |

  Scenario Outline: The new user creates a post and delete it
    Given the new user provides his <name>
    And the new user posts some <text>
    When the POST request for the new user is sent
    Then the status of the response should be 200
    And the post record has been added to DB
    Given the post id has been retrieved from DB
    When the DELETE request is sent
    Then the status of the response should be 200

    Examples:
      | name | text                  |
      | Beth | Beth wrote more words |

  Scenario Outline: The existing user creates a post
    Given the new user provides his <name>
    And the new user posts some <text>
    When the POST request for the new user is sent
    Then the status of the response should be 200
    And the post record has been added to DB

    Given the user id has been retrieved from DB
    And the user provides his <name>
    And the user posts some <new_text>
    When the POST request for the existing user is sent
    Then the status of the response should be 200
    And the post record has been added to DB

    Examples:
      | name | text                  | new_text                  |
      | Adam | Adam's text           | Adam made his second post |
      | Beth | Beth wrote more words | Beth made her second post |
