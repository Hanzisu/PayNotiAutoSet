@tagToSetting
Feature: Setting
	
 Scenario: 네이버페이 비밀번호 등록 - 변경
    Given 베타 설정 페이지에 접근한다.
    When 비밀번호 변경 버튼 클릭한다.
    And 네이버페이 비밀번호 입력창인지 확인한 후 비밀번호를 입력한다.
    Then '네이버페이 비밀번호 변경' me 알림을 확인한다.
