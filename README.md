# Prerequisites

Java 22

# Build notice

- `glfw3.dll` is obtained from https://www.glfw.org/download.html (64-bit Windows binaries, `lib-vc2022`). Feel free to
  download appropriate for you and replace existing one.

# Running example:

- Compile using `mvn clean package`
- Run from `target` with path to `dll` directory:

```
java --enable-native-access=opengl.ffm --module-path opengl-ffm-0.1-SNAPSHOT.jar --module opengl.ffm/ru.fdoke.ffm.opengl.App ../dll/
```

![Exmaple](https://github.com/FDoKE/opengl-ffm/blob/master/result.png?raw=true)

# Description

With new FFM Api in Java 22 there are a lot of possibilities of direct usage of native libraries. This project uses
OPENGL/GLFW dll's to open a window and draw a triangle. It's raw and should not be concerned as the proper way of using
it.

## Changelog

- ### 07.11.2025
    - Updated code to finalized FFM api in Java 22.