Feature: eWA via Chat

  Als Bürger
  möchte ich meine Wohnung anmelden via chat

  Background:
    Given Die Nutzer ist registriert und eingeloggt für eWA

  #@happyPath

  Scenario: eWA Chat erfolgreich starten
    And Der Nutzer befindet sich im Chat für Wohnen und Umzug
    And Es wird nach Datenübernahme für eWA gefragt
    When Der Nutzer die Datenübernahme für eWA bestätigt
    And Wird die Nutzerdatenübernahme für eWA erfolgreich durchgeführt
    And eWA Chat Screen hat die richtige Inhalte
    When Der Nutzer eine eWA Frage "Ich möchte meine Wohnung anmelden" im Textfield eingibt
    Then Die Antwort enthält eWA Frage

