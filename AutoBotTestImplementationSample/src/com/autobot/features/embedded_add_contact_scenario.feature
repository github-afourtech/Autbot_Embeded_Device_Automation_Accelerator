Feature: Lock/Unlock phone

Scenario: Unlock Phone

	Given I delete temparary OCR file
	Given press key "phone_unlock" for "0.25" seconds
	Given wait for "2" seconds
    Given press key "phone_unlock" for "0.10" seconds 
    Given press key "phone_star" for "0.10" seconds
    Given wait for "3" seconds
    
    #Navigate to contacts
    Given press key "phone_back" for "0.5" seconds
    
    #capture image #Pending
    Given capture and save live image
            
    #//verify name available(OCR part)
    Given verify text "Geek" on screen "ContactScreen" with section "FirstContact"
    
    #navigate to main screen
    Given press key "phone_back" for "0.5" seconds
        