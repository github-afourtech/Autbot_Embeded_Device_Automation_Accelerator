Feature: Lock/Unlock phone

Scenario: Unlock Phone

	Given press key "phone_unlock" for "0.25" seconds
	Given wait for "2" seconds
    Given press key "phone_unlock" for "0.10" seconds 
    Given press key "phone_star" for "0.10" seconds
    Given wait for "3" seconds
    
    #menu 4 times
    Given press key "phone_back" for "0.5" seconds
    Given press key "navigate_up" for "0.5" seconds
    Given press key "phone_menu_ok" for "0.5" seconds
    Given press key "phone_menu_ok" for "0.5" seconds
    Given press key "phone_menu_ok" for "0.5" seconds
    Given press key "phone_menu_ok" for "0.5" seconds
    ##Given Press key "phone_menu_ok" for "1" seconds and "4" times
    
    #write name
    Given Write text "geek" on screen
    
    #confirm
    Given press key "phone_unlock" for "0.25" seconds
    Given press key "phone_menu_ok" for "0.25" seconds
    
    #choose number to enter
    Given press key "navigate_down" for "0.5" seconds
    Given press key "phone_menu_ok" for "0.25" seconds
    Given press key "phone_menu_ok" for "0.25" seconds
    
    #write number 5555
    Given press key "numeric_five" for "0.25" seconds
    Given press key "numeric_five" for "0.25" seconds
    Given press key "numeric_five" for "0.25" seconds
    Given press key "numeric_five" for "0.25" seconds
    
    #confirm
    Given press key "phone_unlock" for "0.25" seconds
    Given press key "phone_menu_ok" for "0.25" seconds
    
    #//menu->save
    Given press key "phone_unlock" for "0.25" seconds
    Given press key "navigate_down" for "0.5" seconds
    Given press key "phone_menu_ok" for "0.25" seconds
    
    #//navigate to main screen
    Given press key "phone_back" for "0.5" seconds
    Given press key "phone_back" for "0.5" seconds
            
    #capture image #Pending
    Given capture and save live image
        
    #//verify name available(OCR part)
    Given verify text "Geek" on screen "ContactScreen" with section "FirstContact"
    
    #navigate to main screen
    Given press key "phone_back" for "0.5" seconds
        