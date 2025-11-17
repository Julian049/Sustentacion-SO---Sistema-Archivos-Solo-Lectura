import java.util.ArrayList;

//Clase que simula el sistema de archivos SquashFS
public class SquashFS {
    /*
    Se definen las variables para el funcionamiento del sistema SquashFS:
    files -> Lista que almacena todos los archivos del sistema.
    imageBuild -> Bandera booleana que indica si la imagen ya fue construida
    totalSize -> Tamaño total (en bytes) de todos los archivos sin comprimir.
    compressedSize -> Tamaño (en bytes) de la imagen después de la compresión (calculado en build).
    MAX_SIZE -> Constante que define el tamaño máximo permitido para la imagen.
     */
    private ArrayList<File> files;
    private boolean imageBuild;
    private int totalSize;
    private int compressedSize;
    private static final long MAX_SIZE = 214748364;

    /*
    Agrega un archivo al sistema de archivos.
    Solo funciona si la imagen no ha sido construida (imageBuild == false).
    Verifica que el nuevo archivo no exceda el tamaño máximo (MAX_SIZE).
    Si se agrega, actualiza el tamaño total (totalSize).
     */

    public void addFile(File file) {
        //Inicializa la lista de archivos si es la primera vez
        if (files == null) {
            files = new ArrayList<>();
        }

        //Verifica si la imagen ya fue construida (modo solo lectura)
        if (!imageBuild) {
            //Comprueba que no se exceda el tamaño máximo
            if (file.getSize() + totalSize <= MAX_SIZE) {
                files.add(file);
                totalSize += file.getSize();
                System.out.println("Archivo agregado: " + file.getPath() + " (Tamaño: " + file.getSize() + " bytes)");
            } else {
                System.out.println("No se puede agregar el archivo " + file.getPath() + ". Excede el tamaño máximo de la imagen SquashFS.");
            }
        } else {
            //Si la imagen ya está construida, rechaza el archivo
            System.out.println("La imagen ya fue construida, no se pueden agregar más archivos.");
        }
    }

    /*
    Construye la imagen del sistema de archivos.
    Este proceso es irreversible para la instancia actual.
    Establece la bandera imageBuild a true, bloqueando futuras modificaciones (modo solo lectura).
    Calcula el tamaño comprimido (simulado como un 60% del original).
     */
    public void buildImage() {
        System.out.println("Construyendo la imagen SquashFS...");
        //Marca la imagen como construida (solo lectura)
        imageBuild = true;
        System.out.println("Calculando tamaño total de archivos...");
        System.out.println("Comprimendo archivos...");
        //Simula la compresión (ej. 60% del tamaño total)
        compressedSize = (int) (totalSize * 0.6);
        double compressionRatio = ((double) (totalSize - compressedSize) / totalSize) * 100;
        System.out.println("Total tamaño de archivos: " + totalSize + " bytes");
        System.out.println("Tamaño comprimido de la imagen SquashFS: " + compressedSize + " bytes");
        System.out.println("Imagen SquashFS construida con éxito.");

    }

    //Busca un archivo en la lista 'files' usando su ruta (path)
    //Retorna el objeto File si lo encuentra, o null si no existe.
    public File searchFile(String path) {
        if (files != null) {
            for (File file : files) {
                if (file.getPath().equals(path)) {
                    return file;
                }
            }
        }
        return null;
    }

    /*
    Modifica el contenido de un archivo existente.
    Solo permite modificaciones si la imagen NO ha sido construida (imageBuild == false).
    Si la imagen está construida, imprime un error.
    Actualiza el contenido y el tamaño del archivo.
     */
    public void modifyFile(String path, String newContent) {
        //Si la imagen está construida, no se permite modificar
        if (imageBuild) {
            System.out.println("No se pueden modificar archivos en una imagen SquashFS ya construida.");
        } else {
            //Si la imagen no está construida, procede con la modificación
            if (searchFile(path) == null) {
                System.out.println("Archivo no encontrado: " + path);
                return;
            }
            File file = searchFile(path);
            file.setContent(newContent);
            file.setSize(newContent.length());
            System.out.println("Archivo modificado: " + file.getPath());
        }
    }

    /*
    Elimina un archivo del sistema.
    Solo permite eliminaciones si la imagen NO ha sido construida (imageBuild == false).
    Si la imagen está construida, imprime un error.
    Actualiza el tamaño total (totalSize) al eliminar el archivo.
     */
    public void deleteFile(String path) {
        //Si la imagen está construida, no se permite eliminar
        if (imageBuild) {
            System.out.println("No se pueden eliminar archivos en una imagen SquashFS que ya esta construida.");
        } else {
            //Si la imagen no está construida, procede con la eliminación
            File file = searchFile(path);
            if (file != null) {
                files.remove(file);
                //Resta el tamaño del archivo eliminado del total
                totalSize -= file.getSize();
                System.out.println("Archivo eliminado: " + file.getPath());
            } else {
                System.out.println("Archivo no encontrado: " + path);
            }
        }
    }

    //Muestra en consola un listado de todos los archivos
    public void seeAllFiles() {
        if (files != null && !files.isEmpty()) {
            for (File file : files) {
                System.out.println("├─ Archivo: " + file.getPath());
                System.out.println("│  ├─ Tamaño: " + file.getSize() + " bytes");
                System.out.println("│  ├─ Contenido: " + file.getContent());
                System.out.println("│  ├─ Fecha creación: " + file.getDateCreated());
                System.out.println("│  └─ Inodo: " + file.getInode());
                System.out.println("│");
            }
            System.out.println("└─ Total archivos: " + files.size());
        } else {
            System.out.println("No hay archivos en el sistema de archivos SquashFS.");
        }
    }

    //Muestra las estadísticas actuales del sistema de archivos.
    public void showStatistics() {
        System.out.println("=======ESTADÍSTICAS DEL SISTEMA SQUASHFS=======");
        System.out.println("Estado de la imagen: " + (imageBuild ? "CONSTRUIDA (solo lectura)" : "NO CONSTRUIDA (lectura y escritura)"));
        System.out.println("Total de archivos: " + (files != null ? files.size() : 0));
        System.out.println("Tamaño total sin comprimir: " + totalSize + " bytes");

        //Muestra el tamaño comprimido solo si la imagen ya fue construida
        if (imageBuild) {
            System.out.println("Tamaño comprimido: " + compressedSize + " bytes");
            System.out.println("Espacio ahorrado: " + (totalSize - compressedSize) + " bytes");
        }
    }
}