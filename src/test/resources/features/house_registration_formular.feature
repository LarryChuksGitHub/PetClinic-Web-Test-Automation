Feature: eWA via Formular

  Als Bürger
  möchte meine Wohnung anmelden via formular

  Background:
    Given Die Nutzer ist registriert und eingeloggt für eWA Formular

  #@happyPath

  Scenario: eWA Formular erfolgreich ausfüllen
    And Der Nutzer befindet sich im Formular für eWA
    When Der Nutzer das eWA Formular erfolgreich ausfüllt
    Then Absend Button für eWA wird angezeigt
    When Der Nutzer das Button für eWA senden klickt
    Then Wird das eWA Formular erfolgreich abgesendet
    And Die Bestätigung für eine erfolgreiche eWA Absendung  wird angezeigt
