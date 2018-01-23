Feature: Positive scenarios for Tweeter backend

  @a
  Scenario Outline: The new user creates a post
    Given the new user provides his <name>
    And the new user posts some <text>
    When the POST request is sent
    Then the status of the response should be 200

    Examples:
      | name | text                  |
      | Adam | Adam's text           |
      | Beth | Beth wrote more words |


#  Scenario Outline: Post deleting
#    Given the existing post with <id>
#    When the DELETE request is sent
#    Then the status of the response should be 200
#    And jljlj
#
#    Examples:
#      | id |
#      | 1  |
