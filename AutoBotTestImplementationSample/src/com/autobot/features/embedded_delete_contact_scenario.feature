Feature: Lock/Unlock phone

Scenario: Unlock Phone

	Given press key "phone_unlock" for "0.25" seconds
	Given wait for "2" seconds
    Given press key "phone_unlock" for "0.10" seconds 
    Given press key "phone_star" for "0.10" seconds
    Given wait for "3" seconds
    
    Given press key "phone_back" for "0.5" seconds
    	    
    Given press key "phone_menu_ok" for "0.5" seconds
    Given press key "navigate_down" for "0.5" seconds
    Given press key "navigate_down" for "0.5" seconds
    Given press key "navigate_down" for "0.5" seconds
   
    Given press key "phone_menu_ok" for "0.5" seconds
    Given press key "phone_menu_ok" for "0.5" seconds