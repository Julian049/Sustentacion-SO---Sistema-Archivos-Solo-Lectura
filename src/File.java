public class File {
    private String path;
    private String content;
    private int size;

    public File(String path, String content) {
        this.path = path;
        this.content = content;
        this.size = content.length();
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
}
