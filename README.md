# Polygon Area Calculator 🧮

This is a Java-based desktop GUI application to plot custom polygons, calculate their area, perimeter, and internal angles. You can interactively drag vertices, zoom using your mouse wheel, and toggle an X-Y grid for alignment and accuracy.

## ✨ Features

- Plot any polygon with 3 or more vertices
- Drag-and-drop vertices to reshape dynamically
- Display:
  - Vertex coordinates
  - Edge lengths
  - Internal angles
  - Total area and perimeter
- Mouse zoom functionality
- Toggleable X-Y grid aligned with Cartesian axes
- Real-time updates as you modify points
- Coordinate system centered at (0,0) with mathematical orientation

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or higher
- An IDE (like IntelliJ, Eclipse, or VS Code) or terminal

### Compile & Run

**Using terminal:**

```bash
javac -d . Main.java gui/*.java utils/*.java
java Main

