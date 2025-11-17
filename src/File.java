import java.time.LocalDate;

//Clase archivo
public class File {
    private String path;
    private String content;
    private int size;
    private String dateCreated;
    private int inode;

    public File(String path, String content) {
        this.path = path;
        this.content = content;
        this.size = content.length();
        this.dateCreated = LocalDate.now().toString();
        this.inode = Math.abs(path.hashCode());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getInode() {
        return inode;
    }

    public void setInode(int inode) {
        this.inode = inode;
    }
}
