Feature: eWA via Chat und Avatar

  Als Bürger
  möchte ich Wohnungsanmeldung  via chat und avatar starten

  Background:
    Given Die Nutzer ist registriert und eingeloggt für eWA

  #@happyPath

  Scenario: eWA Chat und Avatar starten
    And Der Nutzer befindet sich im Chat für Wohnen und Umzug
    And Es wird nach Datenübernahme für eWA gefragt
    When Der Nutzer die Datenübernahme für eWA bestätigt
    And Wird die Nutzerdatenübernahme für eWA erfolgreich durchgeführt
    And eWA Chat Screen hat die richtige Inhalte
    When Der Nutzer eine eWA Frage "Ich möchte meine Wohnung anmelden" im Textfield eingibt
    Then Die Antwort enthält eWA Frage
    And eWA Avatar is sichtbar
    And eWA Mikrofone und Anhang sind sichtbar

