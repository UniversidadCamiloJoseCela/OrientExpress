# OrientExpress

Este documento ofrece una descripción en profundidad del proyecto **OrientExpress**, su arquitectura, el módulo de diálogos y el sistema de generación dinámica de vagones.

---
## Índice
1. [Visión General del Proyecto](#visión-general-del-proyecto)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Módulo de Diálogos](#módulo-de-diálogos)
   - 3.1 [Clases Principales](#clases-principales)
   - 3.2 [Flujo de Ejecución de Diálogo](#flujo-de-ejecución-de-diálogo)
   - 3.3 [Definición de Escenas](#definición-de-escenas)
4. [Generación de Vagones (Carriages)](#generación-de-vagones-carriages)
   - 4.1 [CarriageType: Tipos de Vagón](#carriagetype-tipos-de-vagón)
   - 4.2 [CarriageLayout: Diseño Interno](#carriagelayout-diseño-interno)
   - 4.3 [CarriageBuilder: Patrón Builder](#carriagebuilder-patrón-builder)
   - 4.4 [Ensamblaje del Tren](#ensamblaje-del-tren)
   - 4.5 [Navegación entre Vagones](#navegación-entre-vagones)
5. [Internacionalización (i18n)](#internacionalización-i18n)
6. [Conclusión](#conclusión)

---
## Visión General del Proyecto

**OrientExpress** es un juego de aventuras y misterio en Java que simula un viaje en tren. El jugador interactúa mediante diálogos con personajes (NPCs), explora vagones, recoge pistas y resuelve enigmas hasta descubrir al culpable de un crimen.

**Características principales:**
- Motor de diálogos con soporte para escenas lineales y ramificadas.
- Generación dinámica de la composición y el interior de los vagones del tren.
- Interacción con objetos e ítems dentro de cada vagón.
- Internacionalización para múltiples idiomas.

---
## Estructura del Proyecto

```text
OrientExpress/
├── Main.java
├── README.md
├── dialog/
│   ├── DialogueLine.java
│   ├── DialogueScene.java
│   ├── DialogueManager.java
│   └── Scene.java
├── characters/
│   ├── Person.java
│   └── Detective.java, Mayor.java, ...
├── world/
│   ├── TerminalUI.java
│   ├── interactable/
│   ├── item/
│   ├── map/
│   │   ├── Carriage.java
│   │   ├── CarriageBuilder.java
│   │   ├── CarriageLayout.java
│   │   ├── CarriageType.java
│   │   ├── CellType.java
│   │   └── InteriorCell.java
│   └── environment/ UtilsEnvironments.java
├── i18n/
│   ├── messages_en.properties
│   └── dialogues_en.properties
```

- **`Main.java`**: Inicializa el juego, configura idioma, crea vagones y ejecuta el bucle principal.
- **`dialog/`**: Clases para definir y gestionar diálogos y escenas.
- **`world/map/`**: Definición de la estructura del tren (vagones y celdas).
- **`i18n/`**: Ficheros de recursos para texto y diálogos en diferentes idiomas.

---
## Módulo de Diálogos

El módulo de diálogos está diseñado para cargar escenas desde archivos de propiedades y ejecutarlas secuencialmente o en función de elecciones del jugador.

### Clases Principales

#### 1. DialogueLine
```java
public record DialogueLine(Person speaker, String text, long delayMillis) { }
```
- **speaker**: quién habla (`Person`).
- **text**: texto a mostrar.
- **delayMillis**: retardo automático antes de continuar (0 = espera a ENTER).

#### 2. DialogueScene
```java
public record DialogueScene(List<DialogueLine> lines) { }
```
- Agrupa una lista de `DialogueLine` que conforman una escena.

#### 3. DialogueManager
Maneja la cola de líneas, la entrada del usuario y el historial.

- **Atributos clave**:
  - `Queue<DialogueLine> queue`: cola de líneas pendientes.
  - `List<List<DialogueLine>> history`: escenas reproducidas.
  - `Scanner input`: lector de ENTER.

- **Métodos principales**:
  1. `loadScene(DialogueScene scene)`: añade líneas a la cola.
  2. `start()`: procesa la cola:
     ```java
     while (!queue.isEmpty()) {
       DialogueLine line = queue.poll();
       display(line);
       if (line.delayMillis() > 0) Thread.sleep(line.delayMillis());
       else waitForUserAction();
     }
     ```
  3. `printHistory(PrintStream out)`: muestra un resumen tabulado.

### Flujo de Ejecución de Diálogo

1. **Carga de escena**: `Scene.sceneX(...)` construye `DialogueScene` usando `ResourceBundle`.
2. **Ejecución**: `DialogueManager.loadScene(...)` + `DialogueManager.start()`.
3. **Retos y ramificaciones**:
   - Basado en elecciones, carga escenas distintas.
   - Guarda historial para revisión.

### Definición de Escenas

Las escenas se definen en `Scene.java` junto a textos en ficheros `.properties`.

```java
public static DialogueScene scene1(ResourceBundle b, Person char1) {
  return new DialogueScene(List.of(
    new DialogueLine(char1, b.getString("scene1.line1.text"), 0),
    new DialogueLine(char1, b.getString("scene1.line2.text"), 1000)
  ));
}
```

- Los textos (`"scene1.line1.text"`) están en `dialogues_en.properties`, permitiendo fácil traducción.

---
## Generación de Vagones (Carriages)

La lógica de los vagones usa plantillas (`CarriageType`) y un builder para ensamblar el tren.

### CarriageType: Tipos de Vagón

```java
public enum CarriageType {
  VIEWPOINT(new CellType[][] {
    {FLOOR, FLOOR, SEAT, SEAT, SEAT, FLOOR, FLOOR},
    {FLOOR, FLOOR, SEAT, SEAT, SEAT, FLOOR, FLOOR},
    {FLOOR, FLOOR, SEAT, SEAT, SEAT, FLOOR, FLOOR}
  }),
  DINNER(new CellType[][] {
    {FLOOR, TABLE, TABLE, AISLE, TABLE, TABLE, FLOOR},
    {FLOOR, TABLE, TABLE, AISLE, TABLE, TABLE, FLOOR},
    {FLOOR, TABLE, TABLE, AISLE, TABLE, TABLE, FLOOR}
  }),
  LOUNGE(new CellType[][] {
    {FLOOR, SOFA, SOFA, FLOOR, SOFA, SOFA, FLOOR},
    {FLOOR, SOFA, SOFA, FLOOR, SOFA, SOFA, FLOOR},
    {FLOOR, SOFA, SOFA, FLOOR, SOFA, SOFA, FLOOR}
  });
  
  private final CellType[][] matrix;
  CarriageType(CellType[][] m) { this.matrix = m; }
  public CellType[][] getMatrix() {
    // devuelve copia profunda de matrix
  }
}
```

### CarriageLayout: Diseño Interno

```java
public class CarriageLayout {
  public static final int ROWS = 3, COLS = 7;
  private final InteriorCell[][] grid;

  public CarriageLayout() {
    grid = new InteriorCell[ROWS][COLS];
    for (int r = 0; r < ROWS; r++)
      for (int c = 0; c < COLS; c++)
        grid[r][c] = new InteriorCell(CellType.FLOOR);
  }

  public CarriageLayout(CellType[][] template) {
    this();
    for (int r = 0; r < ROWS; r++)
      for (int c = 0; c < COLS; c++)
        grid[r][c] = new InteriorCell(template[r][c]);
  }

  public InteriorCell cellAt(int r, int c) { return grid[r][c]; }
}
```

### CarriageBuilder: Patrón Builder

```java
public class CarriageBuilder {
  private final CarriageType type;
  private CarriageLayout layout;
  private final List<Door> doors = new ArrayList<>();

  public CarriageBuilder(CarriageType t) { this.type = t; }
  public CarriageBuilder setLayout(CellType[][] m) {
    this.layout = new CarriageLayout(m);
    return this;
  }
  public CarriageBuilder addDoor(int row, int col) {
    layout.cellAt(row, col).setType(CellType.DOOR);
    doors.add(new Door(row, col));
    return this;
  }
  public Carriage build() {
    return new Carriage(type, layout, doors);
  }
}
```

### Ensamblaje del Tren

```java
List<Carriage> train = new ArrayList<>();
CarriageType[] types = CarriageType.values();
for (int i = 0; i < types.length; i++) {
  CarriageBuilder b = new CarriageBuilder(types[i])
    .setLayout(types[i].getMatrix());
  if (i > 0) b.addDoor(1, 0);
  if (i < types.length - 1) b.addDoor(1, 6);
  train.add(b.build());
}
return train;
```

### Navegación entre Vagones

El movimiento entre vagones aprovecha las puertas creadas por el `CarriageBuilder` y un mapa de conexiones:

1. **Registro de puertas**: Al añadir una puerta con `addDoor(row, col)`, el builder no solo modifica la celda a `CellType.DOOR`, sino que almacena un objeto `Door` que conserva el índice del vagón, la fila y la columna.

2. **Mapa de conexiones**: Tras construir todos los vagones, en `Main.getCarriages()` o en un método auxiliar se genera un `Map<String, Integer>` donde:
   - La **clave** es una cadena formateada como `"vagónIndex,row,col"`.
   - El **valor** es el índice del vagón al que conecta esa puerta.

   ```java
   Map<String,Integer> connections = new HashMap<>();
   for (int i = 0; i < train.size(); i++) {
     Carriage c = train.get(i);
     for (Door d : c.getDoors()) {
       String key = i + "," + d.getRow() + "," + d.getCol();
       int neighbor = (d.getCol() == 0) ? i - 1 : i + 1;
       connections.put(key, neighbor);
     }
   }
   ```

3. **Detección de movimiento**: En la UI (`TerminalUI`):
   - Cuando el jugador se mueve a una celda, se comprueba si `cell.getType() == CellType.DOOR`.
   - Se construye la misma clave (`"currentIndex,row,col"`) y se consulta `connections.get(key)`.
   - Si existe un índice asociado, se actualiza `currentCarriageIndex` al vagón vecino.

   ```java
   void moveThroughDoor(int row, int col) {
     String key = currentIndex + "," + row + "," + col;
     Integer next = connections.get(key);
     if (next != null) {
       currentIndex = next;
       player.setPosition(row, (col == 0) ? CarriageLayout.COLS - 1 : 0);
       renderCurrentCarriage();
     }
   }
   ```

---
## Internacionalización (i18n)

Ficheros en `i18n/`:
- `messages_en.properties`: textos genéricos (menús, prompts).
- `dialogues_en.properties`: líneas de diálogo.
- Análogos para otros idiomas (`_es.properties`).

**Uso en código**:
```java
ResourceBundle bundleText = ResourceBundle.getBundle("i18n/messages", locale);
TerminalUI ui = new TerminalUI(bundleText);
ResourceBundle bundleDialog = ResourceBundle.getBundle("i18n/dialogues", locale);
```
Permite cambiar idioma al inicio o durante el juego.

---
## Historia del Juego

La historia de **OrientExpress** se ambienta en la década de 1920, durante un lujoso viaje en el famoso tren Orient Express que recorre Europa. El jugador asume el rol de un detective invitado a bordo tras la aparición de un misterioso asesinato en uno de los vagones.

**Sinopsis principal:**
1. **Introducción**: El detective aborda el tren en París con la misión de escoltar a un testigo clave. Durante la cena en el vagón comedor, se descubre el cuerpo de un pasajero prominente.
2. **Investigación**: El detective debe interrogar a los personajes (alcalde, condesa, asistente, etc.) en distintos vagones, recolectando pistas y objetos que revelan móviles y coartadas.
3. **Exploración**: Cada vagón (mirador, salón de té, dormitorio) ofrece nuevos entornos para explorar y encontrar pistas ocultas, usando el sistema de generación de vagones.
4. **Confrontación**: Tras reunir suficiente evidencia, el detective organiza un reencuentro en el vagón lounge donde expone al verdadero culpable.
5. **Desenlace**: Dependiendo de las elecciones y pistas obtenidas, el final puede variar, ofreciendo múltiples resoluciones al misterio.

La narrativa dinámica y las escenas de diálogo ramificadas permiten al jugador experimentar diferentes versiones del caso según sus decisiones.

## Conclusión

El proyecto **OrientExpress** es un ejemplo de cómo combinar:
- Un **motor de diálogos** modular y flexible.
- Generación de escenarios mediante **plantillas y builder pattern**.
- Arquitectura clara que separa UI, mundo, personajes y diálogos.

Esta estructura facilita la adición de nuevos vagones, escenas de diálogo y características, manteniendo el código organizado y escalable.
