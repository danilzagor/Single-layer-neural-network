public class TextEntity {
    final private String language;
    final private String text;
    final private int[] alphabetCount = new int[26];

    public TextEntity(String language, String text) {
        this.language = language;
        this.text = text;
        setAlphabetCount(text);
    }

    private void setAlphabetCount(String text) {
        for (Integer integer : alphabetCount) {
            alphabetCount[integer] = 0;
        }
        text = text.toLowerCase();
        for (char c : text.toCharArray()) {
            if(c>='a' && c<='z')
                alphabetCount[c-97] +=1;
        }
    }

    public String getLanguage() {
        return language;
    }

    public String getText() {
        return text;
    }

    public int[] getAlphabetCount() {
        return alphabetCount;
    }
}
