public class MorseDecoder {

    private BTree<String> morseTree;

    public MorseDecoder() {
        morseTree = new BTree<>();

        // Root holds empty string
        morseTree.addByPath("", "");

        // ---- level 1
        add("E", ".");   // dot → left
        add("T", "-");   // dash → right

        // ---- level 2
        add("I", "..");
        add("A", ".-");
        add("N", "-.");
        add("M", "--");

        // ---- level 3
        add("S", "...");
        add("U", "..-");
        add("R", ".-.");
        add("W", ".--");
        add("D", "-..");
        add("K", "-.-");
        add("G", "--.");
        add("O", "---");

        // ---- level 4
        add("H", "....");
        add("V", "...-");
        add("F", "..-.");
        add("L", ".-..");
        add("P", ".--.");
        add("J", ".---");
        add("B", "-...");
        add("X", "-..-");
        add("C", "-.-.");
        add("Y", "-.--");
        add("Z", "--..");
        add("Q", "--.-");

        // ---- digits (standard Morse)
        add("5", ".....");
        add("4", "....-");
        add("3", "...--");
        add("2", "..---");
        add("1", ".----");
        add("6", "-....");
        add("7", "--...");
        add("8", "---..");
        add("9", "----.");
        add("0", "-----");
    }

    private void add(String symbol, String morse) {
        String path = morseToPath(morse);
        if (path != null) {
            morseTree.addByPath(symbol, path);
        }
    }

    private String morseToPath(String morse) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < morse.length(); i++) {
            char c = morse.charAt(i);
            if (c == '.') {
                sb.append('L');
            } else if (c == '-') {
                sb.append('R');
            } else {
                return null; // invalid character
            }
        }
        return sb.toString();
    }

    public String toString() {
        return morseTree.pre();
    }

    public String decode(String morseStr) {
        if (morseStr == null) {
            return "";
        }

        String path = morseToPath(morseStr);
        if (path == null) {
            return ""; // invalid characters
        }

        String result = morseTree.findByPath(path);
        if (result == null || result.length() == 0) {
            return "";
        }

        return result;
    }
}
