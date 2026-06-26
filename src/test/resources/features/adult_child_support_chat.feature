Feature: Kindergeldantrag ab 18 via Chat

  Als Bürger
  möchte ich Kindergeldantrag für mein volljähriges Kind via chat stellen

  Background:
    Given Die Nutzer ist registriert und eingeloggt

  #@happyPath

  Scenario: Kindergeldantrag ab 18 Chat erfolgreich starten
    And Der Nutzer befindet sich im Chat für Kindergeld ab 18
    And Wird nach Datenübernahme gefragt
    When Der Nutzer die Datenübernahme bestätigt
    And Wird die Nutzerdatenübernahme erfolgreich durchgeführt
    When Der Nutzer eine Frage "Ich möchte Kindergeld ab 18 beantragen" im Textfield eingibt
    Then Die Antwort des KI-Bürgerservice wird erfolgreich angezeigt
    And Die Antwort enthält die Frage "hat das Kind eine abgeschlossene Erstausbildung? Bitte wählen Sie ja oder nein"

