Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario:  Milk production at given age in days
Given A LabYak
When the age of LabYak is 405 days
Then amount of milk produced should be correct

Scenario: Eligibiligy of a LabYak for shaving
Given A LabYak
When the age of LabYak is 423 days
And its age when last shaved was 403 days
Then it is again eligible for shaving today

Scenario: Check yields of many yaks after certain days
Given 3 Lab yaks
And number of days elapsed are 13
Then check total yield of milk and skins
