## Aufgabe 1
### Usage
```bash
java -jar Aufgabe1.jar <path to cities>
```
### Analyse
Die Methode createGraphWithTiming() erstellt einen Graphen basierend auf den Städten, die in einer Datei angegeben sind. Die Laufzeit dieser Methode hängt von zwei Hauptoperationen ab:
* Dem Hinzufügen von Städten zum Graphen und,
* Der Berechnung der Distanzen zwischen allen Städten.
**Das Hinzufügen von Städten zum Graphen**: 
* Diese Operation ist linear, d.h. sie hat eine Laufzeit von O(n), wobei n die Anzahl der Städte ist. Jede Stadt wird einzeln gelesen und zum Graphen hinzugefügt.  
**Die Berechnung der Distanzen zwischen allen Städten**: 
* Diese Operation ist quadratisch, d.h. sie hat eine Laufzeit von O(n^2), da für jede Stadt die Distanz zu jeder anderen Stadt berechnet werden muss. In einem Graphen mit n Knoten (in diesem Fall Städten) gibt es n*(n-1)/2 Kanten (in diesem Fall Distanzen), die berechnet werden müssen. Das Einlesen von der Datei ist in O(n), weil wir nur eine Zeile mehr lesen müssen, wenn wir eine Stadt mehr haben. Die Methode calculateDistance ist in O(1) weil sie einfach nur arithmetische Operationen durchführt, die unabhängig von der Eingabegröße sind.

## Aufgabe 2
-
## Aufgabe 3
-