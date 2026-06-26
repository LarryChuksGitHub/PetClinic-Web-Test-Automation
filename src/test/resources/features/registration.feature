Feature: Registrierung eines neuen Nutzers

  Scenario: Erfolgreiche Nutzer Registrierung mit Mock eID

    Given Die Deutschland App ist gestartet
    And Der Nutzer befindet sich im Onboarding und die Mock eiD aktiviert
    When Der Nutzer die Registrierung startet
    And Eine gültige E-Mail-Adresse eingibt
    And Das OTP erfolgreich bestätigt
    Then Wird die Registrierung erfolgreich abgeschlossen und ID Daten werden unter Nachweise angezeigt