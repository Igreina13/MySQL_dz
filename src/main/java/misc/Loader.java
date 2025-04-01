package misc;

public class Loader {
    public void loader(int rLines) {
        if (rLines == 0) System.out.print("\t\u001b[3mПодождите, идёт загрузка...\u001B[0m");
        else System.out.print("\r".repeat(rLines));
    }

}
