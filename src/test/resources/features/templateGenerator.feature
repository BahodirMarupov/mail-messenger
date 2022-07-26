Feature: feature to test template generator
  Scenario: Check template generator
    Given call prepareMessage with 'Hello #{name}, please check following link out: #{link}'
    Then should return message 'Hello Liam, please check following link out: https://www.test.com/'

