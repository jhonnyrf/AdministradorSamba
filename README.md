# 🖥️ ProyectoAso

## Administrador Gráfico de Samba para Linux

ProyectoAso es una aplicación de escritorio desarrollada en **Java (Swing)** que permite administrar gráficamente el servicio **Samba** en sistemas Linux.

La aplicación permite:

- Editar el archivo `/etc/samba/smb.conf`
- Gestionar comparticiones (shares)
- Administrar usuarios Samba
- Configurar permisos y máscaras
- Controlar el servicio `smb.service`

Todo sin necesidad de editar manualmente el archivo de configuración.

---

# 🎯 Objetivo del Proyecto

El objetivo es facilitar la administración del servidor Samba mediante una interfaz gráfica intuitiva que automatiza:

- La edición estructurada del archivo `smb.conf`
- La ejecución de comandos administrativos del sistema
- La validación de parámetros
- La gestión segura de usuarios

---

# 🏗️ Arquitectura del Sistema

La aplicación está construida bajo una arquitectura basada en:

### 🧠 Modelo

- `SambaConfigSection.java`
  Representa cada sección del archivo `smb.conf` en memoria.

### 🖥 Vista

- Formularios `.form` generados con NetBeans
- Interfaces Swing

### ⚙ Controlador / Lógica

- `principal.java` (control central)
- Clases modales especializadas para edición de parámetros

---

# 🚀 Funcionalidades

## 📁 1. Gestión de Comparticiones (Shares)

Permite:

- Crear nuevas secciones
- Editar secciones existentes
- Eliminar comparticiones
- Agregar parámetros personalizados

Soporta parámetros como:

- `path`
- `comment`
- `read only`
- `printable`
- `valid users`
- `write list`
- `admin users`
- `create mask`
- `directory mask`

---

## 👥 2. Gestión de Usuarios Samba

La aplicación ejecuta comandos reales del sistema:

- Verificar usuario Linux → `id`
- Crear usuario Linux → `useradd`
- Asignar contraseña → `chpasswd`
- Agregar usuario a Samba → `smbpasswd -a`
- Listar usuarios Samba → `pdbedit -L`

Esto convierte la aplicación en una herramienta administrativa real y funcional.

---

## 🔐 3. Editor de Permisos (Máscaras)

Incluye modales especializados:

- `ModalMaskAdd`
- `ModalMaskEdit`
- `ModalMaskInputAdd`
- `ModalInputMaskEdit`

Permite:

- Seleccionar permisos r / w / x
- Generar automáticamente valores octales (ej: 0755)
- Aplicarlos directamente al archivo de configuración

---

## ⚙ 4. Gestión del Servicio Samba

Controla el servicio del sistema mediante:

- `systemctl start smb`
- `systemctl restart smb`
- `systemctl enable smb`

---

## 🧩 5. Edición Dinámica de Parámetros

Clases especializadas:

- `ModalBinarioAdd / Edit`
- `ModalTextInputEdit`
- `ModalPathEdit`
- `ModalUsuariosEdit`
- `ModalAñadirParametro`

Cada tipo de parámetro tiene su interfaz específica según su naturaleza:

| Tipo              | Modal          |
| ----------------- | -------------- |
| Booleano (Yes/No) | ModalBinario   |
| Texto             | ModalTextInput |
| Ruta              | ModalPath      |
| Usuarios          | ModalUsuarios  |
| Máscara           | ModalMask      |

---

# 📂 Estructura del Proyecto

```
proyectoAso
│
├── build/
│   ├── classes/proyectoaso/
│   ├── generated-sources/
│   └── ...
│
├── dist/
│   ├── proyectoAso.jar
│   └── README.TXT
│
├── nbproject/
│   ├── build-impl.xml
│   ├── project.properties
│   └── ...
│
├── src/proyectoaso/
│   ├── AgregarSeccion.java
│   ├── EditarSeccion.java
│   ├── Main.java
│   ├── ModalAgregarUsuario.java
│   ├── ModalAñadirParametro.java
│   ├── ModalBinarioAdd.java
│   ├── ModalBinarioEdit.java
│   ├── ModalInputMaskEdit.java
│   ├── ModalMaskAdd.java
│   ├── ModalMaskEdit.java
│   ├── ModalMaskInputAdd.java
│   ├── ModalPathEdit.java
│   ├── ModalTextInputEdit.java
│   ├── ModalUsuarioAdd.java
│   ├── ModalUsuariosEdit.java
│   ├── principal.java
│   └── SambaConfigSection.java
│
├── build.xml
├── manifest.mf
├── README.md
└── test/
```

---

# 💻 Requisitos

- Sistema operativo Linux
- Java JDK 8 o superior
- Samba instalado
- Permisos sudo

Instalar Samba (Arch Linux):

```bash
sudo pacman -S samba
```

En Debian/Ubuntu:

```bash
sudo apt install samba
```

Habilitar el servicio:

```bash
sudo systemctl enable smb
sudo systemctl start smb
```

---

# ▶️ Ejecución

Desde el archivo compilado:

```bash
java -jar dist/proyectoAso.jar
```

O ejecutar `Main.java` desde NetBeans.

---

# 🔒 Consideraciones de Seguridad

⚠ La aplicación ejecuta comandos administrativos del sistema.

Requiere permisos elevados para:

- Modificar `/etc/samba/smb.conf`
- Crear usuarios Linux
- Configurar contraseñas
- Reiniciar servicios

Se recomienda:

- Ejecutar con sudo controlado
- Implementar en el futuro un sistema de privilegios más seguro
- Agregar respaldos automáticos del archivo antes de sobrescribirlo

---

# 🧠 Flujo Interno

1. Se carga el archivo `/etc/samba/smb.conf`
2. Se parsea en objetos `SambaConfigSection`
3. Se manipulan las secciones en memoria
4. Al guardar:
   - Se reconstruye el archivo completo
   - Se sobrescribe el archivo original
   - Se reinicia el servicio (opcional)

---

# 📈 Mejoras Futuras

- Validación sintáctica avanzada del smb.conf
- Respaldo automático antes de guardar
- Implementación formal de patrón MVC
- Migración a JavaFX
- Soporte multi-distribución
- Sistema de logs
- Manejo robusto de errores
- Detección automática del archivo de configuración
- Interfaz moderna

---

# 🎓 Uso Académico

Proyecto desarrollado con fines educativos para:

- Administración de servicios Linux
- Integración Java + sistema operativo
- Manipulación de archivos de configuración
- Desarrollo de aplicaciones de escritorio

---

# 👨‍💻 Autor

**Jhonny Rojas Flores**
Linux Administrator & Java Developer

---

# 📜 License

This project is licensed under the MIT License - see the LICENSE file for details.
