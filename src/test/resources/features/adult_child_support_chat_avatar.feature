Feature: Kindergeldantrag ab 18 via Chat und Avatar

  Als Bürger
  möchte ich Kindergeldantragstellung für mein volljähriges Kind via chat und avatar starten

  Background:
    Given Die Nutzer ist registriert und eingeloggt

  #@happyPath

  Scenario: Kindergeldantrag ab 18 via Chat und Avatar starten
    And Der Nutzer befindet sich im Chat für Kindergeld ab 18
    And Wird nach Datenübernahme gefragt
    When Der Nutzer die Datenübernahme bestätigt
    And Wird die Nutzerdatenübernahme erfolgreich durchgeführt
    When Der Nutzer eine Frage "Ich möchte Kindergeld ab 18 beantragen" im Textfield eingibt
    Then Die Antwort des KI-Bürgerservice wird erfolgreich angezeigt
    And Die Antwort enthält die Frage "hat das Kind eine abgeschlossene Erstausbildung? Bitte wählen Sie ja oder nein"
    And Kindergeld ab 18 Avatar is sichtbar
    And Kindergeld ab 18 Mikrofone und Anhang sind sichtar

