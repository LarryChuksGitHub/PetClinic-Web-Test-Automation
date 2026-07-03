Feature: Kindergeldantrag ab 18 via Formular

  Als Bürger
  möchte ich Kindergeldantrag für mein volljähriges Kind via formular stellen

  Background:
    Given Die Nutzer ist registriert und eingeloggt für Formular

  #@happyPath

  Scenario: Kindergeldantrag ab 18 Formular erfolgreich ausfüllen
    And Der Nutzer befindet sich im Formular für Kindergeld ab 18
    When Der Nutzer das Formular erfolgreich ausfüllt
    Then Absend Button wird angezeigt
    When Der Nutzer das Button send klickt
    Then Wird das Formular erfolgreich abgesendet
    And Die Bestätigung für eine erfolgreiche Absendung wird angezeigt

