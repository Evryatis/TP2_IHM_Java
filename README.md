Une seule classe par projet hérite de Application

```java
public class Projet2Application extends Application {
    
    public void start (Stage stage) {
        stage.show();
    }
}
```

On met dans la classe Projet2Application la méthode main 

```java
public static void main (string[] args) { 
Application.launch(args);
}
```
Ce qui varie est la racine au dessus de la méthode main, dans la méthode start