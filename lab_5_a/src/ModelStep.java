public class ModelStep {
    private int sheet;
    private int len;

    public ModelStep(int sheet, int len) {
        this.sheet = sheet;
        this.len = len;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getSheet() {
        return sheet;
    }

    public int getLen() {
        return len;
    }
}
